package me.mihaif.licenta.network;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.mihaif.licenta.common.events.network.ConnectionErrorEvent;
import me.mihaif.licenta.common.events.network.ConnectionSuccessfulEvent;
import me.mihaif.licenta.common.events.network.NetworkEventBus;
import me.mihaif.licenta.common.events.ui.DrawEvent;
import me.mihaif.licenta.common.events.ui.UIEventBus;
import me.mihaif.licenta.common.events.ui.WaitingSceneLoadedEvent;
import me.mihaif.licenta.network.commands.client.ClientCommand;
import me.mihaif.licenta.network.commands.client.ClientCommandFactory;
import me.mihaif.licenta.network.commands.client.ClientCommandRegistry;
import me.mihaif.licenta.network.commands.server.ServerCommand;
import me.mihaif.licenta.network.commands.server.ServerCommandFactory;
import me.mihaif.licenta.network.commands.server.ServerCommandRegistry;
import me.mihaif.licenta.network.utilities.PlayerData;

public class Client {
    private static Client instance;

    private String host = "localhost";
    private int port = 0;
    private PlayerData localPlayer = new PlayerData("Fallback", -1);

    private final Connection connection = new Connection();
    private Thread listenThread;

    private Client() {
    	
    }
    
    
    public static synchronized Client getInstance() {
        if (instance == null) instance = new Client();
        return instance;
    }

    private final List<PlayerData> otherPlayers = new ArrayList<PlayerData>();

    public void setUsername(String username) { this.localPlayer.username = username; }
    public void setAvatarID(int avatarID) { this.localPlayer.avatarID = avatarID; }
    public void setHost(String host) { this.host = host; }
    public void setPort(int port) { this.port = port; }
    public void addOtherPlayer(PlayerData otherPlayer) {this.otherPlayers.add(otherPlayer); };
    public void addOtherPlayers(List<PlayerData> otherPlayers) {this.otherPlayers.addAll(otherPlayers); };
    
    public PlayerData getLocalPlayer() {return localPlayer; }
	public int getPort() {return port;}
	public String getHost() {return host;}
	public List<PlayerData> getOtherPlayers() {return otherPlayers;}
     
    public void startAsync() {
        if (listenThread != null && listenThread.isAlive()) return;
        
        ServerCommandFactory.createCommands();
        ClientCommandFactory.createCommands();
        
        listenThread = new Thread(() -> {
            try {
                connection.connect(host, port);
                NetworkEventBus.getInstance().fire(new ConnectionSuccessfulEvent());
                listen();
            } catch (IOException e) {
                NetworkEventBus.getInstance().fire(new ConnectionErrorEvent());
            } finally {
                connection.close();
            }
        }, "Client-Network-Thread");
        
        UIEventBus.getInstance().addListener(DrawEvent.class, event -> {
        	System.out.println("[INFO][NET] Received UI Notification: User Draw -> Sending Draw Request To Server");
        	ClientCommandRegistry.get("DRAW").send(event.x1, event.y1, event.x2, event.y2, event.thickness, event.colour.getRed(), event.colour.getGreen(), event.colour.getBlue());
        });
        UIEventBus.getInstance().addListener(WaitingSceneLoadedEvent.class, _ ->{
        	System.out.println("[INFO][NET] Received UI Notification: WaitingScene Loaded -> Asking Server To Provide Player List");
        	ClientCommandRegistry.get("SYNC_PLAYERS").send();
        });
        
        
        listenThread.setDaemon(true);
        listenThread.start();
    }

    private void listen() throws IOException {
        String line;
        while ((line = connection.readLine()) != null) {
        	System.out.println("[INFO][NET] Received Command From Server: " + line);
        	String[] tokens = line.split(" ");
        	String cmd = tokens[0];
        	String[] payload = Arrays.copyOfRange(tokens, 1, tokens.length);
        	
        	ServerCommand serverCommand = ServerCommandRegistry.get(cmd);
        	
        	if(serverCommand != null) {
        		serverCommand.execute(payload);
        	}
        	else {
        		System.err.println("[WARNING][NET] Server Issued An Unknown Command: " + cmd + " => Check The Server");
        	}
        }
    }
    
    public void sendRaw(String rawClientCommand) {
    	System.out.println("[INFO][NET] Sent Command To Server: " + rawClientCommand);
    	connection.send(rawClientCommand);
    }

}
package me.mihaif.licenta.network.utilities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import me.mihaif.licenta.common.events.network.DrawEvent;

public class ProtocolParser {

    public static DrawEvent parseDrawPayload(String[] tokens) throws NumberFormatException {
        double x1 = Double.parseDouble(tokens[0]);
        double y1 = Double.parseDouble(tokens[1]);
        double x2 = Double.parseDouble(tokens[2]);
        double y2 = Double.parseDouble(tokens[3]);
        double thickness = Double.parseDouble(tokens[4]);
        Color colour = Color.color(
            Double.parseDouble(tokens[5]),
            Double.parseDouble(tokens[6]),
            Double.parseDouble(tokens[7])
        );
        return new DrawEvent(x1, y1, x2, y2, thickness, colour);
    }
    

    public static PlayerData parsePlayerData(String playerData) throws NumberFormatException{
    	String[] tokens = playerData.split(":");
    	String username = tokens[0];
    	int avatarID = Integer.parseInt(tokens[1]);
    	return new PlayerData(username, avatarID);
    }
    public static List<PlayerData> parsePlayersData(String playersData) throws NumberFormatException{
    	List<PlayerData> list = new ArrayList<PlayerData>();
    	String[] players = playersData.split(",");
    	for(String player : players) {
    		list.add(parsePlayerData(player));
    	}
    	return list;
    }
    
}


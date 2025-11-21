package me.mihaif.licenta.network.commands.server;



import me.mihaif.licenta.common.events.network.NetworkEventBus;
import me.mihaif.licenta.common.events.network.PlayerListReceiveEvent;
import me.mihaif.licenta.network.Client;
import me.mihaif.licenta.network.utilities.ProtocolParser;

public class SyncPlayersCommand implements ServerCommand{

	@Override
	public void execute(String[] payload) {
		try {
			Client.getInstance().addOtherPlayers(ProtocolParser.parsePlayersData(payload[0]));
			NetworkEventBus.getInstance().fire(new PlayerListReceiveEvent());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

package me.mihaif.licenta.network.commands.server;

import me.mihaif.licenta.common.events.network.NetworkEventBus;
import me.mihaif.licenta.common.events.network.PlayerJoinEvent;
import me.mihaif.licenta.network.Client;
import me.mihaif.licenta.network.utilities.ProtocolParser;

public class NewPlayerCommand implements ServerCommand{

	@Override
	public void execute(String[] payload) {
		try {
			Client.getInstance().addOtherPlayer(ProtocolParser.parsePlayerData(payload[0]));
			NetworkEventBus.getInstance().fire(new PlayerJoinEvent());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

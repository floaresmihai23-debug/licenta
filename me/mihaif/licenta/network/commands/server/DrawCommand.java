package me.mihaif.licenta.network.commands.server;

import me.mihaif.licenta.common.events.network.NetworkEventBus;
import me.mihaif.licenta.network.utilities.ProtocolParser;

public class DrawCommand implements ServerCommand{
	
	@Override
	public void execute(String[] payload) {
		try {
			NetworkEventBus.getInstance().fire(ProtocolParser.parseDrawPayload(payload));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

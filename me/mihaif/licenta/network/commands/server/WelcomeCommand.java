package me.mihaif.licenta.network.commands.server;

import me.mihaif.licenta.common.events.network.NetworkEventBus;
import me.mihaif.licenta.common.events.network.WelcomeEvent;
import me.mihaif.licenta.network.Client;

public class WelcomeCommand implements ServerCommand {

	@Override
	public void execute(String[] payload){
		try {
			Client.getInstance().setAvatarID(Integer.parseInt(payload[0]));
			NetworkEventBus.getInstance().fire(new WelcomeEvent());	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

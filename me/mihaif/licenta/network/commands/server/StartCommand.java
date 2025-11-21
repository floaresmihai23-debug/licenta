package me.mihaif.licenta.network.commands.server;

import me.mihaif.licenta.common.events.network.NetworkEventBus;
import me.mihaif.licenta.common.events.network.StartEvent;

public class StartCommand implements ServerCommand{

	@Override
	public void execute(String[] payload) {
		try {
			NetworkEventBus.getInstance().fire(new StartEvent());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

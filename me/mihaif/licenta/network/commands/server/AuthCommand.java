package me.mihaif.licenta.network.commands.server;

import me.mihaif.licenta.network.Client;
import me.mihaif.licenta.network.commands.client.ClientCommandRegistry;

public class AuthCommand implements ServerCommand{

	@Override
	public void execute(String[] payload) {
		ClientCommandRegistry.get("USER").send(Client.getInstance().getLocalPlayer().username);;
	}

}

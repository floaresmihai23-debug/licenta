package me.mihaif.licenta.network.commands.client;

import me.mihaif.licenta.network.Client;

public class SyncPlayersCommand implements ClientCommand {

	@Override
	public void send(Object... payload) {
		Client.getInstance().sendRaw("SYNC_PLAYERS");
		
	}
	
}

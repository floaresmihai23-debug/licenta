package me.mihaif.licenta.network.commands.client;

import me.mihaif.licenta.network.commands.Command;

public interface ClientCommand extends Command{
	void send(Object... payload);
}

package me.mihaif.licenta.network.commands.server;

import me.mihaif.licenta.network.commands.Command;

public interface ServerCommand extends Command{
	void execute(String[] payload);
}

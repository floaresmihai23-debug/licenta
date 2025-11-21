package me.mihaif.licenta.network.commands.server;

import java.util.HashMap;
import java.util.Map;

public class ServerCommandRegistry {
	private static final Map<String, ServerCommand> COMMANDS = new HashMap<>();
	
	private ServerCommandRegistry() {}
	
	public static void register(String commandTag, ServerCommand command) {
        COMMANDS.put(commandTag, command);
    }

    public static ServerCommand get(String commandTag) {
        return COMMANDS.get(commandTag);
    }
}

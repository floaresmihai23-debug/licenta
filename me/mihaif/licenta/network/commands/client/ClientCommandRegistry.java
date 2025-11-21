package me.mihaif.licenta.network.commands.client;

import java.util.HashMap;
import java.util.Map;

public class ClientCommandRegistry {
	private static final Map<String, ClientCommand> COMMANDS = new HashMap<>();
	
	private ClientCommandRegistry() {}
	
	public static void register(String commandTag, ClientCommand command) {
        COMMANDS.put(commandTag, command);
    }

    public static ClientCommand get(String commandTag) {
        return COMMANDS.get(commandTag);
    }
}


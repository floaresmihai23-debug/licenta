package me.mihaif.licenta.network.commands.client;



public class ClientCommandFactory {
	public static void createCommands() {
        ClientCommandRegistry.register("USER", new UserCommand());
        ClientCommandRegistry.register("DRAW", new DrawCommand());
        ClientCommandRegistry.register("SYNC_PLAYERS", new SyncPlayersCommand());
        ClientCommandRegistry.register("GUESS", new DrawCommand());
        ClientCommandRegistry.register("READY", null);
	}
}

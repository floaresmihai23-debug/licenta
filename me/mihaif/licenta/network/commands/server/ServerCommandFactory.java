package me.mihaif.licenta.network.commands.server;

public class ServerCommandFactory {
	public static void createCommands() {
        ServerCommandRegistry.register("DRAW", new DrawCommand());
        ServerCommandRegistry.register("WELCOME", new WelcomeCommand());
        ServerCommandRegistry.register("NEW_PLAYER", new NewPlayerCommand());
        ServerCommandRegistry.register("SYNC_PLAYERS", new SyncPlayersCommand());
        ServerCommandRegistry.register("AUTH", new AuthCommand());
        ServerCommandRegistry.register("START", new StartCommand());
	}
}

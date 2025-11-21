package me.mihaif.licenta.network.commands.client;

import java.util.Arrays;
import java.util.stream.Collectors;

import me.mihaif.licenta.network.Client;

public class DrawCommand implements ClientCommand{

	@Override
	public void send(Object... payload) {
		String serializedPayload = Arrays.stream(payload)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
		
		Client.getInstance().sendRaw("DRAW " + serializedPayload);
		
	}

}

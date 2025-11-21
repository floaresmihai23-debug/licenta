package me.mihaif.licenta.common.events.network;


import me.mihaif.licenta.common.events.EventBus;

public class NetworkEventBus extends EventBus<NetworkEvent> {
    private static final NetworkEventBus INSTANCE = new NetworkEventBus();
    public static NetworkEventBus getInstance() { return INSTANCE; }
    private NetworkEventBus() {}
}


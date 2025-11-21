package me.mihaif.licenta.common.events.ui;

import me.mihaif.licenta.common.events.EventBus;

public class UIEventBus extends EventBus<UIEvent> {
    private static final UIEventBus INSTANCE = new UIEventBus();
    public static UIEventBus getInstance() { return INSTANCE; }
    private UIEventBus() {}
}

package me.mihaif.licenta.common.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class EventBus<T extends Event> {

    private final Map<Class<?>, List<Consumer<? extends T>>> listeners = new HashMap<>();

    protected EventBus() {}

    public synchronized <E extends T> void addListener(Class<E> type, Consumer<E> listener) {
        listeners.computeIfAbsent(type, _ -> new ArrayList<>()).add(listener);
    }

    @SuppressWarnings("unchecked")
    public synchronized <E extends T> void fire(E event) {
        var list = listeners.get(event.getClass());
        if (list == null) return;

        for (Consumer<? extends T> raw : list) {
            ((Consumer<T>) raw).accept(event);
        }
    }

    public synchronized <E extends T> void removeListener(Class<E> type, Consumer<E> listener) {
    	List<Consumer<? extends T>> list= listeners.get(type);
        if (list != null) {
            list.remove(listener);
            if (list.isEmpty()) {
                listeners.remove(type);
            }
        }
    }
}


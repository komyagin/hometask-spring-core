package io.github.komyagin.dao.repository;

import io.github.komyagin.dao.entity.Event;
import io.github.komyagin.services.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventsRepository implements EventService {
    private static long id = 0;
    private static Map<Long, Event> eventsContainer = new HashMap<>();

    @Override
    public Event save(@Nonnull Event event) {
        event.setId(id);
        return eventsContainer.put(id++, event);
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventsContainer.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventsContainer.get(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventsContainer.values();
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventsContainer.values().stream()
                .filter(event -> event.getName().equalsIgnoreCase(name))
                .findAny().orElse(null);
    }
}

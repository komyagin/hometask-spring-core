package io.github.komyagin.cmds;

import io.github.komyagin.dao.entity.Event;
import io.github.komyagin.dao.entity.EventRating;
import io.github.komyagin.dao.repository.EventsRepository;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
public class EventServiceCommands extends AbstractCommands implements CommandMarker {
    private EventsRepository repository;

    public void setRepository(EventsRepository repository) {
        this.repository = repository;
    }

    @CliAvailabilityIndicator(value = {"add-event", "get-event-by-id",
            "remove-event"})
    public boolean isAdminEnabled() {
        return adminEnableExecuted;
    }

    @CliCommand(value = "add-event", help = "Adding n event. Mandatory parameters: --firstName --lastName --email")
    public String addEvent(
            @CliOption(key = { "name" }, mandatory = true,
                    help = "name of an event") final String name,
            @CliOption(key = { "price"}, mandatory = true,
                    help = "base price of an event") final String price,
            @CliOption(key = { "email" }, mandatory = true,
                    help = "Rating of an event LOW, MID, HIGH") final String rating) {
        Event event = new Event();
        event.setName(name);
        event.setBasePrice(Double.parseDouble(price));
        event.setRating(EventRating.valueOf(rating));
        repository.save(event);
        return "Event added!\n";
    }

    @CliCommand(value = "get-event-by-id", help = "Getting an event by its id number")
    public String getEventById(
            @CliOption(key = "id", mandatory = true, help = "Id number of an event") final String id) {
        return repository.getById(Long.valueOf(id)).toString();
    }

    @CliCommand(value = "remove-event", help = "Removing an event by id number")
    public String removeEventById(
            @CliOption(key = "id", help = "Provide event's id number") final String id) {
        Event event = repository.getById(Long.valueOf(id));
        if (event != null) {
            repository.remove(event);
            return "Event deleted!";
        }
        return "Event not found!";
    }

    @CliCommand(value = "get-events", help = "Get all of the events from repository")
    public String getEvents() {
        StringBuilder buf = new StringBuilder();
        for (Event event : repository.getAll()) {
            buf.append(event).append(OsUtils.LINE_SEPARATOR);
        }
        return buf.toString();
    }
}

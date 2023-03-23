package es.um.atica.bus.umubus.domain.events;

import java.util.UUID;

public class EventId {

    private UUID id;

    public EventId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return id.toString();
    }
}
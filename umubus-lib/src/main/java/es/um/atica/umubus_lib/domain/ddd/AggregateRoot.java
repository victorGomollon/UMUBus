package es.um.atica.umubus_lib.domain.ddd;

import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventCollection;

public interface AggregateRoot {
    EventCollection getEvents();
    void addEvent(Event event);
}
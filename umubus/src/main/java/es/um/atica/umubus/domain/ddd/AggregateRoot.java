package es.um.atica.umubus.domain.ddd;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventCollection;

public interface AggregateRoot {
    EventCollection getEvents();
    void addEvent(Event event);
}
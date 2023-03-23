package es.um.atica.bus.umubus.domain.ddd;

import es.um.atica.bus.umubus.domain.events.Event;
import es.um.atica.bus.umubus.domain.events.EventCollection;

public interface AggregateRoot {
    EventCollection getEvents();
    void addEvent(Event event);
}
package es.um.atica.bus.umubus.lib.domain.ddd;

import es.um.atica.bus.umubus.lib.domain.events.Event;
import es.um.atica.bus.umubus.lib.domain.events.EventCollection;

public interface AggregateRoot {
    EventCollection getEvents();
    void addEvent(Event event);
}
package es.um.atica.umubus.domain.model;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventCollection;

public abstract class AggregateRoot {
	
	private EventCollection events = new EventCollection();

	public EventCollection getEvents() { return events; }

    public void addEvent(Event event) { events.add(event); }
}
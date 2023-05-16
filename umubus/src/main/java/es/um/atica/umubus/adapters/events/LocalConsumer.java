package es.um.atica.umubus.adapters.events;

import org.springframework.context.event.EventListener;
import es.um.atica.umubus.domain.events.Event;

public abstract class LocalConsumer<T extends Event> {

	@EventListener
	public void accept(T event) {
		handleEvent(event);
    }
	
	public abstract void handleEvent(T event);

}
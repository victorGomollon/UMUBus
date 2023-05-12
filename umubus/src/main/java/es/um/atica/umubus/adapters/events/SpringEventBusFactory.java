package es.um.atica.umubus.adapters.events;

import org.springframework.context.annotation.Profile;

import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umubus.domain.events.IEventBusFactory;

@Profile("local")
public class SpringEventBusFactory implements IEventBusFactory {

	private SpringEventBus eventBus;
	@Override
	public EventBus getEventBus() {
		if(eventBus == null) {
			return new SpringEventBus();
		}
		return eventBus;
	}

}

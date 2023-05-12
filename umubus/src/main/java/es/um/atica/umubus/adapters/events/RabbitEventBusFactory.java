package es.um.atica.umubus.adapters.events;

import org.springframework.context.annotation.Profile;

import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umubus.domain.events.IEventBusFactory;

@Profile("remoto")
public class RabbitEventBusFactory implements IEventBusFactory {
	
	private RabbitEventBus eventBus;
	@Override
	public EventBus getEventBus() {
		if(eventBus == null) {
			return new RabbitEventBus();
		}
		return eventBus;
	}
}

package es.um.atica.umubus.adapters.events;

import org.springframework.context.annotation.Primary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umubus.adapters.queue.RabbitProcessorEvent;
import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;

@Component
@Primary
public class RabbitEventBus extends RabbitProcessorEvent<Message<Event>> implements EventBus  {

	//Incluye la implementación del bus local (SpringEventBus.java) para la gestión de eventos en local si fuese necesario
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
    @Override
    public void publish(Event event) {
    	event.setMetaData();
    	// Store into queue
        if(!event.isLocal()) {
        	addEvent(event);
        }else {
        	applicationEventPublisher.publishEvent(event);
        }
    }
}

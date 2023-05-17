package es.um.atica.umubus.adapters.events;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;

@Component
public class SpringEventBus implements EventBus {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Override
    public void publish(Event event) {
    	event.setMetaData();
        applicationEventPublisher.publishEvent(event); 
    }

	@Override
	public void publishMessageFB(Event event, String idMessage, Timestamp sendDate) {
		this.publish(event);
	}
}

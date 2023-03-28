package es.um.atica.bus.umubus.lib.adapters.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import es.um.atica.bus.umubus.lib.domain.events.Event;
import es.um.atica.bus.umubus.lib.domain.events.EventBus;

@Component
@Primary
public class SpringEventBus implements EventBus {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Override
    public void publish(Event event) {
        applicationEventPublisher.publishEvent(event);        
    }

}

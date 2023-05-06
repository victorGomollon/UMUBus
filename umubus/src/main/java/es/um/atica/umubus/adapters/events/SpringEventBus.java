package es.um.atica.umubus.adapters.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;

@Component
//@Primary
public class SpringEventBus implements EventBus {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Override
    public void publish(Event event) {
        applicationEventPublisher.publishEvent(event);        
    }

}
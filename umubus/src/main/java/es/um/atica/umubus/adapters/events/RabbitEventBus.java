package es.um.atica.umubus.adapters.events;

import org.springframework.context.annotation.Primary;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umubus.adapters.queue.RabbitProcessorEvent;
import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;

@Component
@Primary
public class RabbitEventBus extends RabbitProcessorEvent<Message<Event>> implements EventBus  {

    @Override
    public void publish(Event event) {
        // Store into queue
        addEvent(event);    
    }
}

package es.um.atica.umubus_lib.adapters.events;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import es.um.atica.umubus_lib.adapters.queue.RabbitProcessorEvent;
import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventBus;

@Component
@Primary
public class RabbitEventBus extends RabbitProcessorEvent<Event> implements EventBus  {

    @Override
    public void publish(Event event) {
        // Store into queue
        addEvent(event);    
    }
}

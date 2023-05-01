package es.um.atica.umubus_lib.adapters.events;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import es.um.atica.umubus_lib.adapters.queue.RabbitProcessorEvent;
import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventBus;
import es.um.atica.umubus_lib.domain.queue.CloudEventMessageUMU;

@Component
@Primary
public class RabbitEventBus extends RabbitProcessorEvent<CloudEventMessageUMU> implements EventBus  {

    @Override
    public void publish(Event event) {
        // Store into queue
        addEvent(event);    
    }
}

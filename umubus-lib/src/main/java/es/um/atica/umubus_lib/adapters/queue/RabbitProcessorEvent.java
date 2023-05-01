package es.um.atica.umubus_lib.adapters.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.queue.CloudEventMessageUMU;
import es.um.atica.umubus_lib.domain.queue.ProcessorEvent;

public class RabbitProcessorEvent<T extends CloudEventMessageUMU> implements ProcessorEvent<CloudEventMessageUMU>{

    private Queue<CloudEventMessageUMU> queue = new ConcurrentLinkedQueue<>();

    @Override
    public CloudEventMessageUMU get() {
        // Send to message broker
        return queue.poll();
    }
    
    public void addEvent(Event event) {
        // Store into queue
    	CloudEventMessageUMU cEMU = CloudEventMessageUMU.of(event);
        queue.add(cEMU);    
    }

}

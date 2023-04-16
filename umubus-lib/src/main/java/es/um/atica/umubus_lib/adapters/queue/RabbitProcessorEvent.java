package es.um.atica.umubus_lib.adapters.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.queue.ProcessorEvent;

public class RabbitProcessorEvent<T extends Event> implements ProcessorEvent<Event>{

    private Queue<Event> queue = new ConcurrentLinkedQueue<>();

    @Override
    public Event get() {
        // Send to message broker
        return queue.poll();
    }
    
    public void addEvent(Event event) {
        // Store into queue
        queue.add(event);    
    }

}

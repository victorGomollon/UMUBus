package es.um.atica.umubus_lib.adapters.queue;

import java.net.URI;
import java.time.Instant;
//import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
//import java.time.ZoneOffset;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.queue.ProcessorEvent;

import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.messaging.Message;

public class RabbitProcessorEvent<T extends Message<Event>> implements ProcessorEvent<Message<Event>>{

    private Queue<Message<Event>> queue = new ConcurrentLinkedQueue<>();

    @Override
    public Message<Event> get() {
        // Send to message broker
        return queue.poll();
    }
    
    public void addEvent(Event event) {
        // Store into queue
    	event.setMetaData();
    	Message<Event> messageEvent =  CloudEventMessageBuilder.withData(event)
    			.setSpecVersion("1.0")
    			.setId(UUID.randomUUID().toString())
    			.setSource(URI.create("http://localhost"))
    			.setType(event.getType())
//				.setTime(LocalDateTime.now().atOffset(ZoneOffset.of("+01:00")))
//				.setTime(OffsetDateTime.now())
				.setTime(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
    			.build();
        queue.add(messageEvent);    
    }

}

package es.um.atica.umubus.adapters.queue;

import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.queue.ProcessorEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.messaging.Message;

public class RabbitProcessorEvent<T extends Message<Event>> implements ProcessorEvent<Message<Event>>{

    private Queue<Message<Event>> queue = new ConcurrentLinkedQueue<>();

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;
    
    @Value("${spring.rabbitmq.port}")
    private String rabbitPort;
    
    @Override
    public Message<Event> get() {
        // Send to message broker
        return queue.poll();
    }
    
    public void addEvent(Event event) {
        // Store into queue
    	Message<Event> messageEvent =  CloudEventMessageBuilder.withData(event)
    			.setSpecVersion("1.0")
    			.setId(UUID.randomUUID().toString())
    			.setSource(URI.create("http://" + rabbitHost + ":" + rabbitPort + "/"))
    			.setType(event.getType())
				.setTime(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
    			.build();
        queue.add(messageEvent);    
    }

}

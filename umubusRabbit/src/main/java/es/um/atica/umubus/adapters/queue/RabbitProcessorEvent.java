package es.um.atica.umubus.adapters.queue;

import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.model.MessageFallBack;
import es.um.atica.umubus.domain.queue.ProcessorEvent;
import es.um.atica.umubus.domain.repository.MessageFallBackReadRepository;
import es.um.atica.umubus.domain.repository.MessageFallBackWriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.messaging.Message;

public class RabbitProcessorEvent<T extends Message<Event>> implements ProcessorEvent<Message<Event>>{

    private Queue<Message<Event>> queue = new ConcurrentLinkedQueue<>();

    @Autowired
    private MessageFallBackReadRepository messageFallBackReadRepository;
    
    @Autowired
    private MessageFallBackWriteRepository messageFallBackWriteRepository;
    
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
    			.setSource(URI.create("http://localhost:8080/"))
    			.setType(event.getType())
				.setTime(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
    			.build();
    	addMessageEvent(messageEvent);
    }
    
    public void addMessageEvent(Message<Event> messageEvent) {
        // Store into queue
        queue.add(messageEvent);
        // Send to BBDD
        messageFallBackReadRepository.findMessage(messageEvent.getHeaders().get("ce-id").toString())
        .ifPresentOrElse(
            (mFB)-> { throw new UnsupportedOperationException(String.format("Ya esta el mensaje %s en la tabla",mFB.getId())); },
            () -> {
            MessageFallBack mFB = MessageFallBack.of(messageEvent.getHeaders().get("ce-id").toString(), new Timestamp(messageEvent.getHeaders().getTimestamp()), messageEvent.getPayload());
            messageFallBackWriteRepository.saveMessageFB(mFB);
            }
        );
    }
}

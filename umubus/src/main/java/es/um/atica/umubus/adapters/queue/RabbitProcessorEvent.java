package es.um.atica.umubus.adapters.queue;

import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;
//import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
//import java.time.ZoneOffset;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.model.MessageFallBack;
import es.um.atica.umubus.domain.queue.ProcessorEvent;
import es.um.atica.umubus.domain.repository.MessageFallBackReadRepository;
import es.um.atica.umubus.domain.repository.MessageFallBackWriteRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.messaging.Message;

public class RabbitProcessorEvent<T extends Message<Event>> implements ProcessorEvent<Message<Event>>{

    private Queue<Message<Event>> queue = new ConcurrentLinkedQueue<>();
    private Message<Event> proccess;
    
    @Autowired
    private MessageFallBackReadRepository messageFallBackReadRepository;
    @Autowired
    private MessageFallBackWriteRepository messageFallBackWriteRepository;
    
    @Override
    @CircuitBreaker(name = "UMUBUSCircuitBreaker", fallbackMethod = "getFallBack")
    public Message<Event> get() {
        // Send to message broker
    	Iterable<MessageFallBack> listMFB = messageFallBackReadRepository.findAllMessageFB();
    	if(listMFB.iterator().hasNext()) {//TODO: Como hacer esto idempotente
//    		listMFB.forEach( mFB -> {
//    			queue.add(mFB.getMessage());		//TODO: la idea es que estos los meta y procese primero...Creo que no esta muy bien esta definicios...Seria mejor procesar directamente el mensaje que hay en BBDD en este momento y listo, es decir que coja este en vez de el de la cola.
//    			messageFallBackWriteRepository.deleteMessageFB(mFB);
//    		});
    		MessageFallBack mFB = listMFB.iterator().next();
    		this.proccess = mFB.getMessage();
    		messageFallBackWriteRepository.deleteMessageFB(mFB);
    	} else {
    		this.proccess = queue.poll();
    	}
        return this.proccess;
    }
    
    public void addEvent(Event event) {
        // Store into queue
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
    
    public Message<Event> getFallBack(Exception ex) {
        // Send to message broker
    	messageFallBackReadRepository.findMessage(proccess.getHeaders().getId().toString())
        .ifPresentOrElse(
            (mFB)-> { throw new UnsupportedOperationException(String.format("Ya esta el mensaje %s en la tabla",mFB.getId())); },
            () -> {
            	MessageFallBack mFB = MessageFallBack.of(proccess.getHeaders().getId().toString(), proccess.getHeaders().getId().toString(), new Timestamp(proccess.getHeaders().getTimestamp()), proccess.getPayload(), proccess, ex.getClass().getName());
            	messageFallBackWriteRepository.saveMessageFB(mFB);
            }
        );
    	return proccess;
    }

}

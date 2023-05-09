package es.um.atica.umubus.adapters.events;

import org.springframework.context.annotation.Primary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umubus.adapters.queue.RabbitProcessorEvent;
import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;

@Component
@Primary
public class RabbitEventBus extends RabbitProcessorEvent<Message<Event>> implements EventBus  {

	//Incluye la implementación del bus loca (SpringEventBus.java) para la gestión de eventos en local si fuese necesario
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
	@Value("${umubus.rabbit.active}")
	private boolean isRabbitActive;
	
    @Override
    public void publish(Event event) {
    	event.setMetaData();
    	// Store into queue
        if(isRabbitActive) {
        	addEvent(event);
        }else {
//        	Message<Event> messageEvent =  CloudEventMessageBuilder.withData(event)
//        			.setSpecVersion("1.0")
//        			.setId(UUID.randomUUID().toString())
//        			.setSource(URI.create("http://localhost"))
//        			.setType(event.getType())
////    				.setTime(LocalDateTime.now().atOffset(ZoneOffset.of("+01:00")))
////    				.setTime(OffsetDateTime.now())
//    				.setTime(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
//        			.build();
//        	applicationEventPublisher.publishEvent(messageEvent);
        	applicationEventPublisher.publishEvent(event);
        }
    }
}

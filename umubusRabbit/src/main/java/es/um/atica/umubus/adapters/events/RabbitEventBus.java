package es.um.atica.umubus.adapters.events;

import org.springframework.context.annotation.Primary;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umubus.adapters.queue.RabbitProcessorEvent;
import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;
import java.sql.Timestamp;

@Component
@Primary
public class RabbitEventBus extends RabbitProcessorEvent<Message<Event>> implements EventBus  {

	//Incluye la implementación del bus local (SpringEventBus.java) para la gestión de eventos en local si fuese necesario
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
    @Override
    public void publish(Event event) {
    	event.setMetaData();
    	// Store into queue
        if(!event.isLocal()) {
        	addEvent(event);
        }else {
        	applicationEventPublisher.publishEvent(event);
        }
    }
    
    @Override
    public void publishMessageFB(Event event, String idMessage, Timestamp sendDate) {
    	Message<Event> messageEvent =  CloudEventMessageBuilder.withData(event)
    			.setSpecVersion("1.0")
    			.setId(idMessage)
    			.setSource(URI.create("http://localhost:8080/"))
    			.setType(event.getType())
				.setTime(OffsetDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault()))
    			.build();
    	addMessageEvent(messageEvent);
    }
}

package es.um.atica.umubus.adapters.events;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.repository.MessageFallBackWriteRepository;

@Component
public class EventAckConsumer implements Consumer<Message<Event>> {

	@Autowired
    private MessageFallBackWriteRepository messageFallBackWriteRepository;
	
    @Override
    public void accept(Message<Event> message) {
    	try {
    		messageFallBackWriteRepository.deleteMessageFBById(message.getHeaders().get("ce-id").toString());
    	}catch(EmptyResultDataAccessException e) {
    		System.err.println("No existe el mensaje en BBDD " + e);
    	}
    }
}

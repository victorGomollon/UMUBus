package es.um.atica.umuexample.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent;
import lombok.extern.log4j.Log4j2;
import es.um.atica.umubus.domain.events.Event;

@Log4j2
@Component
public class CrearUsuarioConsumer implements Consumer<Message<CrearUsuarioEvent>> {

	@Override
    public void accept(Message<CrearUsuarioEvent> message) {
    	Event event = message.getPayload();
    	log.info(">>>EVENTO USER CREATED Consumer: " + event.getMetaData().get("userId"));
    }
}

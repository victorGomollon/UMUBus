package es.um.atica.umuexample.usuarios.adapters.events;

import java.util.function.Consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.CrearUsuarioEvent;
import es.um.atica.umubus.domain.events.Event;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CrearUsuarioConsumer implements Consumer<Message<CrearUsuarioEvent>> {

	@Override
    public void accept(Message<CrearUsuarioEvent> message) {
    	Event event = message.getPayload();
    	log.info(">>>EVENTO USUARIO CREATED Consumer: " + event);
    }
}

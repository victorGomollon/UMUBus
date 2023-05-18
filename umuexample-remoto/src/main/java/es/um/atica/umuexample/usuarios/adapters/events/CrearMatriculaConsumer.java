package es.um.atica.umuexample.usuarios.adapters.events;

import java.util.function.Consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import es.um.atica.umubus.domain.events.Event;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CrearMatriculaConsumer implements Consumer<Message<Event>> {

	@Override
    public void accept(Message<Event> message) {
    	Event event = message.getPayload();
    	log.info(">>>EVENTO MATRICULA CREATED Consumer: " + event);
    	log.info(">>>EVENTO MATRICULA CREATED Consumer para la asignatura: " + event.getMetaData().get("asignatura"));
    }
}
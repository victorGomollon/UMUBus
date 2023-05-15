package es.um.atica.umuexample.usuarios.adapters.events;

import java.util.function.Consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import es.um.atica.umubus.domain.events.Event;

@Component
public class CrearMatriculaConsumer implements Consumer<Message<Event>> {

	@Override
    public void accept(Message<Event> message) {
    	Event event = message.getPayload();
        System.err.println(">>>EVENTO MATRICULA CREATED Consumer: " + event);
        System.err.println(">>>EVENTO MATRICULA CREATED Consumer para la asignatura: " + event.getMetaData().get("asignatura"));
    }
}
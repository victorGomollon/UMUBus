package es.um.atica.umuexample.usuarios.adapters.events;

import java.util.function.Consumer;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.CrearUsuarioEvent;
import es.um.atica.umubus.domain.events.Event;

@Component
public class CrearUsuarioConsumer implements Consumer<Message<CrearUsuarioEvent>> {

	@Override
    public void accept(Message<CrearUsuarioEvent> message) {
    	Event event = message.getPayload();
        System.err.println(">>>EVENTO USUARIO CREATED Consumer: " + event);
    }
	
	@EventListener
	public void accept(CrearUsuarioEvent event) {
        System.err.println(">>>EVENTO USUARIO CREATED eventListener: " + event);
    }
}

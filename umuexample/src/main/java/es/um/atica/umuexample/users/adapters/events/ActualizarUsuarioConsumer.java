package es.um.atica.umuexample.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.event.ActualizarUsuarioEvent;

@Component
public class ActualizarUsuarioConsumer implements Consumer<Message<ActualizarUsuarioEvent>> {

    @Override
    public void accept(Message<ActualizarUsuarioEvent> message) {
    	ActualizarUsuarioEvent uE = message.getPayload();
        System.err.println(">>>EVENTO USER UPDATE Consumer: " + uE);
    }
	
	@EventListener
	public void accept(ActualizarUsuarioEvent event) {
		System.err.println(">>>EVENTO USER UPDATE eventListener: " + event);
    }

}
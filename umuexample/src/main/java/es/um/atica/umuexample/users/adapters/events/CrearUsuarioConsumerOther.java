package es.um.atica.umuexample.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent;

@Component
public class CrearUsuarioConsumerOther implements Consumer<Message<CrearUsuarioEvent>> {

    @Override
    public void accept(Message<CrearUsuarioEvent> message) {
    	CrearUsuarioEvent cUE = message.getPayload();
        System.err.println(">>>EVENTO USER CREATED OTHER Consumer: " + cUE);
    }
	
	@EventListener
	public void accept(CrearUsuarioEvent event) {
		System.err.println(">>>EVENTO USER CREATED OTHER eventListener: " + event);
    }
}

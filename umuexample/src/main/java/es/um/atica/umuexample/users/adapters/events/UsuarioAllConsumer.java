package es.um.atica.umuexample.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.event.UsuarioEvent;

@Component
public class UsuarioAllConsumer implements Consumer<Message<UsuarioEvent>> {

    @Override
    public void accept(Message<UsuarioEvent> message) {
    	UsuarioEvent uE = message.getPayload();
        System.err.println(">>>EVENTO USER ????: " + uE);
    }
	
	@EventListener
	public void accept(UsuarioEvent event) {
		System.err.println(">>>EVENTO USER ????: " + event);
    }

}

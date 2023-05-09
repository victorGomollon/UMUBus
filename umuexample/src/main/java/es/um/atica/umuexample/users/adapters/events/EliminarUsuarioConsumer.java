package es.um.atica.umuexample.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.event.EliminarUsuarioEvent;

@Component
public class EliminarUsuarioConsumer implements Consumer<Message<EliminarUsuarioEvent>> {

    @Override
    public void accept(Message<EliminarUsuarioEvent> message) {
    	EliminarUsuarioEvent eUE = message.getPayload();
        System.err.println(">>>EVENTO USER DELETED: " + eUE);
    }
	
	@EventListener
	public void accept(EliminarUsuarioEvent event) {
		System.err.println(">>>EVENTO USER DELETED: " + event);
    }
	
}

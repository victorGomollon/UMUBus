package es.um.atica.umuexample.usuarios.adapters.events;

import java.util.function.Consumer;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.EliminarUsuarioEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EliminarUsuarioConsumer implements Consumer<Message<EliminarUsuarioEvent>> {

    @Override
    public void accept(Message<EliminarUsuarioEvent> message) {
    	EliminarUsuarioEvent eUE = message.getPayload();
    	log.info(">>>EVENTO USUARIO DELETED Consumer: " + eUE);
    }
	
}

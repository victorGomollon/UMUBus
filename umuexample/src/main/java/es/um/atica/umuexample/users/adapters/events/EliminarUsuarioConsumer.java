package es.um.atica.umuexample.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.event.EliminarUsuarioEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EliminarUsuarioConsumer implements Consumer<Message<EliminarUsuarioEvent>> {

    @Override
    public void accept(Message<EliminarUsuarioEvent> message) {
    	EliminarUsuarioEvent eUE = message.getPayload();
    	log.info(">>>EVENTO USER DELETED Consumer: " + eUE);
    }
}

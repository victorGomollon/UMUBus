package es.um.atica.umuexample.usuarios.adapters.events;

import java.util.function.Consumer;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.CrearUsuarioEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CrearUsuarioConsumerOther implements Consumer<Message<CrearUsuarioEvent>> {

    @Override
    public void accept(Message<CrearUsuarioEvent> message) {
    	CrearUsuarioEvent cUE = message.getPayload();
    	log.info(">>>EVENTO USUARIO CREATED OTHER Consumer: " + cUE);
    }
}

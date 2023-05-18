package es.um.atica.umuexample.usuarios.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.CrearUsuarioEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CrearUsuarioConsumerOther {

	@EventListener
	public void accept(CrearUsuarioEvent event) {
		log.info(">>>EVENTO USUARIO CREATED OTHER eventListener: " + event);
    }
}

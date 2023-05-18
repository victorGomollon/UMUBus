package es.um.atica.umuexample.usuarios.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.ActualizarUsuarioEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ActualizarUsuarioConsumer {

	@EventListener
	public void accept(ActualizarUsuarioEvent event) {
		log.info(">>>EVENTO USUARIO UPDATE eventListener: " + event);
    }

}
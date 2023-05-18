package es.um.atica.umuexample.users.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.event.ActualizarUsuarioEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ActualizarUsuarioConsumer {

	@EventListener
	public void accept(ActualizarUsuarioEvent event) {
		log.info(">>>EVENTO USER UPDATE eventListener: " + event);
    }

}
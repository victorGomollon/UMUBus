package es.um.atica.umuexample.usuarios.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.EliminarUsuarioEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EliminarUsuarioConsumer {

	@EventListener
	public void accept(EliminarUsuarioEvent event) {
		log.info(">>>EVENTO USUARIO DELETED eventListener: " + event);
    }
	
}

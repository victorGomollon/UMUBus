package es.um.atica.umuexample.usuarios.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.EliminarUsuarioEvent;

@Component
public class EliminarUsuarioConsumer {

	@EventListener
	public void accept(EliminarUsuarioEvent event) {
		System.err.println(">>>EVENTO USUARIO DELETED eventListener: " + event);
    }
	
}

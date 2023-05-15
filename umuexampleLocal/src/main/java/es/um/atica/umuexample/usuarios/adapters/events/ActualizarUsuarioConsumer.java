package es.um.atica.umuexample.usuarios.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.event.ActualizarUsuarioEvent;

@Component
public class ActualizarUsuarioConsumer {

	@EventListener
	public void accept(ActualizarUsuarioEvent event) {
		System.err.println(">>>EVENTO USUARIO UPDATE eventListener: " + event);
    }

}
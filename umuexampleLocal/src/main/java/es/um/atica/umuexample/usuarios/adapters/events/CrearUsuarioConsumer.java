package es.um.atica.umuexample.usuarios.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import es.um.atica.umuexample.usuarios.domain.event.CrearUsuarioEvent;

@Component
public class CrearUsuarioConsumer {
	
	@EventListener
	public void accept(CrearUsuarioEvent event) {
        System.err.println(">>>EVENTO USUARIO CREATED eventListener: " + event);
    }
}

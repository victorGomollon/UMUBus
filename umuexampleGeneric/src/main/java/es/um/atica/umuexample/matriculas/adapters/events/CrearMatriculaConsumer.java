package es.um.atica.umuexample.matriculas.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.domain.event.CrearMatriculaEvent;
@Component
public class CrearMatriculaConsumer {

	
    @EventListener
    public void accept(CrearMatriculaEvent event) {
        System.err.println(">>>EVENTO MATRICULA CREATED Consumer: " + event);
    }
}

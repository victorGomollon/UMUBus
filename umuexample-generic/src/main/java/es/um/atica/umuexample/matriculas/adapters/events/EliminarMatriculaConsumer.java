package es.um.atica.umuexample.matriculas.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.domain.event.EliminarMatriculaEvent;

@Component
public class EliminarMatriculaConsumer {
	
    @EventListener
    public void accept(EliminarMatriculaEvent event) {
        System.err.println(">>>EVENTO MATRICULA DELETED Consumer: " + event);
    }
}

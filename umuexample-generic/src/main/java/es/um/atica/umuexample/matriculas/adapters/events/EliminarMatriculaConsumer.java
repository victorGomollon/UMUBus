package es.um.atica.umuexample.matriculas.adapters.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.domain.event.EliminarMatriculaEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EliminarMatriculaConsumer {
	
    @EventListener
    public void accept(EliminarMatriculaEvent event) {
        log.info(">>>EVENTO MATRICULA DELETED Consumer: " + event);
    }
}

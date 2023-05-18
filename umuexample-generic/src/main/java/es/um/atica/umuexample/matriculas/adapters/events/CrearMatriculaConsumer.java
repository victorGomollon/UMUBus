package es.um.atica.umuexample.matriculas.adapters.events;

import java.util.function.Consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.domain.event.CrearMatriculaEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CrearMatriculaConsumer implements Consumer<Message<CrearMatriculaEvent>> {

	@Override
	public void accept(Message<CrearMatriculaEvent> message) {
		CrearMatriculaEvent event = message.getPayload();
		log.info(">>>EVENTO MATRICULA CREATED Consumer: " + event);
	}
}

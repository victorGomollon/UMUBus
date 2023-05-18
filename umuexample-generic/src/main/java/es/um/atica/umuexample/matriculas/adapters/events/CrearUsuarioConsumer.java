package es.um.atica.umuexample.matriculas.adapters.events;

import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.application.command.CrearMatriculaCommand;
import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umubus.domain.cqrs.SyncCommandBus;
import es.um.atica.umubus.domain.events.Event;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CrearUsuarioConsumer implements Consumer<Message<Event>> {

    @Autowired
    private SyncCommandBus syncCommandBus;
	
	@Override
    public void accept(Message<Event> message) {
    	Event event = message.getPayload();
    	log.info(">>>EVENTO USUARIO CREATED Consumer: " + event.getMetaData().get("usuarioId"));
        Matricula mat = Matricula.of(UUID.randomUUID().toString(), "Matricula a traves de usuario", "SSOO", event.getMetaData().get("usuarioId").toString());
        try {
			syncCommandBus.handle(CrearMatriculaCommand.of(mat.getId(), mat.getName(), mat.getAsignatura(), mat.getUsuario()));
		} catch (Exception e) {
			e.printStackTrace();
		}
        log.info(">>>Se ha creado la matricula Consumer: " + mat);
    }
}

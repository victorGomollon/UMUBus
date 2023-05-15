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

@Component
public class CrearUsuarioConsumer implements Consumer<Message<Event>> {

    @Autowired
    private SyncCommandBus syncCommandBus;
	
	@Override
    public void accept(Message<Event> message) {
    	Event event = message.getPayload();
        System.err.println(">>>EVENTO USUARIO CREATED Consumer: " + event.getMetaData().get("usuarioId"));
        Matricula mat = Matricula.of(UUID.randomUUID().toString(), "Matricula a traves de usuario", "SSOO", event.getMetaData().get("usuarioId").toString());
        try {
			syncCommandBus.handle(CrearMatriculaCommand.of(mat.getId(), mat.getName(), mat.getAsignatura(), mat.getUsuario()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.err.println(">>>Se ha creado la matricula Consumer: " + mat);
    }
}

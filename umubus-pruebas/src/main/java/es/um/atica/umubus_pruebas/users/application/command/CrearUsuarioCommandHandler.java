package es.um.atica.umubus_pruebas.users.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umubus_pruebas.users.domain.factory.UsuarioFactory;
import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_pruebas.users.domain.model.UsuarioId;
import es.um.atica.umubus_pruebas.users.domain.repository.UsuarioReadRepository;
import es.um.atica.umubus_pruebas.users.domain.repository.UsuarioWriteRepository;
import es.um.atica.umubus_lib.domain.cqrs.SyncCommandHandler;
import es.um.atica.umubus_lib.domain.events.EventBus;

@Component
public class CrearUsuarioCommandHandler implements SyncCommandHandler<Void,CrearUsuarioCommand>{

    @Autowired
    private UsuarioWriteRepository usersWriteRepository;

    @Autowired
    private UsuarioReadRepository usersReadRepository;


    @Autowired
    private EventBus eventBus;

    @Override
    public Void handle(CrearUsuarioCommand command) {
        // Idempotency
        usersReadRepository.findUser(command.getId())
            .ifPresentOrElse(
                (u)-> { throw new UnsupportedOperationException(String.format("Usuario ya creado %s",u.getId().getValue())); },
                () -> {
                    Usuario usr = UsuarioFactory
                        .createUser(
                            UsuarioId.of(command.getId()),
                            command.getName(),
                            command.getAge());
                    usr.createUser();
                    usersWriteRepository.saveUser(usr);
                    eventBus.publish(usr);
                }
            );
        return null;        
    }

}

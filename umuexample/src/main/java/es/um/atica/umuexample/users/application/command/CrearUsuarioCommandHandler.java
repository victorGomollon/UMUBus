package es.um.atica.umuexample.users.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.factory.UsuarioFactory;
import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umuexample.users.domain.repository.UsuarioReadRepository;
import es.um.atica.umuexample.users.domain.repository.UsuarioWriteRepository;
import es.um.atica.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umubus.domain.events.EventBus;

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
                (u)-> { throw new UnsupportedOperationException(String.format("Usuario ya creado %s",u.getId())); },
                () -> {
                    Usuario usr = UsuarioFactory
                        .createUser(
                            command.getId(),
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

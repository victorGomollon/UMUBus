package es.um.atica.umuexample.usuarios.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.factory.UsuarioFactory;
import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioReadRepository;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioWriteRepository;
import es.um.atica.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umubus.domain.events.EventBus;

@Component
public class CrearUsuarioCommandHandler implements SyncCommandHandler<Void,CrearUsuarioCommand>{

    @Autowired
    private UsuarioWriteRepository usuariosWriteRepository;

    @Autowired
    private UsuarioReadRepository usuariosReadRepository;


    @Autowired
    private EventBus eventBus;

    @Override
    public Void handle(CrearUsuarioCommand command) {
        // Idempotency
    	usuariosReadRepository.findUsuario(command.getId())
            .ifPresentOrElse(
                (u)-> { throw new UnsupportedOperationException(String.format("Usuario ya creado %s",u.getId())); },
                () -> {
                    Usuario usr = UsuarioFactory
                        .createUsuario(
                            command.getId(),
                            command.getName(),
                            command.getAge());
                    usr.createUsuario();
                    usuariosWriteRepository.saveUsuario(usr);
                    eventBus.publish(usr);
                }
            );
        return null;        
    }

}

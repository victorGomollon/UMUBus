package es.um.atica.umuexample.usuarios.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.repository.UsuarioReadRepository;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioWriteRepository;
import es.um.atica.umubus.domain.cqrs.CommandHandler;
import es.um.atica.umubus.domain.events.EventBus;

@Component
public class EliminarUsuarioCommandHandler implements CommandHandler<EliminarUsuarioCommand>{

    @Autowired
    private UsuarioReadRepository usuariosReadRepository;

    @Autowired
    private UsuarioWriteRepository usuariosWriteRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public void handle(EliminarUsuarioCommand command) {
        // Idempotency
    	usuariosReadRepository.findUsuario(command.getId()).ifPresent((u)->{
            u.deleteUsuario();
            usuariosWriteRepository.deleteUsuario(u);
            eventBus.publish(u);
        });
    }
    
}

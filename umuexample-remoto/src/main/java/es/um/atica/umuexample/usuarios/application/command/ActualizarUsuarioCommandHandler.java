package es.um.atica.umuexample.usuarios.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.repository.UsuarioReadRepository;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioWriteRepository;
import es.um.atica.umubus.domain.cqrs.CommandHandler;
import es.um.atica.umubus.domain.events.EventBus;

@Component
public class ActualizarUsuarioCommandHandler implements CommandHandler<ActualizarUsuarioCommand>{

    @Autowired
    private UsuarioReadRepository usuariosReadRepository;

    @Autowired
    private UsuarioWriteRepository usuariosWriteRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public void handle(ActualizarUsuarioCommand command) {
        // Idempotency
    	usuariosReadRepository.findUsuario(command.getId()).ifPresent((u)->{
            if (command.getName().isPresent()) u.updateUsuarioName(command.getName().get());
            if (command.getAge().isPresent()) u.updateUsuarioAge(command.getAge().get());
            usuariosWriteRepository.saveUsuario(u);
            eventBus.publish(u);
        });
    }
    
}

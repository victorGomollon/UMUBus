package es.um.atica.umuexample.users.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.repository.UsuarioReadRepository;
import es.um.atica.umuexample.users.domain.repository.UsuarioWriteRepository;
import es.um.atica.umubus.domain.cqrs.CommandHandler;
import es.um.atica.umubus.domain.events.IEventBusFactory;

@Component
public class ActualizarUsuarioCommandHandler implements CommandHandler<ActualizarUsuarioCommand>{

    @Autowired
    private UsuarioReadRepository usersReadRepository;

    @Autowired
    private UsuarioWriteRepository usersWriteRepository;

    @Autowired
    private IEventBusFactory eventBusFactory;

    @Override
    public void handle(ActualizarUsuarioCommand command) {
        // Idempotency
        usersReadRepository.findUser(command.getId()).ifPresent((u)->{
            if (command.getName().isPresent()) u.updateUserName(command.getName().get());
            if (command.getAge().isPresent()) u.updateUserAge(command.getAge().get());
            usersWriteRepository.saveUser(u);
            eventBusFactory.getEventBus().publish(u);
        });
    }
    
}

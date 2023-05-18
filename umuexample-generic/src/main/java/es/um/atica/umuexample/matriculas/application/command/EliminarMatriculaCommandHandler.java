package es.um.atica.umuexample.matriculas.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.domain.repository.MatriculaReadRepository;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaWriteRepository;
import es.um.atica.umubus.domain.cqrs.CommandHandler;
import es.um.atica.umubus.domain.events.EventBus;

@Component
public class EliminarMatriculaCommandHandler implements CommandHandler<EliminarMatriculaCommand>{

    @Autowired
    private MatriculaReadRepository matriculasReadRepository;

    @Autowired
    private MatriculaWriteRepository matriculasWriteRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public void handle(EliminarMatriculaCommand command) {
        // Idempotency
    	matriculasReadRepository.findMatricula(command.getId()).ifPresent((u)->{
            u.deleteMatricula();
            matriculasWriteRepository.deleteMatricula(u);
            eventBus.publish(u);
        });
    }
    
}

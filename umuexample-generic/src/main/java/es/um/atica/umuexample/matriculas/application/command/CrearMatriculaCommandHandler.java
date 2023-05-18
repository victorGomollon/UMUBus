package es.um.atica.umuexample.matriculas.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaReadRepository;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaWriteRepository;
import es.um.atica.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umubus.domain.events.EventBus;

@Component
public class CrearMatriculaCommandHandler implements SyncCommandHandler<Void,CrearMatriculaCommand>{

    @Autowired
    private MatriculaWriteRepository matriculasWriteRepository;

    @Autowired
    private MatriculaReadRepository matriculasReadRepository;


    @Autowired
    private EventBus eventBus;

    @Override
    public Void handle(CrearMatriculaCommand command) {
        // Idempotency
    	matriculasReadRepository.findMatricula(command.getId())
            .ifPresentOrElse(
                (u)-> { throw new UnsupportedOperationException(String.format("Matricula ya creado %s",u.getId())); },
                () -> {
                    Matricula mat = Matricula
                        .of(
                            command.getId(),
                            command.getName(),
                            command.getAsignatura(),
                            command.getUsuario());
                    mat.createMatricula();
                    matriculasWriteRepository.saveMatricula(mat);
                    eventBus.publish(mat);
                }
            );
        return null;        
    }

}

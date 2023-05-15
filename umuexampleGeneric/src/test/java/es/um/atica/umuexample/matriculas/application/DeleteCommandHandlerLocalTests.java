package es.um.atica.umuexample.matriculas.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umuexample.SpringConfigurationTest;
import es.um.atica.umuexample.matriculas.application.command.EliminarMatriculaCommand;
import es.um.atica.umuexample.matriculas.application.command.EliminarMatriculaCommandHandler;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaWriteRepository;
import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umuexample.matriculas.domain.event.EliminarMatriculaEvent;

@SpringBootTest
class DeleteCommandHandlerLocalTests extends SpringConfigurationTest{
    
    private static final String ID_MATRICULA = "10497182-c376-11ed-afa1-0242ac220002";
    private static final String ID_MATRICULA_INEXISTENTE = "10497082-c376-11ed-afa1-0242ac120555";

    @Autowired
    EliminarMatriculaCommandHandler eliminarMatriculaCommandHandler;

    @SpyBean
    MatriculaWriteRepository matriculaWriteRepository;

    @SpyBean
    private EventBus eventBus;

    @Test
    void eliminar_matricula_existente() throws Exception {
        // Dado un comando delete con id correcto
        EliminarMatriculaCommand del = EliminarMatriculaCommand.of(ID_MATRICULA);
        // Cuando se lanza el comando
        eliminarMatriculaCommandHandler.handle(del);
        // Entonces se elimina el matricula
        ArgumentCaptor<Matricula> matricula = ArgumentCaptor.forClass(Matricula.class);
        Mockito.verify(matriculaWriteRepository).deleteMatricula(matricula.capture());
        // Y tiene el id esperado
        assertEquals(ID_MATRICULA, matricula.getValue().getId());
        // Y se lanza el evento
        ArgumentCaptor<EliminarMatriculaEvent> event = ArgumentCaptor.forClass(EliminarMatriculaEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_MATRICULA, event.getValue().getMetaData().get("matriculaId"));
    }

    @Test
    void eliminar_matricula_inexistente() throws Exception {
        // Dado un comando delete con id inexistente
        EliminarMatriculaCommand del = EliminarMatriculaCommand.of(ID_MATRICULA_INEXISTENTE);
        // Cuando se lanza el comando
        eliminarMatriculaCommandHandler.handle(del);
        // Entonces no se elimina ningún matricula
        ArgumentCaptor<Matricula> matricula = ArgumentCaptor.forClass(Matricula.class);
        Mockito.verify(matriculaWriteRepository,never()).deleteMatricula(matricula.capture());
        // Y tiene el id esperado
        assertEquals(0, matricula.getAllValues().size());
        // Y no se lanza ningún evento
        ArgumentCaptor<EliminarMatriculaEvent> event = ArgumentCaptor.forClass(EliminarMatriculaEvent.class);
        Mockito.verify(eventBus,never()).publish(event.capture());
        assertEquals(0, event.getAllValues().size());
    }

}


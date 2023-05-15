package es.um.atica.umuexample.matriculas.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umuexample.SpringConfigurationTest;
import es.um.atica.umuexample.matriculas.application.command.CrearMatriculaCommand;
import es.um.atica.umuexample.matriculas.application.command.CrearMatriculaCommandHandler;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaWriteRepository;
import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umuexample.matriculas.domain.event.CrearMatriculaEvent;

@SpringBootTest
class CreateCommandHandlerLocalTests extends SpringConfigurationTest{
    
    private static final String ID_MATRICULA = "10497182-c376-11ed-afa1-0242ac220002";
    private static final String ID_MATRICULA_INEXISTENTE = "10497182-c376-11ed-afa1-0242ac120555";

    @Autowired
    CrearMatriculaCommandHandler crearMatriculaCommandHandler;

    @SpyBean
    MatriculaWriteRepository matriculaWriteRepository;

    @SpyBean
    private EventBus eventBus;

    @Test
    void crear_matricula_ok() throws Exception {
        // Dado un comando crear con id nuevo
        CrearMatriculaCommand add = CrearMatriculaCommand.of(ID_MATRICULA_INEXISTENTE, "TestCrear", "Telematica", "30497182-c376-11ed-afa1-0242ac220002");
        // Cuando se lanza el comando
        crearMatriculaCommandHandler.handle(add);
        // Entonces se crea el matricula
        ArgumentCaptor<Matricula> matricula = ArgumentCaptor.forClass(Matricula.class);
        Mockito.verify(matriculaWriteRepository).saveMatricula(matricula.capture());
        // Y tiene el id esperado
        assertEquals(ID_MATRICULA_INEXISTENTE, matricula.getValue().getId());
        // Y se lanza el evento
        ArgumentCaptor<CrearMatriculaEvent> event = ArgumentCaptor.forClass(CrearMatriculaEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_MATRICULA_INEXISTENTE, event.getValue().getMetaData().get("matriculaId"));
    }

    @Test
    void crear_matricula_ya_existe() throws Exception {
        // Dado un comando crear con id existente
    	CrearMatriculaCommand add = CrearMatriculaCommand.of(ID_MATRICULA, "TestMatricula 1", "SSOO", "30497182-c376-11ed-afa1-0242ac220002");
        // Cuando se lanza el comando
    	assertThrows(UnsupportedOperationException.class, () ->{ crearMatriculaCommandHandler.handle(add); });
        // Entonces no se crea ningún matricula
        ArgumentCaptor<Matricula> matricula = ArgumentCaptor.forClass(Matricula.class);
        Mockito.verify(matriculaWriteRepository,never()).saveMatricula(matricula.capture());
        // Y tiene el id esperado
        assertEquals(0, matricula.getAllValues().size());
        // Y no se lanza ningún evento
        ArgumentCaptor<CrearMatriculaEvent> event = ArgumentCaptor.forClass(CrearMatriculaEvent.class);
        Mockito.verify(eventBus,never()).publish(event.capture());
        assertEquals(0, event.getAllValues().size());
    }

}


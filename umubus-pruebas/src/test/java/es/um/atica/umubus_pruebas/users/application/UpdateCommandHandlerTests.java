package es.um.atica.umubus_pruebas.users.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import es.um.atica.umubus_lib.domain.events.EventBus;
import es.um.atica.umubus_pruebas.users.application.command.ActualizarUsuarioCommand;
import es.um.atica.umubus_pruebas.users.application.command.ActualizarUsuarioCommandHandler;
import es.um.atica.umubus_pruebas.users.domain.repository.UsuarioWriteRepository;
import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_pruebas.users.domain.event.ActualizarUsuarioEvent;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class UpdateCommandHandlerTests {
    
    private static final String ID_USER = "30497182-c376-11ed-afa1-0242ac120002";
    private static final String ID_USER_NAME = "Pepe Lopez";
    private static final String ID_NOT_EXISTING_USER = "30497182-c376-11ed-afa1-0242ac120555";

    @Autowired
    ActualizarUsuarioCommandHandler updateUserCommandHandler;

    @SpyBean
    UsuarioWriteRepository usersWriteRepository;

    @SpyBean
    private EventBus eventBus;

    @Test
    public void actualizar_usuario_datos_correctos() throws Exception {
        // Dado un comando update con id y nombre correcto
        ActualizarUsuarioCommand upd = ActualizarUsuarioCommand.of(ID_USER,Optional.of(ID_USER_NAME),Optional.empty());
        // Cuando se lanza el comando
        updateUserCommandHandler.handle(upd);
        // Entonces se actualiza el usuario
        ArgumentCaptor<Usuario> user = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usersWriteRepository).saveUser(user.capture());
        // Y tiene el id y name esperado
        assertEquals(ID_USER, user.getValue().getId());
        assertEquals(ID_USER_NAME, user.getValue().getName());
        // Y se lanza el evento
        ArgumentCaptor<ActualizarUsuarioEvent> event = ArgumentCaptor.forClass(ActualizarUsuarioEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_USER, event.getValue().getAggregateId());
    }

    @Test
    public void actualizar_usuario_inexistente() throws Exception {
        // Dado un comando update con id inexistente y nombre correcto
        ActualizarUsuarioCommand upd = ActualizarUsuarioCommand.of(ID_NOT_EXISTING_USER,Optional.of(ID_USER_NAME),Optional.empty());
        // Cuando se lanza el comando
        updateUserCommandHandler.handle(upd);
        // Entonces no se actualiza el usuario
        ArgumentCaptor<Usuario> user = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usersWriteRepository,never()).saveUser(user.capture());
        // Y tiene el id y name esperado
        assertEquals(0, user.getAllValues().size());
        // Y no se lanza el evento
        ArgumentCaptor<ActualizarUsuarioEvent> event = ArgumentCaptor.forClass(ActualizarUsuarioEvent.class);
        Mockito.verify(eventBus,never()).publish(event.capture());
        assertEquals(0, event.getAllValues().size());
    }

}


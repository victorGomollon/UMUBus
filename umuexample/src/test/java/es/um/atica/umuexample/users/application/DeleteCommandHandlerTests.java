package es.um.atica.umuexample.users.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umuexample.users.application.command.EliminarUsuarioCommand;
import es.um.atica.umuexample.users.application.command.EliminarUsuarioCommandHandler;
import es.um.atica.umuexample.users.domain.repository.UsuarioWriteRepository;
import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umuexample.users.domain.event.EliminarUsuarioEvent;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class DeleteCommandHandlerTests {
    
    private static final String ID_USER = "30497182-c376-11ed-afa1-0242ac220002";
    private static final String ID_NOT_EXISTING_USER = "30497182-c376-11ed-afa1-0242ac120555";

    @Autowired
    EliminarUsuarioCommandHandler deleteUserCommandHandler;

    @SpyBean
    UsuarioWriteRepository usersWriteRepository;

    @SpyBean
    private EventBus eventBus;

    @Test
    public void eliminar_usuario_datos_correctos() throws Exception {
        // Dado un comando delete con id correcto
        EliminarUsuarioCommand del = EliminarUsuarioCommand.of(ID_USER);
        // Cuando se lanza el comando
        deleteUserCommandHandler.handle(del);
        // Entonces se elimina el usuario
        ArgumentCaptor<Usuario> user = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usersWriteRepository).deleteUser(user.capture());
        // Y tiene el id esperado
        assertEquals(ID_USER, user.getValue().getId());
        // Y se lanza el evento
        ArgumentCaptor<EliminarUsuarioEvent> event = ArgumentCaptor.forClass(EliminarUsuarioEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_USER, event.getValue().getMetaData().get("userId"));
    }

    @Test
    public void eliminar_usuario_que_no_existe() throws Exception {
        // Dado un comando delete con id inexistente
        EliminarUsuarioCommand del = EliminarUsuarioCommand.of(ID_NOT_EXISTING_USER);
        // Cuando se lanza el comando
        deleteUserCommandHandler.handle(del);
        // Entonces no se elimina ningún usuario
        ArgumentCaptor<Usuario> user = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usersWriteRepository,never()).deleteUser(user.capture());
        // Y tiene el id esperado
        assertEquals(0, user.getAllValues().size());
        // Y no se lanza ningún evento
        ArgumentCaptor<EliminarUsuarioEvent> event = ArgumentCaptor.forClass(EliminarUsuarioEvent.class);
        Mockito.verify(eventBus,never()).publish(event.capture());
        assertEquals(0, event.getAllValues().size());
    }

}


package es.um.atica.umuexample.users.application;

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
import es.um.atica.umuexample.users.application.command.CrearUsuarioCommand;
import es.um.atica.umuexample.users.application.command.CrearUsuarioCommandHandler;
import es.um.atica.umuexample.users.domain.repository.UsuarioWriteRepository;
import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent;

@SpringBootTest
class CreateCommandHandlerRemoteTests extends SpringConfigurationTest{
    
    private static final String ID_USUARIO = "30497182-c376-11ed-afa1-0242ac220002";
    private static final String ID_USUARIO_INEXISTENTE = "30497172-c376-11ed-afa1-0242ac120555";

    @Autowired
    CrearUsuarioCommandHandler crearUsuarioCommandHandler;

    @SpyBean
    UsuarioWriteRepository usuarioWriteRepository;

    @SpyBean
    private EventBus eventBus;
    
    @Test
    void crear_usuario_ok() throws Exception {
        // Dado un comando crear con id nuevo
        CrearUsuarioCommand add = CrearUsuarioCommand.of(ID_USUARIO_INEXISTENTE, "TestCrear", 25);
        // Cuando se lanza el comando
        crearUsuarioCommandHandler.handle(add);
        // Entonces se crea el usuario
        ArgumentCaptor<Usuario> user = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usuarioWriteRepository).saveUser(user.capture());
        // Y tiene el id esperado
        assertEquals(ID_USUARIO_INEXISTENTE, user.getValue().getId());
        // Y se lanza el evento
        ArgumentCaptor<CrearUsuarioEvent> event = ArgumentCaptor.forClass(CrearUsuarioEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_USUARIO_INEXISTENTE, event.getValue().getMetaData().get("userId"));
    }

    @Test
    void crear_usuario_ya_existe() throws Exception {
        // Dado un comando crear con id existente
    	CrearUsuarioCommand add = CrearUsuarioCommand.of(ID_USUARIO, "TestUser 1", 21);
        // Cuando se lanza el comando
    	assertThrows(UnsupportedOperationException.class, () ->{ crearUsuarioCommandHandler.handle(add); });
        // Entonces no se crea ningún usuario
        ArgumentCaptor<Usuario> user = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usuarioWriteRepository,never()).saveUser(user.capture());
        // Y tiene el id esperado
        assertEquals(0, user.getAllValues().size());
        // Y no se lanza ningún evento
        ArgumentCaptor<CrearUsuarioEvent> event = ArgumentCaptor.forClass(CrearUsuarioEvent.class);
        Mockito.verify(eventBus,never()).publish(event.capture());
        assertEquals(0, event.getAllValues().size());
    }

}


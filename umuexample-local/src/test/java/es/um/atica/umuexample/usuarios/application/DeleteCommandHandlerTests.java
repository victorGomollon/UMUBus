package es.um.atica.umuexample.usuarios.application;

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
import es.um.atica.umuexample.usuarios.application.command.EliminarUsuarioCommand;
import es.um.atica.umuexample.usuarios.application.command.EliminarUsuarioCommandHandler;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioWriteRepository;
import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umuexample.usuarios.domain.event.EliminarUsuarioEvent;

@SpringBootTest
class DeleteCommandHandlerTests extends SpringConfigurationTest{
    
    private static final String ID_USUARIO = "30497182-c376-11ed-afa1-0242ac220002";
    private static final String ID_USUARIO_INEXISTENTE = "30497082-c376-11ed-afa1-0242ac120555";

    @Autowired
    EliminarUsuarioCommandHandler eliminarUsuarioCommandHandler;

    @SpyBean
    UsuarioWriteRepository usuarioWriteRepository;

    @SpyBean
    private EventBus eventBus;

    @Test
    void eliminar_usuario_existente() throws Exception {
        // Dado un comando delete con id correcto
        EliminarUsuarioCommand del = EliminarUsuarioCommand.of(ID_USUARIO);
        // Cuando se lanza el comando
        eliminarUsuarioCommandHandler.handle(del);
        // Entonces se elimina el usuario
        ArgumentCaptor<Usuario> usuario = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usuarioWriteRepository).deleteUsuario(usuario.capture());
        // Y tiene el id esperado
        assertEquals(ID_USUARIO, usuario.getValue().getId());
        // Y se lanza el evento
        ArgumentCaptor<EliminarUsuarioEvent> event = ArgumentCaptor.forClass(EliminarUsuarioEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_USUARIO, event.getValue().getMetaData().get("usuarioId"));
    }

    @Test
    void eliminar_usuario_inexistente() throws Exception {
        // Dado un comando delete con id inexistente
        EliminarUsuarioCommand del = EliminarUsuarioCommand.of(ID_USUARIO_INEXISTENTE);
        // Cuando se lanza el comando
        eliminarUsuarioCommandHandler.handle(del);
        // Entonces no se elimina ningún usuario
        ArgumentCaptor<Usuario> usuario = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usuarioWriteRepository,never()).deleteUsuario(usuario.capture());
        // Y tiene el id esperado
        assertEquals(0, usuario.getAllValues().size());
        // Y no se lanza ningún evento
        ArgumentCaptor<EliminarUsuarioEvent> event = ArgumentCaptor.forClass(EliminarUsuarioEvent.class);
        Mockito.verify(eventBus,never()).publish(event.capture());
        assertEquals(0, event.getAllValues().size());
    }

}


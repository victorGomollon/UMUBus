package es.um.atica.umuexample.usuarios.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umuexample.SpringConfigurationTest;
import es.um.atica.umuexample.usuarios.application.command.ActualizarUsuarioCommand;
import es.um.atica.umuexample.usuarios.application.command.ActualizarUsuarioCommandHandler;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioWriteRepository;
import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umuexample.usuarios.domain.event.ActualizarUsuarioEvent;

@SpringBootTest
class UpdateCommandHandlerLocalTests extends SpringConfigurationTest{
    
    private static final String ID_USUARIO = "30497182-c376-11ed-afa1-0242ac120002";
    private static final String NOMBRE_USUARIO = "Pepe Lopez";
    private static final String ID_USUARIO_INEXISTENTE = "30797182-c376-11ed-afa1-0242ac120555";

    @Autowired
    ActualizarUsuarioCommandHandler actualizarUsuarioCommandHandler;

    @SpyBean
    UsuarioWriteRepository usuariosWriteRepository;

    @SpyBean
    private EventBus eventBus;

    @Test
    void actualizar_nombre_usuario_ok() throws Exception {
        // Dado un comando update con id y nombre correcto
        ActualizarUsuarioCommand upd = ActualizarUsuarioCommand.of(ID_USUARIO,Optional.of(NOMBRE_USUARIO),Optional.empty());
        // Cuando se lanza el comando
        actualizarUsuarioCommandHandler.handle(upd);
        // Entonces se actualiza el usuario
        ArgumentCaptor<Usuario> usuario = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usuariosWriteRepository).saveUsuario(usuario.capture());
        // Y tiene el id y name esperado
        assertEquals(ID_USUARIO, usuario.getValue().getId());
        assertEquals(NOMBRE_USUARIO, usuario.getValue().getName());
        // Y se lanza el evento
        ArgumentCaptor<ActualizarUsuarioEvent> event = ArgumentCaptor.forClass(ActualizarUsuarioEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_USUARIO, event.getValue().getMetaData().get("usuarioId"));
    }
    
    @Test
    void actualizar_edad_usuario_ok() throws Exception {
        // Dado un comando update con id y nombre correcto
        ActualizarUsuarioCommand upd = ActualizarUsuarioCommand.of(ID_USUARIO,Optional.empty(),Optional.of(35));
        // Cuando se lanza el comando
        actualizarUsuarioCommandHandler.handle(upd);
        // Entonces se actualiza el usuario
        ArgumentCaptor<Usuario> usuario = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usuariosWriteRepository).saveUsuario(usuario.capture());
        // Y tiene el id y edad esperado
        assertEquals(ID_USUARIO, usuario.getValue().getId());
        assertEquals(35, usuario.getValue().getAge());
        // Y se lanza el evento
        ArgumentCaptor<ActualizarUsuarioEvent> event = ArgumentCaptor.forClass(ActualizarUsuarioEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_USUARIO, event.getValue().getMetaData().get("usuarioId"));
    }

    @Test
    void actualizar_usuario_inexistente() throws Exception {
        // Dado un comando update con id inexistente y nombre correcto
        ActualizarUsuarioCommand upd = ActualizarUsuarioCommand.of(ID_USUARIO_INEXISTENTE,Optional.of(NOMBRE_USUARIO),Optional.empty());
        // Cuando se lanza el comando
        actualizarUsuarioCommandHandler.handle(upd);
        // Entonces no se actualiza el usuario
        ArgumentCaptor<Usuario> usuario = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usuariosWriteRepository,never()).saveUsuario(usuario.capture());
        // Y tiene el id y name esperado
        assertEquals(0, usuario.getAllValues().size());
        // Y no se lanza el evento
        ArgumentCaptor<ActualizarUsuarioEvent> event = ArgumentCaptor.forClass(ActualizarUsuarioEvent.class);
        Mockito.verify(eventBus,never()).publish(event.capture());
        assertEquals(0, event.getAllValues().size());
    }

}


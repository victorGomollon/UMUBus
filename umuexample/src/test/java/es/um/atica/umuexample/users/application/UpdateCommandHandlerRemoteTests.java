package es.um.atica.umuexample.users.application;

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
import es.um.atica.umuexample.users.application.command.ActualizarUsuarioCommand;
import es.um.atica.umuexample.users.application.command.ActualizarUsuarioCommandHandler;
import es.um.atica.umuexample.users.domain.repository.UsuarioWriteRepository;
import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umuexample.users.domain.event.ActualizarUsuarioEvent;

@SpringBootTest
class UpdateCommandHandlerRemoteTests extends SpringConfigurationTest{
    
    private static final String ID_USUARIO = "30497182-c376-11ed-afa1-0242ac120002";
    private static final String NOMBRE_USUARIO = "Pepe Lopez";
    private static final String ID_USUARIO_INEXISTENTE = "30797182-c376-11ed-afa1-0242ac120555";

    @Autowired
    ActualizarUsuarioCommandHandler actualizarUsuarioCommandHandler;

    @SpyBean
    UsuarioWriteRepository usersWriteRepository;

    @SpyBean
    private EventBus eventBus;

    @Test
    void actualizar_nombre_usuario_ok() throws Exception {
        // Dado un comando update con id y nombre correcto
        ActualizarUsuarioCommand upd = ActualizarUsuarioCommand.of(ID_USUARIO,Optional.of(NOMBRE_USUARIO),Optional.empty());
        // Cuando se lanza el comando
        actualizarUsuarioCommandHandler.handle(upd);
        // Entonces se actualiza el usuario
        ArgumentCaptor<Usuario> user = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usersWriteRepository).saveUser(user.capture());
        // Y tiene el id y name esperado
        assertEquals(ID_USUARIO, user.getValue().getId());
        assertEquals(NOMBRE_USUARIO, user.getValue().getName());
        // Y se lanza el evento
        ArgumentCaptor<ActualizarUsuarioEvent> event = ArgumentCaptor.forClass(ActualizarUsuarioEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_USUARIO, event.getValue().getMetaData().get("userId"));
    }
    
    @Test
    void actualizar_edad_usuario_ok() throws Exception {
        // Dado un comando update con id y nombre correcto
        ActualizarUsuarioCommand upd = ActualizarUsuarioCommand.of(ID_USUARIO,Optional.empty(),Optional.of(35));
        // Cuando se lanza el comando
        actualizarUsuarioCommandHandler.handle(upd);
        // Entonces se actualiza el usuario
        ArgumentCaptor<Usuario> user = ArgumentCaptor.forClass(Usuario.class);
        Mockito.verify(usersWriteRepository).saveUser(user.capture());
        // Y tiene el id y edad esperado
        assertEquals(ID_USUARIO, user.getValue().getId());
        assertEquals(35, user.getValue().getAge());
        // Y se lanza el evento
        ArgumentCaptor<ActualizarUsuarioEvent> event = ArgumentCaptor.forClass(ActualizarUsuarioEvent.class);
        Mockito.verify(eventBus).publish(event.capture());
        assertEquals(ID_USUARIO, event.getValue().getMetaData().get("userId"));
    }

    @Test
    void actualizar_usuario_inexistente() throws Exception {
        // Dado un comando update con id inexistente y nombre correcto
        ActualizarUsuarioCommand upd = ActualizarUsuarioCommand.of(ID_USUARIO_INEXISTENTE,Optional.of(NOMBRE_USUARIO),Optional.empty());
        // Cuando se lanza el comando
        actualizarUsuarioCommandHandler.handle(upd);
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


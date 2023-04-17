package es.um.atica.umubus_pruebas.users.domain.event;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_lib.domain.events.Event;

public class CrearUsuarioEvent extends Event {

    private String userId;

    private CrearUsuarioEvent(String id) {
        this.userId = id;
    }

    public static CrearUsuarioEvent of (Usuario user) {
        return new CrearUsuarioEvent(user.getId());
    }

    public String getUserId() { return userId; }

    @Override
    public String getAggregateId() {
        return this.getUserId();
    }
}

package es.um.atica.umubus_pruebas.users.domain.event;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_lib.domain.events.Event;

public class EliminarUsuarioEvent extends Event {

    private String userId;

    private EliminarUsuarioEvent(String id) {
        this.userId = id;
    }

    public static EliminarUsuarioEvent of (Usuario user) {
        return new EliminarUsuarioEvent(user.getId().getValue());
    }

    public String getUserId() { return userId; }

    @Override
    public String getAggregateId() {
        return this.getUserId();
    }
}

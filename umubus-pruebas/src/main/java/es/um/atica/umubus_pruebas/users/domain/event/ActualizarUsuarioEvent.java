package es.um.atica.umubus_pruebas.users.domain.event;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_lib.domain.events.Event;

public class ActualizarUsuarioEvent extends Event {

    private String userId;
    private String changelog;

    private ActualizarUsuarioEvent(String id, String changelog) {
        this.userId = id; this.changelog = changelog;
    }

    public static ActualizarUsuarioEvent of (Usuario user, String changelog) {
        return new ActualizarUsuarioEvent(user.getId().getValue(), changelog);
    }

    public String getUserId() { return userId; }

    public String getChangelog() { return changelog; }

    @Override
    public String getAggregateId() {
        return this.getUserId();
    }
    
    //TODO: Es necesario??¿¿
    @Override
    public String getTypeFormat() { return "events.1.%s"; }
}

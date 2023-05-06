package es.um.atica.umuexample.users.domain.event;

import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umubus.domain.events.Event;

public class ActualizarUsuarioEvent extends Event {

    private String userId;
    private String changelog;

    public ActualizarUsuarioEvent() {
    	super();
    }
    
    private ActualizarUsuarioEvent(String id, String changelog) {
    	super();
        this.userId = id; this.changelog = changelog;
    }

    public static ActualizarUsuarioEvent of (Usuario user, String changelog) {
        return new ActualizarUsuarioEvent(user.getId(), changelog);
    }

    public String getUserId() { return userId; }

    public String getChangelog() { return changelog; }

}
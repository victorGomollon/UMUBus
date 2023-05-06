package es.um.atica.umuexample.users.domain.event;

import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umubus.domain.events.Event;

public class EliminarUsuarioEvent extends Event {

    private String userId;

    public EliminarUsuarioEvent() {
    	super();
    }
    
    private EliminarUsuarioEvent(String id) {
    	super();
        this.userId = id;
    }

    public static EliminarUsuarioEvent of (Usuario user) {
        return new EliminarUsuarioEvent(user.getId());
    }

    public String getUserId() { return userId; }

}
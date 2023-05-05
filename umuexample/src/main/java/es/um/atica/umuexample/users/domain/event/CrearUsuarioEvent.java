package es.um.atica.umuexample.users.domain.event;

import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umubus.domain.events.Event;

public class CrearUsuarioEvent extends Event {

    private String userId;

    public CrearUsuarioEvent() {
        super();
    }
    
    private CrearUsuarioEvent(String id) {
    	super();
        this.userId = id;
    }

    public static CrearUsuarioEvent of (Usuario user) {
        return new CrearUsuarioEvent(user.getId());
    }

    public String getUserId() { return userId; }

    @Override
    public String toString() { 
        return super.toString() + String.format("[userId:%s]",userId);
    }
}

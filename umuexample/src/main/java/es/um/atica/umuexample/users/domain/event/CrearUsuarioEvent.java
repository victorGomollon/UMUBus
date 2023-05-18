package es.um.atica.umuexample.users.domain.event;

import es.um.atica.umuexample.users.domain.model.Usuario;
import lombok.Getter;
import es.um.atica.umubus.domain.events.Event;

@Getter
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

    @Override
    public String toString() { 
        return super.toString() + String.format("[userId:%s]",userId);
    }
}

package es.um.atica.umuexample.users.domain.event;

import es.um.atica.umuexample.users.domain.model.Usuario;
import lombok.Getter;
import es.um.atica.umubus.domain.events.Event;

@Getter
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

    @Override
    public String toString() { 
        return super.toString() + String.format("[userId:%s]",userId);
    }

}

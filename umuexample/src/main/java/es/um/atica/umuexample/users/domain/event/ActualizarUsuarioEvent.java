package es.um.atica.umuexample.users.domain.event;

import es.um.atica.umuexample.users.domain.model.Usuario;
import lombok.Getter;
import es.um.atica.umubus.domain.events.Event;

@Getter
public class ActualizarUsuarioEvent extends Event {

    private String userId;
    private String changelog;

    public ActualizarUsuarioEvent() {
    	super();
    	this.setLocal(true);
    }
    
    private ActualizarUsuarioEvent(String id, String changelog) {
    	super();
        this.userId = id; this.changelog = changelog; this.setLocal(true);
    }

    public static ActualizarUsuarioEvent of (Usuario user, String changelog) {
        return new ActualizarUsuarioEvent(user.getId(), changelog);
    }
    
    @Override
    public String toString() { 
        return super.toString() + String.format("[userId:%s][chlog:%s]",userId,changelog);
    }

}

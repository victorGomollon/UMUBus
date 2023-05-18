package es.um.atica.umuexample.usuarios.domain.event;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import lombok.Getter;
import es.um.atica.umubus.domain.events.Event;

@Getter
public class ActualizarUsuarioEvent extends Event {

    private String usuarioId;
    private String changelog;

    public ActualizarUsuarioEvent() {
    	super();
    	this.setLocal(true);
    }
    
    private ActualizarUsuarioEvent(String id, String changelog) {
    	super();
        this.usuarioId = id; this.changelog = changelog; this.setLocal(true);
    }

    public static ActualizarUsuarioEvent of (Usuario usuario, String changelog) {
        return new ActualizarUsuarioEvent(usuario.getId(), changelog);
    }

    @Override
    public String toString() { 
        return super.toString() + String.format("[usuarioId:%s][chlog:%s]",usuarioId,changelog);
    }

}

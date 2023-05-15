package es.um.atica.umuexample.usuarios.domain.event;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umubus.domain.events.Event;

public class ActualizarUsuarioEvent extends Event {

    private String usuarioId;
    private String changelog;

    public ActualizarUsuarioEvent() {
    	super();
    }
    
    private ActualizarUsuarioEvent(String id, String changelog) {
    	super();
        this.usuarioId = id; this.changelog = changelog;
    }

    public static ActualizarUsuarioEvent of (Usuario usuario, String changelog) {
        return new ActualizarUsuarioEvent(usuario.getId(), changelog);
    }

    public String getUsuarioId() { return usuarioId; }

    public String getChangelog() { return changelog; }
    
    @Override
    public String toString() { 
        return super.toString() + String.format("[usuarioId:%s][chlog:%s]",usuarioId,changelog);
    }

}

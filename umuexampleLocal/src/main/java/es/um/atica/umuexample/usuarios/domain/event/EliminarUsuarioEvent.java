package es.um.atica.umuexample.usuarios.domain.event;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umubus.domain.events.Event;

public class EliminarUsuarioEvent extends Event {

    private String usuarioId;

    public EliminarUsuarioEvent() {
    	super();
    }
    
    private EliminarUsuarioEvent(String id) {
    	super();
        this.usuarioId = id;
    }

    public static EliminarUsuarioEvent of (Usuario usuario) {
        return new EliminarUsuarioEvent(usuario.getId());
    }

    public String getUsuarioId() { return usuarioId; }
    
    @Override
    public String toString() { 
        return super.toString() + String.format("[usuarioId:%s]",usuarioId);
    }

}

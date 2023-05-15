package es.um.atica.umuexample.usuarios.domain.event;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umubus.domain.events.Event;

public class CrearUsuarioEvent extends Event {

    private String usuarioId;

    public CrearUsuarioEvent() {
        super();
    }
    
    private CrearUsuarioEvent(String id) {
    	super();
        this.usuarioId = id;
    }

    public static CrearUsuarioEvent of (Usuario usuario) {
        return new CrearUsuarioEvent(usuario.getId());
    }

    public String getUsuarioId() { return usuarioId; }

    @Override
    public String toString() { 
        return super.toString() + String.format("[usuarioId:%s]",usuarioId);
    }
}

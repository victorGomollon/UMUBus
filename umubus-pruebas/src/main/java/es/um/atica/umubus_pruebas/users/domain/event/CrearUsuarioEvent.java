package es.um.atica.umubus_pruebas.users.domain.event;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_lib.domain.events.Event;

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
    public String getAggregateId() {
        return this.getUserId();
    }
    
    //TODO: Es necesario??¿¿
//    @Override
//    public String getTypeFormat() { return "events.1.%s"; }
    
    @Override
    public String toString() { 
        return super.toString() + String.format("[userId:%s]",userId);
    }
}

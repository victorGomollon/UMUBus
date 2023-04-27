package es.um.atica.umubus_pruebas.users.domain.event;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_lib.domain.events.Event;

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

    @Override
    public String getAggregateId() {
        return this.getUserId();
    }
    
    //TODO: Es necesario??¿¿
//    @Override
//    public String getTypeFormat() { return "events.1.%s"; }
}

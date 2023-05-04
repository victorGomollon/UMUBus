package es.um.atica.umubus_pruebas.users.domain.event;

import es.um.atica.umubus_lib.domain.events.Event;

public class UsuarioEvent extends Event {

    protected String userId;

    public UsuarioEvent() { super(); }

    protected UsuarioEvent(String id) {
        super();
        this.userId = id;
    }

    @Override
    public String toString() { 
        return super.toString() + String.format("[userId:%s]",userId);
    }

}

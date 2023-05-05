package es.um.atica.umuexample.users.domain.event;

import es.um.atica.umubus.domain.events.Event;

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

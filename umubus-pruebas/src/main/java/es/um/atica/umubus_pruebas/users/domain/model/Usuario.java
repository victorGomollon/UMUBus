package es.um.atica.umubus_pruebas.users.domain.model;

import es.um.atica.umubus_lib.domain.ddd.AggregateRoot;
import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventCollection;
import es.um.atica.umubus_pruebas.users.domain.event.CrearUsuarioEvent;
import es.um.atica.umubus_pruebas.users.domain.event.EliminarUsuarioEvent;
import es.um.atica.umubus_pruebas.users.domain.event.ActualizarUsuarioEvent;

public class Usuario implements AggregateRoot {

    private String id;
    private String name;
    private int age;
    private EventCollection events = new EventCollection();

    private Usuario(String id, String name, int age) {
        this.id = id; this.name = name; this.age = age;
    }

    public static Usuario of (String id, String name, int age) {
        return new Usuario(id,name,age);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void createUser() {
        this.addEvent(CrearUsuarioEvent.of(this));
    }

    public void updateUserName(String name) {
        this.name = name;
        this.addEvent(ActualizarUsuarioEvent.of(this,"name changed"));
    }

    public void updateUserAge(int age) {
        this.age = age;
        this.addEvent(ActualizarUsuarioEvent.of(this,"age changed"));
    }

    public void deleteUser() {
        this.addEvent(EliminarUsuarioEvent.of(this));
    }

    public boolean nameStartsWith(String startsWith) {
        return this.name.startsWith(startsWith);
    }

    @Override
    public EventCollection getEvents() { return events; }

    @Override
    public void addEvent(Event event) { events.add(event); }

}
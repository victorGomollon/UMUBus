package es.um.atica.umuexample.usuarios.domain.model;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventCollection;
import es.um.atica.umubus.domain.model.AggregateRoot;
import es.um.atica.umuexample.usuarios.domain.event.CrearUsuarioEvent;
import es.um.atica.umuexample.usuarios.domain.event.EliminarUsuarioEvent;
import lombok.Getter;
import es.um.atica.umuexample.usuarios.domain.event.ActualizarUsuarioEvent;

@Getter
public class Usuario extends AggregateRoot {

    private String id;
    private String name;
    private int age;

    private Usuario(String id, String name, int age) {
        this.id = id; this.name = name; this.age = age;
    }

    public static Usuario of (String id, String name, int age) {
        return new Usuario(id,name,age);
    }

    public void createUsuario() {
        this.addEvent(CrearUsuarioEvent.of(this));
    }

    public void updateUsuarioName(String name) {
        this.name = name;
        this.addEvent(ActualizarUsuarioEvent.of(this,"name changed"));
    }

    public void updateUsuarioAge(int age) {
        this.age = age;
        this.addEvent(ActualizarUsuarioEvent.of(this,"age changed"));
    }

    public void deleteUsuario() {
        this.addEvent(EliminarUsuarioEvent.of(this));
    }

}
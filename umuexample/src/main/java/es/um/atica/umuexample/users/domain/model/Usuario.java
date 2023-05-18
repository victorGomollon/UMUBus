package es.um.atica.umuexample.users.domain.model;

import es.um.atica.umubus.domain.model.AggregateRoot;
import es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent;
import es.um.atica.umuexample.users.domain.event.EliminarUsuarioEvent;
import lombok.Getter;
import es.um.atica.umuexample.users.domain.event.ActualizarUsuarioEvent;

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
}
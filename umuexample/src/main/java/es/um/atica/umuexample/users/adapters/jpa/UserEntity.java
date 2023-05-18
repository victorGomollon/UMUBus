package es.um.atica.umuexample.users.adapters.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.um.atica.umuexample.users.domain.factory.UsuarioFactory;
import es.um.atica.umuexample.users.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="USERS",schema="PRUEBAS")
public class UserEntity {

    @Id
    @Column(name = "identificador")
    private String id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "edad")
    private int age;

    private UserEntity() {}
    private UserEntity(String id, String name,int age) {
        this.id = id; this.name = name; this.age = age;
    }

    public static UserEntity of (Usuario usr) {
        return new UserEntity(
            usr.getId(),
            usr.getName(),
            usr.getAge());
    }

    public Usuario toModel() {
        return UsuarioFactory.createUser(
            this.id, 
            this.name,
            this.age);
    }
}

package es.um.atica.umuexample.usuarios.adapters.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.um.atica.umuexample.usuarios.domain.factory.UsuarioFactory;
import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import lombok.Getter;

@Entity
@Getter
@Table(name="USUARIOS",schema="PRUEBAS_LOCAL")
public class UsuarioEntity {

	@Id
    @Column(name = "identificador")
    private String id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "edad")
    private int age;

    private UsuarioEntity() {}
    private UsuarioEntity(String id, String name,int age) {
        this.id = id; this.name = name; this.age = age;
    }

    public static UsuarioEntity of (Usuario usr) {
        return new UsuarioEntity(
            usr.getId(),
            usr.getName(),
            usr.getAge());
    }

    public Usuario toModel() {
        return UsuarioFactory.createUsuario(
            this.id, 
            this.name,
            this.age);
    }
}

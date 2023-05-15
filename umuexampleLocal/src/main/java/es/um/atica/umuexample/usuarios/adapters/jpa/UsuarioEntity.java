package es.um.atica.umuexample.usuarios.adapters.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.um.atica.umuexample.usuarios.domain.factory.UsuarioFactory;
import es.um.atica.umuexample.usuarios.domain.model.Usuario;

@Entity
@Table(name="USUARIOS",schema="PRUEBAS_LOCAL")
public class UsuarioEntity {
    private String id;
    private String name;
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

    @Id
    @Column(name = "identificador")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Column(name = "nombre")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name = "edad")
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

}

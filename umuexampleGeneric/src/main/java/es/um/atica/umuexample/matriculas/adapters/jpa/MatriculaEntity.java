package es.um.atica.umuexample.matriculas.adapters.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;

@Entity
@Table(name="MATRICULAS",schema="PRUEBAS_GENERIC")
public class MatriculaEntity {
    private String id;
    private String name;
    private String asignatura;
    private String usuario;

    private MatriculaEntity() {}
    private MatriculaEntity(String id, String name,String asignatura, String usuario) {
        this.id = id; this.name = name; this.asignatura = asignatura; this.usuario=usuario;
    }

    public static MatriculaEntity of (Matricula mat) {
        return new MatriculaEntity(
            mat.getId(),
            mat.getName(),
            mat.getAsignatura(),
            mat.getUsuario());
    }

    public Matricula toModel() {
        return Matricula.of(
            this.id, 
            this.name,
            this.asignatura,
            this.usuario);
    }

    @Id
    @Column(name = "identificador")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Column(name = "nombre")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name = "asignatura")
    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }
    
    @Column(name = "usuario")
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

}

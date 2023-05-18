package es.um.atica.umuexample.matriculas.adapters.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import lombok.Getter;

@Entity
@Getter
@Table(name="MATRICULAS",schema="PRUEBAS_GENERIC")
public class MatriculaEntity {

	@Id
    @Column(name = "identificador")
    private String id;
	@Column(name = "nombre")
    private String name;
	@Column(name = "asignatura")
    private String asignatura;
	@Column(name = "usuario")
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
}

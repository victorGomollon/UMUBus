package es.um.atica.umuexample.matriculas.domain.model;

import es.um.atica.umubus.domain.model.AggregateRoot;
import es.um.atica.umuexample.matriculas.domain.event.CrearMatriculaEvent;
import es.um.atica.umuexample.matriculas.domain.event.EliminarMatriculaEvent;
import lombok.Getter;

@Getter
public class Matricula extends AggregateRoot {

    private String id;
    private String name;
    private String asignatura;
    private String usuario;

    private Matricula(String id, String name, String asignatura, String usuario) {
        this.id = id; this.name = name; this.asignatura = asignatura; this.usuario = usuario;
    }

    public static Matricula of (String id, String name, String asignatura, String usuario) {
        return new Matricula(id,name,asignatura, usuario);
    }

    public void createMatricula() {
        this.addEvent(CrearMatriculaEvent.of(this));
    }

    public void deleteMatricula() {
        this.addEvent(EliminarMatriculaEvent.of(this));
    }
}
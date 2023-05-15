package es.um.atica.umuexample.matriculas.domain.model;

import es.um.atica.umubus.domain.ddd.AggregateRoot;
import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventCollection;
import es.um.atica.umuexample.matriculas.domain.event.CrearMatriculaEvent;
import es.um.atica.umuexample.matriculas.domain.event.EliminarMatriculaEvent;

public class Matricula implements AggregateRoot {

    private String id;
    private String name;
    private String asignatura;
    private String usuario;
    private EventCollection events = new EventCollection();

    private Matricula(String id, String name, String asignatura, String usuario) {
        this.id = id; this.name = name; this.asignatura = asignatura; this.usuario = usuario;
    }

    public static Matricula of (String id, String name, String asignatura, String usuario) {
        return new Matricula(id,name,asignatura, usuario);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getAsignatura() { return asignatura; }
    public String getUsuario() { return usuario; }

    public void createMatricula() {
        this.addEvent(CrearMatriculaEvent.of(this));
    }

    public void deleteMatricula() {
        this.addEvent(EliminarMatriculaEvent.of(this));
    }

    @Override
    public EventCollection getEvents() { return events; }

    @Override
    public void addEvent(Event event) { events.add(event); }

}
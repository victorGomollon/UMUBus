package es.um.atica.umuexample.matriculas.application.command;

import es.um.atica.umubus.domain.cqrs.SyncCommand;

public class CrearMatriculaCommand extends SyncCommand<Void> {
    
    private String id;
    private String name;
    private String asignatura;
    private String usuario;

    private CrearMatriculaCommand(String id, String name, String asignatura, String usuario) {
        this.id = id; this.name = name; this.asignatura = asignatura; this.usuario = usuario;
    }

    public static CrearMatriculaCommand of(String id, String name, String asignatura, String usuario) {
        // Validate Command Data for UI
        return new CrearMatriculaCommand(id, name, asignatura, usuario);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getAsignatura() { return asignatura; }
    public String getUsuario() { return usuario; }

}

package es.um.atica.umuexample.matriculas.application.command;

import es.um.atica.umubus.domain.cqrs.Command;

public class EliminarMatriculaCommand extends Command {
    
    private String id;

    private EliminarMatriculaCommand(String id) {
        this.id = id;
    }

    public static EliminarMatriculaCommand of(String id) {
        // Validate Command Data for UI
        return new EliminarMatriculaCommand(id);
    }

    public String getId() { return id; }

}

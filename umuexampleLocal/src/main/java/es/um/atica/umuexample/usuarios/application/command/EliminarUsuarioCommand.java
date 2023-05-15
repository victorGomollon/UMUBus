package es.um.atica.umuexample.usuarios.application.command;

import es.um.atica.umubus.domain.cqrs.Command;

public class EliminarUsuarioCommand extends Command {
    
    private String id;

    private EliminarUsuarioCommand(String id) {
        this.id = id;
    }

    public static EliminarUsuarioCommand of(String id) {
        // Validate Command Data for UI
        return new EliminarUsuarioCommand(id);
    }

    public String getId() { return id; }

}

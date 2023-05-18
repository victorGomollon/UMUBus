package es.um.atica.umuexample.users.application.command;

import es.um.atica.umubus.domain.cqrs.Command;
import lombok.Getter;

@Getter
public class EliminarUsuarioCommand extends Command {
    
    private String id;

    private EliminarUsuarioCommand(String id) {
        this.id = id;
    }

    public static EliminarUsuarioCommand of(String id) {
        // Validate Command Data for UI
        return new EliminarUsuarioCommand(id);
    }

}

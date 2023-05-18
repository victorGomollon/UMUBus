package es.um.atica.umuexample.usuarios.application.command;

import java.util.Optional;

import es.um.atica.umubus.domain.cqrs.Command;

public class ActualizarUsuarioCommand extends Command {
    
    private String id;
    private Optional<String> name;
    private Optional<Integer> age;

    private ActualizarUsuarioCommand(String id, Optional<String> name, Optional<Integer> age) {
        this.id = id; this.name = name; this.age = age;
    }

    public static ActualizarUsuarioCommand of(String id, Optional<String> name, Optional<Integer> age) {
        // Validate Command Data for UI
        if (name.isPresent()) name.get();
        if (age.isPresent()) age.get();
        return new ActualizarUsuarioCommand(id, name, age);
    }

    public String getId() { return id; }
    public Optional<String> getName() { return name; }
    public Optional<Integer> getAge() { return age; }

}

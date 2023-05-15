package es.um.atica.umuexample.usuarios.application.command;

import es.um.atica.umubus.domain.cqrs.SyncCommand;

public class CrearUsuarioCommand extends SyncCommand<Void> {
    
    private String id;
    private String name;
    private int age;

    private CrearUsuarioCommand(String id, String name, int age) {
        this.id = id; this.name = name; this.age = age;
    }

    public static CrearUsuarioCommand of(String id, String name, int age) {
        // Validate Command Data for UI
        return new CrearUsuarioCommand(id, name, age);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

}

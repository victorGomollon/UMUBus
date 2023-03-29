package es.um.atica.umubus_lib.domain.cqrs;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}

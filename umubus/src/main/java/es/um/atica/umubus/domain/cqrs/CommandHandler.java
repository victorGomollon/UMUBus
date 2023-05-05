package es.um.atica.umubus.domain.cqrs;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}

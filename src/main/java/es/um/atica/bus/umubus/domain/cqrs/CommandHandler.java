package es.um.atica.bus.umubus.domain.cqrs;

public interface CommandHandler<T extends Command> {
    void handle(T command) throws Exception;
}

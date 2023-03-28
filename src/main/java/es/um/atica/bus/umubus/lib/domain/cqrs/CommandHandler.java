package es.um.atica.bus.umubus.lib.domain.cqrs;

public interface CommandHandler<T extends Command> {
    void handle(T command) throws Exception;
}

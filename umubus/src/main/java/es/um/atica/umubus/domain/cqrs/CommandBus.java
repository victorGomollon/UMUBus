package es.um.atica.umubus.domain.cqrs;

public interface CommandBus {
    void handle(Command command) throws Exception;
}

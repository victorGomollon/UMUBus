package es.um.atica.umubus_lib.domain.cqrs;

public interface CommandBus {
    void handle(Command command) throws Exception;
}

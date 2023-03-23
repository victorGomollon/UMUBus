package es.um.atica.bus.umubus.domain.cqrs;

public interface CommandBus {
    void handle(Command command) throws Exception;
}

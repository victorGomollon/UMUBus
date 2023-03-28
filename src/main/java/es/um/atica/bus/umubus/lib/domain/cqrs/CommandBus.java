package es.um.atica.bus.umubus.lib.domain.cqrs;

public interface CommandBus {
    void handle(Command command) throws Exception;
}

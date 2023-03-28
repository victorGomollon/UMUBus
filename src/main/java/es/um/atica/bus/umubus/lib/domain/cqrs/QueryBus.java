package es.um.atica.bus.umubus.lib.domain.cqrs;

public interface QueryBus {
    <T> T handle(Query<T> query) throws Exception;
}

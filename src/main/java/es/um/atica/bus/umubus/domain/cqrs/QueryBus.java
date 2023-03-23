package es.um.atica.bus.umubus.domain.cqrs;

public interface QueryBus {
    <T> T handle(Query<T> query) throws Exception;
}

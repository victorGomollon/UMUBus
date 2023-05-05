package es.um.atica.umubus.domain.cqrs;

public interface QueryBus {
    <T> T handle(Query<T> query) throws Exception;
}

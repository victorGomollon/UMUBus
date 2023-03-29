package es.um.atica.umubus_lib.domain.cqrs;

public interface QueryBus {
    <T> T handle(Query<T> query) throws Exception;
}

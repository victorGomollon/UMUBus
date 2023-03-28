package es.um.atica.bus.umubus.lib.domain.cqrs;

public interface QueryHandler<T,U extends Query<T>> {
    T handle(U query) throws Exception;
}

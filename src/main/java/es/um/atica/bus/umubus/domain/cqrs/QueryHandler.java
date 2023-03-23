package es.um.atica.bus.umubus.domain.cqrs;

public interface QueryHandler<T,U extends Query<T>> {
    T handle(U query) throws Exception;
}

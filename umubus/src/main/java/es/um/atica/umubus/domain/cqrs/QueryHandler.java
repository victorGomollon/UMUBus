package es.um.atica.umubus.domain.cqrs;

public interface QueryHandler<T,U extends Query<T>> {
    T handle(U query) throws Exception;
}

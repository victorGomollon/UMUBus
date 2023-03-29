package es.um.atica.umubus_lib.domain.cqrs;

public interface SyncCommandHandler<T,U extends SyncCommand<T>> {
    T handle(U command) throws Exception;
}
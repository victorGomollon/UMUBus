package es.um.atica.umubus.domain.cqrs;

public interface SyncCommandHandler<T,U extends SyncCommand<T>> {
    T handle(U command) throws Exception;
}
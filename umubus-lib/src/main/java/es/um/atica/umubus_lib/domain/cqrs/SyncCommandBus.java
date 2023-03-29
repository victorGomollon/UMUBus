package es.um.atica.umubus_lib.domain.cqrs;

public interface SyncCommandBus {
    <T> T handle(SyncCommand<T> command) throws Exception;
}

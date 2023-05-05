package es.um.atica.umubus.domain.cqrs;

public interface SyncCommandBus {
    <T> T handle(SyncCommand<T> command) throws Exception;
}

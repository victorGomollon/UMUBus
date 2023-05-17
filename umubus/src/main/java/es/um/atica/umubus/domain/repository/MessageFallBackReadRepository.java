package es.um.atica.umubus.domain.repository;

import java.util.Optional;

import es.um.atica.umubus.domain.model.MessageFallBack;

public interface MessageFallBackReadRepository {
    
    public Iterable<MessageFallBack> findAllMessageFB();
    public Optional<MessageFallBack> findMessage(String messageFBId);

}

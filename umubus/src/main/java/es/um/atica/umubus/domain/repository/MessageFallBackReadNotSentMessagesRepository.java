package es.um.atica.umubus.domain.repository;

import es.um.atica.umubus.domain.model.MessageFallBack;

public interface MessageFallBackReadNotSentMessagesRepository {

	 public Iterable<MessageFallBack> findAllNotSentMessages();
}

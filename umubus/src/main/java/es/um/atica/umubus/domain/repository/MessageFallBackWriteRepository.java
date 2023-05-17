package es.um.atica.umubus.domain.repository;

import es.um.atica.umubus.domain.model.MessageFallBack;

public interface MessageFallBackWriteRepository {
    
    public void saveMessageFB(MessageFallBack messageFB);

    public void deleteMessageFB(MessageFallBack messageFB);

	void deleteMessageFBById(String idMessage);

}

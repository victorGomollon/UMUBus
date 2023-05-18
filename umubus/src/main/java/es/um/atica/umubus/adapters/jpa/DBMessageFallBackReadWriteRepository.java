package es.um.atica.umubus.adapters.jpa;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import es.um.atica.umubus.domain.model.MessageFallBack;
import es.um.atica.umubus.domain.repository.MessageFallBackReadNotSentMessagesRepository;
import es.um.atica.umubus.domain.repository.MessageFallBackReadRepository;
import es.um.atica.umubus.domain.repository.MessageFallBackWriteRepository;

@Service
@ConditionalOnProperty(name="umubus.event.producer")
public class DBMessageFallBackReadWriteRepository implements MessageFallBackReadRepository, MessageFallBackWriteRepository, MessageFallBackReadNotSentMessagesRepository {

	@Autowired
    private JpaMessageFallBackReadRepository jpaMessageFallBackReadRepository;

    @Override
    public void saveMessageFB(MessageFallBack messageFB) {
    	jpaMessageFallBackReadRepository.save(MessageFallBackEntity.of(messageFB));
    }

    @Override
    public void deleteMessageFB(MessageFallBack messageFB) {
    	jpaMessageFallBackReadRepository.delete(MessageFallBackEntity.of(messageFB));
    	jpaMessageFallBackReadRepository.deleteById(null);
    }

    @Override
    public void deleteMessageFBById(String idMessage) {
    	jpaMessageFallBackReadRepository.deleteById(idMessage);
    }
    
    @Override
    public Optional<MessageFallBack> findMessage(String messageFBId) {
        return jpaMessageFallBackReadRepository.findById(messageFBId).map(MessageFallBackEntity::toModel);
    }

    @Override
    public Iterable<MessageFallBack> findAllMessageFB() {
        return StreamSupport.stream(jpaMessageFallBackReadRepository.findAll().spliterator(), false)
            .map(MessageFallBackEntity::toModel).collect(Collectors.toList());
    }

	@Override
	public Iterable<MessageFallBack> findAllNotSentMessages() {
		Timestamp ts = new Timestamp(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toEpochSecond() - 60000);
		return StreamSupport.stream(jpaMessageFallBackReadRepository.findBySendDateBefore(ts).spliterator(), false)
	            .map(MessageFallBackEntity::toModel).collect(Collectors.toList());
	}
    
}

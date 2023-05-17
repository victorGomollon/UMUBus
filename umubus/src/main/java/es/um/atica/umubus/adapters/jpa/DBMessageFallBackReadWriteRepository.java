package es.um.atica.umubus.adapters.jpa;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import es.um.atica.umubus.domain.model.MessageFallBack;
import es.um.atica.umubus.domain.repository.MessageFallBackReadRepository;
import es.um.atica.umubus.domain.repository.MessageFallBackWriteRepository;

@Service
@ConditionalOnProperty(name="umubus.event.producer")
public class DBMessageFallBackReadWriteRepository implements MessageFallBackReadRepository,MessageFallBackWriteRepository {

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
    
}

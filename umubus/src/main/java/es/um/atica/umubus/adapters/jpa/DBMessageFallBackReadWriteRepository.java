package es.um.atica.umubus.adapters.jpa;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.atica.umubus.domain.model.MessageFallBack;
import es.um.atica.umubus.domain.repository.MessageFallBackReadRepository;
import es.um.atica.umubus.domain.repository.MessageFallBackWriteRepository;

@Service
@org.springframework.context.annotation.Primary
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

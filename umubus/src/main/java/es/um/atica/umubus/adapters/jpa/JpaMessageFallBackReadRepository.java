package es.um.atica.umubus.adapters.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMessageFallBackReadRepository extends JpaRepository<MessageFallBackEntity,String>{
    
}

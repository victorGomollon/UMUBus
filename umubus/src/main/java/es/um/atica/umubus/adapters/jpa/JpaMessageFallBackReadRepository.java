package es.um.atica.umubus.adapters.jpa;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMessageFallBackReadRepository extends JpaRepository<MessageFallBackEntity,String>{
	List<MessageFallBackEntity> findBySendDateBefore(Timestamp ts);
}

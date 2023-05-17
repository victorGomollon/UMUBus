package es.um.atica.umuexample.users.adapters.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAUsersReadRepository extends JpaRepository<UserEntity,String> {
    
}

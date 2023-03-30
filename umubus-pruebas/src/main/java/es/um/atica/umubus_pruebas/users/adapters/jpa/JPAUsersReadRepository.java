package es.um.atica.umubus_pruebas.users.adapters.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAUsersReadRepository extends PagingAndSortingRepository<UserEntity,String>, JpaSpecificationExecutor<UserEntity>{
    
}

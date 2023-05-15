package es.um.atica.umuexample.usuarios.adapters.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAUsuariosReadRepository extends PagingAndSortingRepository<UsuarioEntity,String>, JpaSpecificationExecutor<UsuarioEntity>{
    
}

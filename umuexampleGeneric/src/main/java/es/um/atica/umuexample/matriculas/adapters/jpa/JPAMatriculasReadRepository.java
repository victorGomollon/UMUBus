package es.um.atica.umuexample.matriculas.adapters.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAMatriculasReadRepository extends PagingAndSortingRepository<MatriculaEntity,String>, JpaSpecificationExecutor<MatriculaEntity>{
    
}

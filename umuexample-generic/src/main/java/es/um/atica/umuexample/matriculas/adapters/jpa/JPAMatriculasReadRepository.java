package es.um.atica.umuexample.matriculas.adapters.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAMatriculasReadRepository extends JpaRepository<MatriculaEntity,String> {
    
}

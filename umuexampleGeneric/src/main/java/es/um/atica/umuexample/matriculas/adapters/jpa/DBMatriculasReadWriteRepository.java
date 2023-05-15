package es.um.atica.umuexample.matriculas.adapters.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaReadRepository;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaWriteRepository;

@Service
@org.springframework.context.annotation.Primary
public class DBMatriculasReadWriteRepository implements MatriculaReadRepository,MatriculaWriteRepository {

    @Autowired
    private JPAMatriculasReadRepository jpaMatriculasReadRepository;
    

    @Override
    public void saveMatricula(Matricula matricula) {
        jpaMatriculasReadRepository.save(MatriculaEntity.of(matricula));
    }

    @Override
    public void deleteMatricula(Matricula matricula) {
    	jpaMatriculasReadRepository.delete(MatriculaEntity.of(matricula));
    }

    @Override
    public Optional<Matricula> findMatricula(String matriculaId) {
        return jpaMatriculasReadRepository.findById(matriculaId).map(MatriculaEntity::toModel);
    }

    @Override
    public List<Matricula> findAllMatriculas() {
        return StreamSupport.stream(jpaMatriculasReadRepository.findAll().spliterator(), false)
            .map(MatriculaEntity::toModel).collect(Collectors.toList());
    }
    
}

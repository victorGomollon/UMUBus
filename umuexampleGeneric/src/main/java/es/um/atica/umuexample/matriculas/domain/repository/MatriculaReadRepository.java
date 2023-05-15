package es.um.atica.umuexample.matriculas.domain.repository;

import java.util.List;
import java.util.Optional;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;

public interface MatriculaReadRepository {
    
    public List<Matricula> findAllMatriculas();
    public Optional<Matricula> findMatricula(String matriculaId);

}

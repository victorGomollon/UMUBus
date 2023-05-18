package es.um.atica.umuexample.matriculas.domain.repository;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;

public interface MatriculaWriteRepository {
    
    public void saveMatricula(Matricula matricula);

    public void deleteMatricula(Matricula matricula);

}

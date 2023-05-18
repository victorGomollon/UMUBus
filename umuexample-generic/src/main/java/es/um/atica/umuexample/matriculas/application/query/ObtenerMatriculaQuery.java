package es.um.atica.umuexample.matriculas.application.query;

import java.util.Optional;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import lombok.Getter;
import es.um.atica.umubus.domain.cqrs.Query;

@Getter
public class ObtenerMatriculaQuery extends Query<Optional<Matricula>> {
    private String matriculaId;
    private ObtenerMatriculaQuery(String matriculaId) {
        this.matriculaId = matriculaId;
    }
    public static ObtenerMatriculaQuery of(String matriculaId) {
        return new ObtenerMatriculaQuery(matriculaId);
    }
}

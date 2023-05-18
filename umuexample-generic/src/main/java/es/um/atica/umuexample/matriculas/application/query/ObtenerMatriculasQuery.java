package es.um.atica.umuexample.matriculas.application.query;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;

import java.util.List;

import es.um.atica.umubus.domain.cqrs.Query;

public class ObtenerMatriculasQuery extends Query<List<Matricula>> {
    private ObtenerMatriculasQuery() {
    }
    public static ObtenerMatriculasQuery of() {
        return new ObtenerMatriculasQuery();
    }
}

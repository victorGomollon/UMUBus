package es.um.atica.umuexample.usuarios.application.query;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;

import java.util.List;

import es.um.atica.umubus.domain.cqrs.Query;

public class ObtenerUsuariosQuery extends Query<List<Usuario>> {
    private ObtenerUsuariosQuery() {
    }
    public static ObtenerUsuariosQuery of() {
        return new ObtenerUsuariosQuery();
    }
}

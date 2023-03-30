package es.um.atica.umubus_pruebas.users.application.query;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;

import java.util.List;

import es.um.atica.umubus_lib.domain.cqrs.Query;

public class ObtenerUsuariosQuery extends Query<List<Usuario>> {
    private ObtenerUsuariosQuery() {
    }
    public static ObtenerUsuariosQuery of() {
        return new ObtenerUsuariosQuery();
    }
}

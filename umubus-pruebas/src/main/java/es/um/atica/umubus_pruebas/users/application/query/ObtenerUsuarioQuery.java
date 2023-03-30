package es.um.atica.umubus_pruebas.users.application.query;

import java.util.Optional;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_lib.domain.cqrs.Query;

public class ObtenerUsuarioQuery extends Query<Optional<Usuario>> {
    private String userId;
    private ObtenerUsuarioQuery(String userId) {
        this.userId = userId;
    }
    public static ObtenerUsuarioQuery of(String userId) {
        return new ObtenerUsuarioQuery(userId);
    }

    public String getUserId() { return userId; }
}

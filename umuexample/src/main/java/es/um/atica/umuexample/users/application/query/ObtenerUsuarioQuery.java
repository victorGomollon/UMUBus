package es.um.atica.umuexample.users.application.query;

import java.util.Optional;

import es.um.atica.umuexample.users.domain.model.Usuario;
import lombok.Getter;
import es.um.atica.umubus.domain.cqrs.Query;

@Getter
public class ObtenerUsuarioQuery extends Query<Optional<Usuario>> {
    private String userId;
    private ObtenerUsuarioQuery(String userId) {
        this.userId = userId;
    }
    public static ObtenerUsuarioQuery of(String userId) {
        return new ObtenerUsuarioQuery(userId);
    }
}

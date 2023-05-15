package es.um.atica.umuexample.usuarios.application.query;

import java.util.Optional;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umubus.domain.cqrs.Query;

public class ObtenerUsuarioQuery extends Query<Optional<Usuario>> {
    private String usuarioId;
    private ObtenerUsuarioQuery(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    public static ObtenerUsuarioQuery of(String usuarioId) {
        return new ObtenerUsuarioQuery(usuarioId);
    }

    public String getUsuarioId() { return usuarioId; }
}

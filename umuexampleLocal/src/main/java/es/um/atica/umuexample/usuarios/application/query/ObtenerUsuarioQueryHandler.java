package es.um.atica.umuexample.usuarios.application.query;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioReadRepository;
import es.um.atica.umubus.domain.cqrs.QueryHandler;

@Component
public class ObtenerUsuarioQueryHandler implements QueryHandler<Optional<Usuario>,ObtenerUsuarioQuery> {

    @Autowired
    private UsuarioReadRepository usuariosRepository;

    @Override
    public Optional<Usuario> handle(ObtenerUsuarioQuery query) throws Exception {
        return usuariosRepository.findUsuario(query.getUsuarioId());
    }
}

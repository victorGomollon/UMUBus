package es.um.atica.umubus_pruebas.users.application.query;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_pruebas.users.domain.repository.UsuarioReadRepository;
import es.um.atica.umubus_lib.domain.cqrs.QueryHandler;

@Component
public class ObtenerUsuarioQueryHandler implements QueryHandler<Optional<Usuario>,ObtenerUsuarioQuery> {

    @Autowired
    private UsuarioReadRepository usersRepository;

    @Override
    public Optional<Usuario> handle(ObtenerUsuarioQuery query) throws Exception {
        return usersRepository.findUser(query.getUserId());
    }
}

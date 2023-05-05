package es.um.atica.umuexample.users.application.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umuexample.users.domain.repository.UsuarioReadRepository;
import es.um.atica.umubus.domain.cqrs.QueryHandler;

@Component
public class ObtenerUsuariosQueryHandler implements QueryHandler<List<Usuario>,ObtenerUsuariosQuery> {

	@Autowired
    private UsuarioReadRepository usersRepository;
	
    @Override
    public List<Usuario> handle(ObtenerUsuariosQuery query) throws Exception {
        return usersRepository.findAllUsers();
    }
}

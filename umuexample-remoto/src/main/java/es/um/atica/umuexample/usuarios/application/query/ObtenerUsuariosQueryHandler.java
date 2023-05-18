package es.um.atica.umuexample.usuarios.application.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioReadRepository;
import es.um.atica.umubus.domain.cqrs.QueryHandler;

@Component
public class ObtenerUsuariosQueryHandler implements QueryHandler<List<Usuario>,ObtenerUsuariosQuery> {

	@Autowired
    private UsuarioReadRepository usuariosRepository;
	
    @Override
    public List<Usuario> handle(ObtenerUsuariosQuery query) throws Exception {
        return usuariosRepository.findAllUsuarios();
    }
}

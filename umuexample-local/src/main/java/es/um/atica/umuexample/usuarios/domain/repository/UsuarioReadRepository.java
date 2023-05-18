package es.um.atica.umuexample.usuarios.domain.repository;

import java.util.List;
import java.util.Optional;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;

public interface UsuarioReadRepository {
    
    public List<Usuario> findAllUsuarios();
    public Optional<Usuario> findUsuario(String usuarioId);

}

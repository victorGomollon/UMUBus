package es.um.atica.umuexample.users.domain.repository;

import java.util.List;
import java.util.Optional;

import es.um.atica.umuexample.users.domain.model.Usuario;

public interface UsuarioReadRepository {
    
    public List<Usuario> findAllUsers();
    public Optional<Usuario> findUser(String userId);

}

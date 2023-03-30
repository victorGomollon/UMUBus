package es.um.atica.umubus_pruebas.users.domain.repository;

import java.util.List;
import java.util.Optional;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;

public interface UsuarioReadRepository {
    
    public List<Usuario> findAllUsers();
    public Optional<Usuario> findUser(String userId);

}

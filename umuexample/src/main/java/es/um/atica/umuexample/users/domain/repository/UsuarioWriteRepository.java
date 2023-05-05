package es.um.atica.umuexample.users.domain.repository;

import es.um.atica.umuexample.users.domain.model.Usuario;

public interface UsuarioWriteRepository {
    
    public void saveUser(Usuario user);

    public void deleteUser(Usuario user);

}

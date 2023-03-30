package es.um.atica.umubus_pruebas.users.domain.repository;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;

public interface UsuarioWriteRepository {
    
    public void saveUser(Usuario user);

    public void deleteUser(Usuario user);

}

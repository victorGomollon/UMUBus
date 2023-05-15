package es.um.atica.umuexample.usuarios.domain.repository;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;

public interface UsuarioWriteRepository {
    
    public void saveUsuario(Usuario usuario);

    public void deleteUsuario(Usuario usuario);

}

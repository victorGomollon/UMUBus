package es.um.atica.umuexample.usuarios.adapters.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioReadRepository;
import es.um.atica.umuexample.usuarios.domain.repository.UsuarioWriteRepository;

@Service
@org.springframework.context.annotation.Primary
public class DBUsuariosReadWriteRepository implements UsuarioReadRepository,UsuarioWriteRepository {

    @Autowired
    private JPAUsuariosReadRepository jpaUsuariosReadRepository;
    

    @Override
    public void saveUsuario(Usuario usuario) {
    	jpaUsuariosReadRepository.save(UsuarioEntity.of(usuario));
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
    	jpaUsuariosReadRepository.delete(UsuarioEntity.of(usuario));
    }

    @Override
    public Optional<Usuario> findUsuario(String usuarioId) {
        return jpaUsuariosReadRepository.findById(usuarioId).map(UsuarioEntity::toModel);
    }

    @Override
    public List<Usuario> findAllUsuarios() {
        return StreamSupport.stream(jpaUsuariosReadRepository.findAll().spliterator(), false)
            .map(UsuarioEntity::toModel).collect(Collectors.toList());
    }
    
}

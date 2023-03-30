package es.um.atica.umubus_pruebas.users.adapters.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import es.um.atica.umubus_pruebas.users.domain.repository.UsuarioReadRepository;
import es.um.atica.umubus_pruebas.users.domain.repository.UsuarioWriteRepository;

@Service
@org.springframework.context.annotation.Primary
public class DBUsersReadWriteRepository implements UsuarioReadRepository,UsuarioWriteRepository {

    @Autowired
    private JPAUsersReadRepository jpaUsersReadRepository;
    

    @Override
    public void saveUser(Usuario user) {
        jpaUsersReadRepository.save(UserEntity.of(user));
    }

    @Override
    public void deleteUser(Usuario user) {
        jpaUsersReadRepository.delete(UserEntity.of(user));
    }

    @Override
    public Optional<Usuario> findUser(String userId) {
        return jpaUsersReadRepository.findById(userId).map(UserEntity::toModel);
    }

    @Override
    public List<Usuario> findAllUsers() {
        return StreamSupport.stream(jpaUsersReadRepository.findAll().spliterator(), false)
            .map(UserEntity::toModel).collect(Collectors.toList());
    }
    
}

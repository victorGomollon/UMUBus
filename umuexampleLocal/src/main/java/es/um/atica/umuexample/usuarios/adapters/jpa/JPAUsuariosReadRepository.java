package es.um.atica.umuexample.usuarios.adapters.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAUsuariosReadRepository extends JpaRepository<UsuarioEntity,String> {
    
}

package es.um.atica.umuexample.usuarios.adapters.rest.dto;

import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import lombok.Builder;
import lombok.Getter;

@Builder
public class UsuarioDTO {
    @Getter
    private String id;
    @Getter
    private String name;
    @Getter
    private Integer age;

    public static UsuarioDTO of(Usuario usuario) {
        return UsuarioDTO
            .builder()
            .id(usuario.getId())
            .name(usuario.getName())
            .age(usuario.getAge())
            .build();
    }
}

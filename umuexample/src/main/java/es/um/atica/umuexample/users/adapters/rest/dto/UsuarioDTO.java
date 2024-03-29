package es.um.atica.umuexample.users.adapters.rest.dto;

import es.um.atica.umuexample.users.domain.model.Usuario;
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

    public static UsuarioDTO of(Usuario user) {
        return UsuarioDTO
            .builder()
            .id(user.getId())
            .name(user.getName())
            .age(user.getAge())
            .build();
    }
}

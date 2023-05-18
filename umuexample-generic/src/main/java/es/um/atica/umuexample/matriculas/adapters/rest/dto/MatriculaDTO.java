package es.um.atica.umuexample.matriculas.adapters.rest.dto;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import lombok.Builder;
import lombok.Getter;

@Builder
public class MatriculaDTO {
    @Getter
    private String id;
    @Getter
    private String name;
    @Getter
    private String asignatura;
    @Getter
    private String usuario;

    public static MatriculaDTO of(Matricula matricula) {
        return MatriculaDTO
            .builder()
            .id(matricula.getId())
            .name(matricula.getName())
            .asignatura(matricula.getAsignatura())
            .usuario(matricula.getUsuario())
            .build();
    }
}

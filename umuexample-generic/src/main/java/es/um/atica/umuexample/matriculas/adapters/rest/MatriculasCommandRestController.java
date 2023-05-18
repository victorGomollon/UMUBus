package es.um.atica.umuexample.matriculas.adapters.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umuexample.matriculas.adapters.rest.dto.MatriculaDTO;
import es.um.atica.umuexample.matriculas.application.command.CrearMatriculaCommand;
import es.um.atica.umuexample.matriculas.application.command.EliminarMatriculaCommand;
import es.um.atica.umubus.domain.cqrs.CommandBus;
import es.um.atica.umubus.domain.cqrs.SyncCommandBus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Matriculas Endpoints")
@RestController
@RequestMapping(value="/umuexample")
public class MatriculasCommandRestController {
    
    @Autowired
    private CommandBus commandBus;

    @Autowired
    private SyncCommandBus syncCommandBus;

    @Operation(
        description = "Crear nuevo matricula",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_post"),
            @ApiResponse(responseCode = "409", ref = "conflict"),
            @ApiResponse(responseCode = "200", ref = "ok_matricula"),
        }
    )
    @PostMapping("/matricula/{id}")
    public ResponseEntity<MatriculaDTO> createMatricula(@PathVariable(name="id",required = true) String matriculaId,
        @RequestBody MatriculaDTO mat, HttpServletRequest req) throws Exception {
        syncCommandBus.handle(CrearMatriculaCommand.of(matriculaId, mat.getName(), mat.getAsignatura(), mat.getUsuario()));
        return new ResponseEntity<>(MatriculaDTO.builder().id(matriculaId).build(), HttpStatus.OK);
    }

    @Operation(
        description = "Eliminar matricula existente",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_delete"),
            @ApiResponse(responseCode = "200", ref = "ok_matricula"),
        }
    )
    @DeleteMapping("/matricula/{id}")
    public ResponseEntity<MatriculaDTO> deleteMatricula(@PathVariable(name="id",required = true) String matriculaId) throws Exception {
        commandBus.handle(EliminarMatriculaCommand.of(matriculaId));
        return new ResponseEntity<>(MatriculaDTO.builder().id(matriculaId).build(), HttpStatus.OK);
    }

}

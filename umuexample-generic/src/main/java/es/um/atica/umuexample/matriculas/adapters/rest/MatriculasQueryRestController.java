package es.um.atica.umuexample.matriculas.adapters.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umuexample.matriculas.adapters.rest.dto.MatriculaDTO;
import es.um.atica.umuexample.matriculas.application.query.ObtenerMatriculaQuery;
import es.um.atica.umuexample.matriculas.application.query.ObtenerMatriculasQuery;
import es.um.atica.umubus.domain.cqrs.QueryBus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Matriculas Endpoints")
@RestController
@RequestMapping(value="/umuexample")
public class MatriculasQueryRestController {
    
    @Autowired
    private QueryBus queryBus;

    @Operation(
        description = "Obtener todos los matriculas",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_get"),
            @ApiResponse(responseCode = "200", ref = "ok_matriculas"),
        }
    )
    @GetMapping("/matricula")
    public ResponseEntity<List<MatriculaDTO>> allMatriculas() throws Exception {
        return new ResponseEntity<>(queryBus.handle(ObtenerMatriculasQuery.of()).stream()
    			.map( MatriculaDTO::of ).collect( Collectors.toList() ), HttpStatus.OK );
    }

    @Operation(
        description = "Obtener matricula",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_get"),
            @ApiResponse(responseCode = "404", ref = "notfound"),
            @ApiResponse(responseCode = "200", ref = "ok_matricula"),
        }
    )
    @GetMapping("/matricula/{id}")
    public ResponseEntity<MatriculaDTO> matriculaDetail(@PathVariable(name="id",required = true) String matriculaId) throws Exception {
        return new ResponseEntity<>(queryBus.handle(ObtenerMatriculaQuery.of(matriculaId)).map(MatriculaDTO::of)
            .orElseThrow(() -> new NoSuchElementException(String.format("Matricula not found %s",matriculaId))), HttpStatus.OK);
    }
}

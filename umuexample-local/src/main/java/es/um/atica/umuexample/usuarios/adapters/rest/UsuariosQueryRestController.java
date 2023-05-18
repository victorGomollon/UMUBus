package es.um.atica.umuexample.usuarios.adapters.rest;

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

import es.um.atica.umuexample.usuarios.adapters.rest.dto.UsuarioDTO;
import es.um.atica.umuexample.usuarios.application.query.ObtenerUsuarioQuery;
import es.um.atica.umuexample.usuarios.application.query.ObtenerUsuariosQuery;
import es.um.atica.umubus.domain.cqrs.QueryBus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios Endpoints")
@RestController
@RequestMapping(value="/umuexample")
public class UsuariosQueryRestController {
    
    @Autowired
    private QueryBus queryBus;

    @Operation(
        description = "Obtener todos los usuarios",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_get"),
            @ApiResponse(responseCode = "200", ref = "ok_usuarios"),
        }
    )
    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioDTO>> allUsuarios() throws Exception {
        return new ResponseEntity<>(queryBus.handle(ObtenerUsuariosQuery.of()).stream()
    			.map( UsuarioDTO::of ).collect( Collectors.toList() ), HttpStatus.OK );
    }

    @Operation(
        description = "Obtener usuario",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_get"),
            @ApiResponse(responseCode = "404", ref = "notfound"),
            @ApiResponse(responseCode = "200", ref = "ok_usuario"),
        }
    )
    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> usuarioDetail(@PathVariable(name="id",required = true) String usuarioId) throws Exception {
        return new ResponseEntity<>(queryBus.handle(ObtenerUsuarioQuery.of(usuarioId)).map(UsuarioDTO::of)
            .orElseThrow(() -> new NoSuchElementException(String.format("Usuario not found %s",usuarioId))), HttpStatus.OK);
    }
}

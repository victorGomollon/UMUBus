package es.um.atica.umuexample.users.adapters.rest;

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

import es.um.atica.umuexample.users.adapters.rest.dto.UsuarioDTO;
import es.um.atica.umuexample.users.application.query.ObtenerUsuarioQuery;
import es.um.atica.umuexample.users.application.query.ObtenerUsuariosQuery;
import es.um.atica.umubus.domain.cqrs.QueryBus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users Endpoints")
@RestController
@RequestMapping(value="/pruebas/v1")
public class UsuariosQueryRestController {
    
    @Autowired
    private QueryBus queryBus;

    @Operation(
        description = "Get all user list paginated",
        responses = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden_get"),
            @ApiResponse(responseCode = "200", ref = "ok_users"),
        }
    )
    @GetMapping("/user")
    public ResponseEntity<List<UsuarioDTO>> allUsers() throws Exception {
        return new ResponseEntity<>(queryBus.handle(ObtenerUsuariosQuery.of()).stream()
    			.map( UsuarioDTO::of ).collect( Collectors.toList() ), HttpStatus.OK );
    }

    @Operation(
        description = "Get user detail",
        responses = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden_get"),
            @ApiResponse(responseCode = "404", ref = "notfound"),
            @ApiResponse(responseCode = "200", ref = "ok_user"),
        }
    )
    @GetMapping("/user/{id}")
    public ResponseEntity<UsuarioDTO> userDetail(@PathVariable(name="id",required = true) String userId) throws Exception {
        return new ResponseEntity<>(queryBus.handle(ObtenerUsuarioQuery.of(userId)).map(UsuarioDTO::of)
            .orElseThrow(() -> new NoSuchElementException(String.format("User not found %s",userId))), HttpStatus.OK);
    }
}

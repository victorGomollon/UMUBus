package es.um.atica.umuexample.usuarios.adapters.rest;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umuexample.usuarios.adapters.rest.dto.UsuarioDTO;
import es.um.atica.umuexample.usuarios.application.command.CrearUsuarioCommand;
import es.um.atica.umuexample.usuarios.application.command.EliminarUsuarioCommand;
import es.um.atica.umuexample.usuarios.application.command.ActualizarUsuarioCommand;
import es.um.atica.umubus.domain.cqrs.CommandBus;
import es.um.atica.umubus.domain.cqrs.SyncCommandBus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios Endpoints")
@RestController
@RequestMapping(value="/umuexample")
public class UsuariosCommandRestController {
    
    @Autowired
    private CommandBus commandBus;

    @Autowired
    private SyncCommandBus syncCommandBus;

    @Operation(
        description = "Crear nuevo usuario",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_post"),
            @ApiResponse(responseCode = "409", ref = "conflict"),
            @ApiResponse(responseCode = "200", ref = "ok_usuario"),
        }
    )
    @PostMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> createUsuario(@PathVariable(name="id",required = true) String usuarioId,
        @RequestBody UsuarioDTO usr, HttpServletRequest req) throws Exception {
        syncCommandBus.handle(CrearUsuarioCommand.of(usuarioId, usr.getName(), usr.getAge()));
        return new ResponseEntity<>(UsuarioDTO.builder().id(usuarioId).build(), HttpStatus.OK);
    }

    @Operation(
        description = "Actualizar usuario existente",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_put"),
            @ApiResponse(responseCode = "200", ref = "ok_usuario"),
        }
    )
    @PutMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable(name="id",required = true) String usuarioId,
        @RequestBody UsuarioDTO usr) throws Exception {
        commandBus.handle(ActualizarUsuarioCommand.of(usuarioId, Optional.ofNullable(usr.getName()), Optional.ofNullable(usr.getAge())));
        return new ResponseEntity<>(UsuarioDTO.builder().id(usuarioId).build(), HttpStatus.OK);
    }

    @Operation(
        description = "Eliminar usuario existente",
        responses = {
            @ApiResponse(responseCode = "403", ref = "forbidden_delete"),
            @ApiResponse(responseCode = "200", ref = "ok_usuario"),
        }
    )
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> deleteUsuario(@PathVariable(name="id",required = true) String usuarioId) throws Exception {
        commandBus.handle(EliminarUsuarioCommand.of(usuarioId));
        return new ResponseEntity<>(UsuarioDTO.builder().id(usuarioId).build(), HttpStatus.OK);
    }

}

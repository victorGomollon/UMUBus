package es.um.atica.umubus_pruebas.users.adapters.rest;

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

import es.um.atica.umubus_pruebas.users.adapters.rest.dto.UsuarioDTO;
import es.um.atica.umubus_pruebas.users.application.command.CrearUsuarioCommand;
import es.um.atica.umubus_pruebas.users.application.command.EliminarUsuarioCommand;
import es.um.atica.umubus_pruebas.users.application.command.ActualizarUsuarioCommand;
import es.um.atica.umubus_lib.domain.cqrs.CommandBus;
import es.um.atica.umubus_lib.domain.cqrs.SyncCommandBus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users Endpoints")
@RestController
@RequestMapping(value="/pruebas/v1")
public class UsuariosCommandRestController {
    
    @Autowired
    private CommandBus commandBus;

    @Autowired
    private SyncCommandBus syncCommandBus;

    @Operation(
        description = "Create new user",
        responses = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden_post"),
            @ApiResponse(responseCode = "409", ref = "conflict"),
            @ApiResponse(responseCode = "200", ref = "ok_user"),
        }
    )
    @PostMapping("/user/{id}")
    public ResponseEntity<UsuarioDTO> createUser(@PathVariable(name="id",required = true) String userId,
        @RequestBody UsuarioDTO usr, HttpServletRequest req) throws Exception {
        syncCommandBus.handle(CrearUsuarioCommand.of(userId, usr.getName(), usr.getAge()));
        return new ResponseEntity<>(UsuarioDTO.builder().id(userId).build(), HttpStatus.OK);
    }

    @Operation(
        description = "Update existing user",
        responses = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden_put"),
            @ApiResponse(responseCode = "200", ref = "ok_user"),
        }
    )
    @PutMapping("/user/{id}")
    public ResponseEntity<UsuarioDTO> updateUser(@PathVariable(name="id",required = true) String userId,
        @RequestBody UsuarioDTO usr) throws Exception {
        commandBus.handle(ActualizarUsuarioCommand.of(userId, Optional.ofNullable(usr.getName()), Optional.ofNullable(usr.getAge())));
        return new ResponseEntity<>(UsuarioDTO.builder().id(userId).build(), HttpStatus.OK);
    }

    @Operation(
        description = "Delete existing user",
        responses = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "403", ref = "forbidden_delete"),
            @ApiResponse(responseCode = "200", ref = "ok_user"),
        }
    )
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UsuarioDTO> deleteUser(@PathVariable(name="id",required = true) String userId) throws Exception {
        commandBus.handle(EliminarUsuarioCommand.of(userId));
        return new ResponseEntity<>(UsuarioDTO.builder().id(userId).build(), HttpStatus.OK);
    }

}

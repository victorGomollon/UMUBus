package es.um.atica.umuexample.usuarios.adapters.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umuexample.usuarios.adapters.rest.dto.UsuarioDTO;
import es.um.atica.umuexample.usuarios.domain.event.CrearUsuarioEvent;
import es.um.atica.umuexample.usuarios.domain.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuarios Endpoints")
@RestController
@RequestMapping(value="/umuexample")
public class PruebaUnRest {
    @Autowired
    private EventBus eventBus;
    
    @Operation(
            description = "Probar evento crear usuario",
            responses = {
                @ApiResponse(responseCode = "403", ref = "forbidden_post"),
                @ApiResponse(responseCode = "409", ref = "conflict"),
                @ApiResponse(responseCode = "200", ref = "ok_usuario"),
            }
        )
        @PostMapping("/usuarioEvent/{id}")
        public ResponseEntity<UsuarioDTO> createUsuario(@PathVariable(name="id",required = true) String usuarioId,
            HttpServletRequest req) throws Exception {
		Usuario usuario = Usuario.of(usuarioId,"Pedro", 30);
		eventBus.publish(CrearUsuarioEvent.of(usuario));
		return new ResponseEntity<>(UsuarioDTO.builder().id(usuarioId).build(), HttpStatus.OK);
        }
}
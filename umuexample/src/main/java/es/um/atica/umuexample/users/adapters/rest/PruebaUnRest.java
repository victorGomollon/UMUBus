package es.um.atica.umuexample.users.adapters.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umubus.domain.events.IEventBusFactory;
import es.um.atica.umuexample.users.adapters.rest.dto.UsuarioDTO;
import es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent;
import es.um.atica.umuexample.users.domain.model.Usuario;
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
    private IEventBusFactory eventBusFactory;
    
    @Operation(
            description = "Probar evento crear usuario",
            responses = {
                @ApiResponse(responseCode = "403", ref = "forbidden_post"),
                @ApiResponse(responseCode = "409", ref = "conflict"),
                @ApiResponse(responseCode = "200", ref = "ok_user"),
            }
        )
        @PostMapping("/userEvent/{id}")
        public ResponseEntity<UsuarioDTO> createUser(@PathVariable(name="id",required = true) String userId,
            HttpServletRequest req) throws Exception {
		Usuario user = Usuario.of(userId,"Pedro", 30);
		eventBusFactory.getEventBus().publish(CrearUsuarioEvent.of(user));
		return new ResponseEntity<>(UsuarioDTO.builder().id(userId).build(), HttpStatus.OK);
        }
}
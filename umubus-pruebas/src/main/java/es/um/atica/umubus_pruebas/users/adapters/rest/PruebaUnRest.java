package es.um.atica.umubus_pruebas.users.adapters.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umubus_lib.domain.events.EventBus;
import es.um.atica.umubus_pruebas.users.domain.event.CrearUsuarioEvent;
import es.um.atica.umubus_pruebas.users.domain.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(
        value = "/producer",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PruebaUnRest {
    @Autowired
    private EventBus eventBus;
	@PostMapping
	public ResponseEntity getAllUserById(@RequestBody String id) {
		Usuario user = Usuario.of(id,"Pedro", 30);
		eventBus.publish(CrearUsuarioEvent.of(user));
		return ResponseEntity.accepted().build();
	}
}
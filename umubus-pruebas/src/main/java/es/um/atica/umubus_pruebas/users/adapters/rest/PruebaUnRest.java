package es.um.atica.umubus_pruebas.users.adapters.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umubus_lib.domain.queues.IPublisherChannel;
import es.um.atica.umubus_lib.domain.queues.MessageEvent;
import es.um.atica.umubus_pruebas.users.domain.event.CrearUsuarioEvent;
import es.um.atica.umubus_pruebas.users.domain.model.Usuario;
import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(
        value = "/producer",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor
public class PruebaUnRest {
	private final IPublisherChannel publisher;
	@PostMapping
	public ResponseEntity getAllUserById(@RequestBody String id) {
		Usuario user = Usuario.of(id,"PEdro", 30);
		CrearUsuarioEvent event = CrearUsuarioEvent.of(user);
		MessageEvent messageEvent = MessageEvent.of(event);
		publisher.sendMessage(messageEvent);
		return ResponseEntity.accepted().build();
	}
}

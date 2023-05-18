package es.um.atica.umuexample.config;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.Message;

import es.um.atica.umubus.adapters.events.EventAckConsumer;
import es.um.atica.umubus.adapters.events.RabbitEventBus;
import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umuexample.usuarios.adapters.events.ActualizarUsuarioConsumer;
import es.um.atica.umuexample.usuarios.adapters.events.CrearMatriculaConsumer;
import es.um.atica.umuexample.usuarios.adapters.events.CrearUsuarioConsumer;
import es.um.atica.umuexample.usuarios.adapters.events.CrearUsuarioConsumerOther;
import es.um.atica.umuexample.usuarios.adapters.events.EliminarUsuarioConsumer;
import es.um.atica.umuexample.usuarios.domain.event.ActualizarUsuarioEvent;
import es.um.atica.umuexample.usuarios.domain.event.CrearUsuarioEvent;
import es.um.atica.umuexample.usuarios.domain.event.EliminarUsuarioEvent;

@Configuration
@ComponentScan(basePackages = {"es.um.atica.umuexample","es.um.atica.umubus"})
@EnableJpaRepositories( basePackages = {"es.um.atica.umuexample","es.um.atica.umubus"} )
@EntityScan({"es.um.atica.umuexample","es.um.atica.umubus"})
public class UmuBusRemoteConfig {
	@Autowired
	private RabbitEventBus eventBus;

	@Autowired
	private CrearUsuarioConsumer crearUsuarioConsumer;

	@Autowired
	private CrearUsuarioConsumerOther crearUsuarioConsumerOther;

	@Autowired
	private EliminarUsuarioConsumer eliminarUsuarioConsumer;
	
	@Autowired
	private CrearMatriculaConsumer crearMatriculaConsumer;
	
	@Bean
	public Supplier<Message<Event>> eventProcessor() {
		return eventBus;
	}

	@Bean
	public Consumer<Message<CrearUsuarioEvent>> crearConsumer() {
		return crearUsuarioConsumer;
	}

	@Bean
	public Consumer<Message<CrearUsuarioEvent>> crearConsumer2() {
		return crearUsuarioConsumerOther;
	}

	@Bean
	public Consumer<Message<EliminarUsuarioEvent>> eliminarConsumer() {
		return eliminarUsuarioConsumer;
	}

	@Bean
	public Consumer<Message<Event>> crearMatConsumer() {
		return crearMatriculaConsumer;
	}
	
	@Bean
	public EventBus eventTypeResolver() {
		return eventBus;
	}
	
	//Siempre que sea remoto, tiene que llevar este evento definido, ya que se encarga de validar que se reciben los eventos
	@Autowired
	private EventAckConsumer eventAckConsumer;

	@Bean
	public Consumer<Message<Event>> ackConsumer() {
		return eventAckConsumer;
	}
}

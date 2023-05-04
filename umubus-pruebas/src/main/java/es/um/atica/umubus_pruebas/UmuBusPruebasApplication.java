package es.um.atica.umubus_pruebas;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.Message;
import org.springframework.web.filter.ForwardedHeaderFilter;

import es.um.atica.umubus_lib.adapters.events.RabbitEventBus;
import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventBus;
import es.um.atica.umubus_pruebas.users.adapters.events.UsuarioAllConsumer;
import es.um.atica.umubus_pruebas.users.adapters.events.CrearUsuarioConsumer;
import es.um.atica.umubus_pruebas.users.adapters.events.CrearUsuarioConsumerOther;
import es.um.atica.umubus_pruebas.users.adapters.events.EliminarUsuarioConsumer;
import es.um.atica.umubus_pruebas.users.domain.event.CrearUsuarioEvent;
import es.um.atica.umubus_pruebas.users.domain.event.EliminarUsuarioEvent;
import es.um.atica.umubus_pruebas.users.domain.event.UsuarioEvent;

@SpringBootApplication
@ComponentScan(basePackages = {"es.um.atica.umubus_pruebas","es.um.atica.umubus_lib"})
public class UmuBusPruebasApplication {

	@Bean
	public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(UmuBusPruebasApplication.class, args);
	}
	
	//No quiero tener que depender de declarar los beans al arrancar...meterlo en un fichero de configuración (Por lo menos el supplier)
	@Autowired
	private RabbitEventBus eventBus;

	@Autowired
	private CrearUsuarioConsumer crearUsuarioConsumer;

	@Autowired
	private CrearUsuarioConsumerOther crearUsuarioConsumerOther;

	@Autowired
	private UsuarioAllConsumer usuarioAllConsumer;

	@Autowired
	private EliminarUsuarioConsumer eliminarUsuarioConsumer;
	
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
	public Consumer<Message<UsuarioEvent>> allConsumer() {
		return usuarioAllConsumer;
	}

	@Bean
	public Consumer<Message<EliminarUsuarioEvent>> eliminarConsumer() {
		return eliminarUsuarioConsumer;
	}

	@Bean
	public EventBus eventTypeResolver() {
		return eventBus;
	}

}

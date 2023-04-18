package es.um.atica.umubus_pruebas;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.ForwardedHeaderFilter;

import es.um.atica.umubus_lib.adapters.events.RabbitEventBus;
import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventBus;
import es.um.atica.umubus_pruebas.users.adapters.events.UserAllConsumer;
import es.um.atica.umubus_pruebas.users.adapters.events.UserCreatedConsumer;
import es.um.atica.umubus_pruebas.users.adapters.events.UserCreatedConsumerOther;
import es.um.atica.umubus_pruebas.users.adapters.events.UserDeletedConsumer;
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
	private RabbitEventBus springEventBus;

	@Autowired
	private UserCreatedConsumer userCreatedConsumer;

	@Autowired
	private UserCreatedConsumerOther userCreatedConsumerOther;

	@Autowired
	private UserAllConsumer userAllConsumer;

	@Autowired
	private UserDeletedConsumer userDeletedConsumer;
	
	@Bean
	public Supplier<Event> eventProducer() {
		return springEventBus;
	}

	@Bean
	public Consumer<CrearUsuarioEvent> createdConsumer() {
		return userCreatedConsumer;
	}

	@Bean
	public Consumer<CrearUsuarioEvent> createdConsumer2() {
		return userCreatedConsumerOther;
	}

	@Bean
	public Consumer<UsuarioEvent> allConsumer() {
		return userAllConsumer;
	}

	@Bean
	public Consumer<EliminarUsuarioEvent> deletedConsumer() {
		return userDeletedConsumer;
	}

	@Bean
	public EventBus eventTypeResolver() {
		return springEventBus;
	}

}

package es.um.atica.umuexample;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.Message;
import org.springframework.web.filter.ForwardedHeaderFilter;

import es.um.atica.umubus.adapters.events.EventAckConsumer;
import es.um.atica.umubus.adapters.events.RabbitEventBus;
import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umuexample.users.adapters.events.ActualizarUsuarioConsumer;
import es.um.atica.umuexample.users.adapters.events.CrearUsuarioConsumer;
import es.um.atica.umuexample.users.adapters.events.CrearUsuarioConsumerOther;
import es.um.atica.umuexample.users.adapters.events.EliminarUsuarioConsumer;
import es.um.atica.umuexample.users.domain.event.ActualizarUsuarioEvent;
import es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent;
import es.um.atica.umuexample.users.domain.event.EliminarUsuarioEvent;

@SpringBootApplication(scanBasePackages = {"es.um.atica.umuexample","es.um.atica.umubus"})
@EnableJpaRepositories( basePackages = {"es.um.atica.umuexample","es.um.atica.umubus"} )
@EntityScan({"es.um.atica.umuexample","es.um.atica.umubus"})
public class UmuBusPruebasApplication {

	@Bean
	public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(UmuBusPruebasApplication.class, args);
	}
	
	@Autowired
	private RabbitEventBus eventBus;

	@Autowired
	private CrearUsuarioConsumer crearUsuarioConsumer;

	@Autowired
	private CrearUsuarioConsumerOther crearUsuarioConsumerOther;

	@Autowired
	private ActualizarUsuarioConsumer actualizarUsuarioConsumer;

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
	public Consumer<Message<ActualizarUsuarioEvent>> actualizarConsumer() {
		return actualizarUsuarioConsumer;
	}
	
	@Bean
	public Consumer<Message<EliminarUsuarioEvent>> eliminarConsumer() {
		return eliminarUsuarioConsumer;
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

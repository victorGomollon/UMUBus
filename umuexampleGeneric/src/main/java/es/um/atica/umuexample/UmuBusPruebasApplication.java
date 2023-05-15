package es.um.atica.umuexample;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.Message;
import org.springframework.web.filter.ForwardedHeaderFilter;

import es.um.atica.umubus.adapters.events.RabbitEventBus;
import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umuexample.matriculas.adapters.events.CrearUsuarioConsumer;
import es.um.atica.umuexample.matriculas.domain.event.CrearMatriculaEvent;

@SpringBootApplication
@ComponentScan(basePackages = {"es.um.atica.umuexample","es.um.atica.umubus"})
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
	private CrearUsuarioConsumer crearMatriculaConsumer;

	@Bean
	public Supplier<Message<Event>> eventProcessor() {
		return eventBus;
	}

	@Bean
	public Consumer<Message<Event>> crearConsumer() {
		return crearMatriculaConsumer;
	}

	@Bean
	public EventBus eventTypeResolver() {
		return eventBus;
	}

}

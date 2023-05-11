package es.um.atica.umuexample;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.atica.umubus.adapters.events.RabbitEventBus;
import es.um.atica.umubus.domain.events.Event;
 
@Testcontainers(disabledWithoutDocker = true)
@AutoConfigureMockMvc
@TestPropertySource("classpath:test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {SpringConfigurationTest.Initializer.class})
public abstract class SpringConfigurationTest {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper objectMapper;

    private static final int RABBITMQ_DEFAULT_PORT = 5672;
    private static final int RABBITMQ_DEFAULT_HTTP_PORT = 15672;

    @Container
    static final RabbitMQContainer rabbitMQ;

    static {
        rabbitMQ = new RabbitMQContainer("rabbitmq:3.8.9-management")
            .withExposedPorts(RABBITMQ_DEFAULT_PORT,RABBITMQ_DEFAULT_HTTP_PORT)
            .waitingFor(Wait.forListeningPort());
        rabbitMQ.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    	@Bean
    	public Supplier<Message<Event>> eventProcessor() {
    		return new RabbitEventBus();
    	}
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(configurableApplicationContext,
                "spring.cloud.function.definition=eventProcessor;crearConsumer;crearConsumer2;actualizarConsumer;eliminarConsumer",
                "spring.cloud.stream.rabbit.bindings.eventProcessor-out-0.producer.routingKeyExpression=@eventTypeResolver.eventType(payload)",
                "spring.rabbitmq.host=" + rabbitMQ.getHost(),
                "spring.rabbitmq.port=" + rabbitMQ.getMappedPort(RABBITMQ_DEFAULT_PORT),
                "spring.rabbitmq.username=" + rabbitMQ.getAdminUsername(),
                "spring.rabbitmq.password=" + rabbitMQ.getAdminPassword());
        }
    }

}

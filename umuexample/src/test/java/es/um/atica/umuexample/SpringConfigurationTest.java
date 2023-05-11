package es.um.atica.umuexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import com.fasterxml.jackson.databind.ObjectMapper;

@Testcontainers(disabledWithoutDocker = true)
@AutoConfigureMockMvc
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(initializers = {SpringConfigurationTest.Initializer.class})
public abstract class SpringConfigurationTest {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper objectMapper;

    private static final int RABBITMQ_DEFAULT_PORT = 5672;
    private static final int RABBITMQ_DEFAULT_HTTP_PORT = 15672;

    static final RabbitMQContainer rabbitMQ;

    static {
        rabbitMQ = new RabbitMQContainer("rabbitmq:3.8.9-management")
            .withExposedPorts(RABBITMQ_DEFAULT_PORT,RABBITMQ_DEFAULT_HTTP_PORT)
            .waitingFor(Wait.forListeningPort());
        rabbitMQ.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(configurableApplicationContext,
                "spring.cloud.function.definition=eventProcessor;crearConsumer;crearConsumer2;actualizarConsumer;eliminarConsumer",
                "spring.cloud.stream.rabbit.bindings.eventProcessor-out-0.producer.routingKeyExpression=@eventTypeResolver.eventType(payload)",
                "spring.cloud.stream.rabbit.bindings.crearConsumer-in-0.consumer.bindingRoutingKey=events.1.es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent",
                "spring.cloud.stream.rabbit.bindings.crearConsumer2-in-0.consumer.bindingRoutingKey=events.1.es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent",
                "spring.cloud.stream.rabbit.bindings.eliminarConsumer-in-0.consumer.bindingRoutingKey=events.1.es.um.atica.umuexample.users.domain.event.EliminarUsuarioEvent",
                "spring.cloud.stream.rabbit.bindings.actualizarConsumer-in-0.consumer.bindingRoutingKey=events.1.es.um.atica.umuexample.users.domain.event.ActualizarUsuarioEvent",
                "spring.rabbitmq.host=" + rabbitMQ.getHost(),
                "spring.rabbitmq.port=" + rabbitMQ.getMappedPort(RABBITMQ_DEFAULT_PORT),
                "spring.rabbitmq.username=" + rabbitMQ.getAdminUsername(),
                "spring.rabbitmq.password=" + rabbitMQ.getAdminPassword());
        }
    }

}

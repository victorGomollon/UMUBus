package es.um.atica.umubus_lib.domain.queues;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConsumerConfiguration {

	public static final String QUEUE_NAME = "UMUBUS-queue";

	public static final String EXCHANGE_NAME = "UMUBUS-exchange";

//	@Value("${spring.rabbitmq.host}")
	private String host = "localhost";

//	@Value("${spring.rabbitmq.port}")
	private Integer port = 5672;

//	@Value("${spring.rabbitmq.username}")
	private String user = "guest";

//	@Value("${spring.rabbitmq.password}")
	private String password = "guest";

//	@Value("${spring.rabbitmq.virtualhost}")
	private String virtualhost = "/";

//	private Map<String, Queue> queues = new HashMap<String, Queue>();
//	
//	private Map<String, Binding> Bindings = new HashMap<String, Binding>();
	
//	@Bean
//	public ConnectionFactory connectionFactoryConsumer() throws Exception {
//		final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setAddresses(host);
//		connectionFactory.setPort(port);
//		connectionFactory.setUsername(user);
//		connectionFactory.setPassword(password);
//		connectionFactory.setVirtualHost(virtualhost);
//		return connectionFactory;
//	}
//
//	//Listener del consumer
//	@Bean
//	public SimpleRabbitListenerContainerFactory customRabbitListenerContainerFactory() throws Exception {
//		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactoryConsumer());
//		factory.setMessageConverter(jsonMessageConverterConsumer());
//		return factory;
//	}
//	
//	@Bean
//	public MessageConverter jsonMessageConverterConsumer(){
//	    return new Jackson2JsonMessageConverter();
//	}
}

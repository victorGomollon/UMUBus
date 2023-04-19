package es.um.atica.umubus_lib.config.queues;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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

import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitConfiguration {

	public static final String QUEUE_NAME = "UMUBUS-queue";

	public static final String EXCHANGE_NAME = "UMUBUS-exchange";
	
	public static final long INITIAL_INTERVAL = 3;
	
	public static final long MAX_INTERVAL = 252000;
	
	public static final long MULTIPLIER = 2;

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
	
	@Bean
	public ConnectionFactory connectionFactory(){
		final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(user);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualhost);
		return connectionFactory;
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME, true, false);
	}
	@Bean
	public Queue queue() {
		return new Queue (QUEUE_NAME, true);
	}
	
	@Bean
	public Binding binding(Queue testeQueue, TopicExchange exchange) {
		return BindingBuilder.bind(testeQueue).to(exchange).with(QUEUE_NAME);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

		ExponentialBackOffPolicy policy = new ExponentialBackOffPolicy();

		policy.setInitialInterval(INITIAL_INTERVAL);
		policy.setMaxInterval(MAX_INTERVAL);
		policy.setMultiplier(MULTIPLIER);

		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setBackOffPolicy(policy);

		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setRetryTemplate(retryTemplate);

		return template;

	}
	
//	@Bean
//	public Binding binding() {
//		return BindingBuilder.bind(queue()).to(exchange()).with(QUEUE_NAME);
//	}
	
//	//Template del Supplier
//	@Bean
//	public RabbitTemplate rabbitTemplate() throws Exception {
//		 RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
//		 rabbitTemplate.setMessageConverter(jsonMessageConverter());
//		 return rabbitTemplate;
//	}
	
//	//Listener del consumer
//	@Bean
//	public SimpleRabbitListenerContainerFactory customRabbitListenerContainerFactory() throws Exception {
//		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		factory.setMessageConverter(jsonMessageConverter());
//		return factory;
//	}
	
    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
	
}

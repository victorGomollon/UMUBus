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
public class RabbitProcessorConfiguration {

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
	
	@Bean
	public ConnectionFactory connectionFactory() throws Exception {
		final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(user);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualhost);
		return connectionFactory;
	}
	//Template del Supplier
	@Bean
	public RabbitTemplate rabbitTemplate() throws Exception {
		 RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		 rabbitTemplate.setMessageConverter(jsonMessageConverter());
		 return rabbitTemplate;
	}
	
	//Listener del consumer
	@Bean
	public SimpleRabbitListenerContainerFactory customRabbitListenerContainerFactory() throws Exception {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true, false, false);
	}
	
//	@Bean
//	public Queue queue(String event) {
//		if(queues == null) {
//			queues = new HashMap<String, Queue>();
//		}
//		if(!queues.containsKey(event)) {
//			queues.put(event.getClass().getName(), new Queue(event, true, false, false));
//		}
//		return queues.get(event);
//	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME, true, false);
	}


	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(QUEUE_NAME);
	}
	
//	@Bean
//	public Binding binding(String event) {
//		if(Bindings == null) {
//			Bindings = new HashMap<String, Binding>();
//		}
//		if(!Bindings.containsKey(event)) {
//			Bindings.put(event, BindingBuilder.bind(queue(event)).to(exchange()).with(event));
//		}
//		return Bindings.get(event);
//	}
	
	@Bean
	public MessageConverter jsonMessageConverter(){
	    return new Jackson2JsonMessageConverter();
	}
	
//	@Bean
//	public List<Queue> queue() {
//		return new ArrayList<Queue>();
//	}
	
//	@Bean
//	public List<TopicExchange> exchange() {
//		return new ArrayList<TopicExchange>();
//	}
	
//	@Bean
//	public List<Binding> binding() {
//		return new ArrayList<Binding>();
//	}
	
//	@Bean
//	public ConnectionFactory connectionFactoryProcessor() throws Exception {
//		final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setAddresses(host);
//		connectionFactory.setPort(port);
//		connectionFactory.setUsername(user);
//		connectionFactory.setPassword(password);
//		connectionFactory.setVirtualHost(virtualhost);
//		return connectionFactory;
//	}
//	//Template del Supplier
//	@Bean
//	public RabbitTemplate rabbitTemplate() throws Exception {
//		 RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactoryProcessor());
//		 rabbitTemplate.setMessageConverter(jsonMessageConverterProcesor());
//		 return rabbitTemplate;
//	}
	
}

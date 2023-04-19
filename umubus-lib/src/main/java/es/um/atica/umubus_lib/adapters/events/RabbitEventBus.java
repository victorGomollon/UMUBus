package es.um.atica.umubus_lib.adapters.events;

import java.util.concurrent.ExecutorService;

//import java.util.UUID;
//import java.util.concurrent.ExecutorService;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import es.um.atica.umubus_lib.config.queues.RabbitConfiguration;
import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Primary
public class RabbitEventBus implements EventBus {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
	/** The executor service. */
	@Autowired
	@Qualifier("fixedThreadPoolProducer")
	private ExecutorService executorService;
    
    @Override
//    @Async
//  public void publish(Event event) throws AmqpException{
    public void publish(String event) throws AmqpException{
    	log.debug("XXXXXXXXX: AQUI LLEGAMOS");
//    	rabbitTemplate.convertAndSend(RabbitConfiguration.QUEUE_NAME, event);
    	executorService.execute(() -> {
    		rabbitTemplate.convertAndSend(RabbitConfiguration.QUEUE_NAME, event);
    	});
    }

}

package es.um.atica.umubus_lib.adapters.events;

import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventBus;
import es.um.atica.umubus_lib.domain.queues.RabbitProcessorConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Primary
public class RabbitEventBus implements EventBus {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Override
    @Async
	@Retryable(value = { AmqpException.class }, maxAttemptsExpression = "#{${server.retry.policy.max.attempts:3}}", backoff = @Backoff(delayExpression = "#{${server.retry.policy.delay:36000}}", multiplierExpression = "#{${server.retry.policy.multiplier:2}}", maxDelayExpression = "#{${server.retry.policy.max.delay:252000}}"))
    public void publish(Event event) throws AmqpException{
    	
    	String correlationId = UUID.randomUUID().toString();
//    	rabbitTemplate.convertAndSend(event.getClass().getName(), event);
    	log.debug("XXXXXXXXX: AQUI LLEGAMOS");
    	rabbitTemplate.convertAndSend(RabbitProcessorConfiguration.QUEUE_NAME, event);
    	
//    	rabbitTemplate.convertAndSend(  RabbitConfiguration.QUEUE_NAME, event, m -> {
//			m.getMessageProperties().setCorrelationId(correlationId);
//			m.getMessageProperties().getHeaders().put("EVENT_OBJECT", event);
//			return m;
//    	});
    }

}

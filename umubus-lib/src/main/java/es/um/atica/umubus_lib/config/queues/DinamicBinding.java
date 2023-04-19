package es.um.atica.umubus_lib.config.queues;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DinamicBinding {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    private boolean switchedOn = true;

    //Toggle binding programatically
    @Scheduled(fixedRate = 10000L)
    public void manipulateBinding() {
        Exchange exchange = ExchangeBuilder.topicExchange(RabbitConfiguration.EXCHANGE_NAME).autoDelete().build();
        Queue queue = QueueBuilder.nonDurable(RabbitConfiguration.QUEUE_NAME).build();
    
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(RabbitConfiguration.QUEUE_NAME).noargs();
        
        if(switchedOn) {
            rabbitAdmin.declareBinding(binding);
        } else {
            rabbitAdmin.removeBinding(binding);
        }
    }
}

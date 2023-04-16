package es.um.atica.umubus_pruebas.users.adapters.events;

import org.springframework.stereotype.Component;
import es.um.atica.umubus_pruebas.users.domain.event.UsuarioEvent;
import es.um.atica.umubus_lib.adapters.queue.RabbitConsumerEvent;

@Component
public class UserAllConsumer extends RabbitConsumerEvent<UsuarioEvent> {
    
//    @Override
//    public void accept(UserCreated t) {
//        System.err.println(">>>EVENTO USER CREATED: "+t);
//    }
	
}

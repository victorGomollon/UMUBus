package es.um.atica.umubus_pruebas.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;
import es.um.atica.umubus_pruebas.users.domain.event.UsuarioEvent;
//import es.um.atica.umubus_lib.adapters.queue.RabbitConsumerEvent;

@Component
public class UsuarioAllConsumer implements Consumer<UsuarioEvent> {

    @Override
    public void accept(UsuarioEvent t) {
        System.err.println(">>>EVENTO USER ????: "+t);
    }


//public class UsuarioAllConsumer extends RabbitConsumerEvent<UsuarioEvent> {
    
//    @Override
//    public void accept(UserCreated t) {
//        System.err.println(">>>EVENTO USER CREATED: "+t);
//    }
	
}

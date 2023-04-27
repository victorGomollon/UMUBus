package es.um.atica.umubus_pruebas.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

//import es.um.atica.umubus_lib.adapters.queue.RabbitConsumerEvent;
import es.um.atica.umubus_pruebas.users.domain.event.CrearUsuarioEvent;

@Component
public class CrearUsuarioConsumer implements Consumer<CrearUsuarioEvent> {

    @Override
    public void accept(CrearUsuarioEvent t) {
        System.err.println(">>>EVENTO USER CREATED: "+t);
    }
    
//public class CrearUsuarioConsumer extends RabbitConsumerEvent<CrearUsuarioEvent> {

//    @Override
//    public void accept(UserCreated t) {
//        System.err.println(">>>EVENTO USER CREATED: "+t);
//    }
    
}

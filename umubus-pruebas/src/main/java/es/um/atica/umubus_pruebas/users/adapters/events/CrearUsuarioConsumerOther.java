package es.um.atica.umubus_pruebas.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

//import es.um.atica.umubus_lib.adapters.queue.RabbitConsumerEvent;
import es.um.atica.umubus_pruebas.users.domain.event.CrearUsuarioEvent;

@Component
public class CrearUsuarioConsumerOther implements Consumer<CrearUsuarioEvent> {

    @Override
    public void accept(CrearUsuarioEvent t) {
        System.err.println(">>>EVENTO USER CREATED OTHER: "+t);
    }

//public class CrearUsuarioConsumerOther extends RabbitConsumerEvent<CrearUsuarioEvent> {

//    @Override
//    public void accept(UserCreated t) {
//        System.err.println(">>>EVENTO USER CREATED OTHER: "+t);
//    }
//    
}
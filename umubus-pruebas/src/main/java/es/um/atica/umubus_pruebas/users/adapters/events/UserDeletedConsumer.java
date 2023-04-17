package es.um.atica.umubus_pruebas.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

//import es.um.atica.umubus_lib.adapters.queue.RabbitConsumerEvent;
import es.um.atica.umubus_pruebas.users.domain.event.EliminarUsuarioEvent;

@Component
public class UserDeletedConsumer implements Consumer<EliminarUsuarioEvent> {

    @Override
    public void accept(EliminarUsuarioEvent t) {
        System.err.println(">>>EVENTO USER DELETED: "+t);
    }

//public class UserDeletedConsumer extends RabbitConsumerEvent<EliminarUsuarioEvent> {

//    @Override
//    public void accept(UserDeleted t) {
//        System.err.println(">>>EVENTO USER DELETED: "+t);
//    }
    
}

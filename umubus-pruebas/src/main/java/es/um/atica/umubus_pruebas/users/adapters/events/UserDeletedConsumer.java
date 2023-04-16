package es.um.atica.umubus_pruebas.users.adapters.events;

import org.springframework.stereotype.Component;

import es.um.atica.umubus_lib.adapters.queue.RabbitConsumerEvent;
import es.um.atica.umubus_pruebas.users.domain.event.EliminarUsuarioEvent;

@Component
public class UserDeletedConsumer extends RabbitConsumerEvent<EliminarUsuarioEvent> {

//    @Override
//    public void accept(UserDeleted t) {
//        System.err.println(">>>EVENTO USER DELETED: "+t);
//    }
    
}

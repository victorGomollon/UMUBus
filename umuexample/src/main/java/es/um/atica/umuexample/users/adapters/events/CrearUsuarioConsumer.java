package es.um.atica.umuexample.users.adapters.events;

import java.util.function.Consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

//import es.um.atica.umubus.adapters.queue.RabbitConsumerEvent;
import es.um.atica.umuexample.users.domain.event.CrearUsuarioEvent;
import es.um.atica.umubus.domain.events.Event;

@Component
public class CrearUsuarioConsumer implements Consumer<Message<CrearUsuarioEvent>> {

    @Override
    public void accept(Message<CrearUsuarioEvent> message) {
    	CrearUsuarioEvent cUE = message.getPayload();
    	Event event = message.getPayload();
        System.err.println(">>>EVENTO USER CREATED: " + cUE);
        System.err.println(">>>EVENTO USER CREATED USER_ID: " + cUE.getUserId());
        System.err.println(">>>EVENTO USER CREATED USER_ID METADATA: " + event.getMetaData().get("userId"));
    }
    
//public class CrearUsuarioConsumer extends RabbitConsumerEvent<CrearUsuarioEvent> {

//    @Override
//    public void accept(UserCreated t) {
//        System.err.println(">>>EVENTO USER CREATED: "+t);
//    }
    
}

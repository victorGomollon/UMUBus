package es.um.atica.umubus.adapters.events;

import java.util.function.Consumer;
import org.springframework.messaging.Message;
import es.um.atica.umubus.domain.events.Event;

public abstract class RabbitConsumer<T extends Event> extends LocalConsumer<T> implements Consumer<Message<T>> {

    @Override
    public void accept(Message<T> message) {
    	handleEvent(message.getPayload());
    }
}
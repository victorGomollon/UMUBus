package es.um.atica.umubus_lib.domain.queue;

import java.util.function.Supplier;

import org.springframework.messaging.Message;

import es.um.atica.umubus_lib.domain.events.Event;

public interface ProcessorEvent<T extends Message<Event>> extends Supplier<Message<Event>>{

}

package es.um.atica.umubus.domain.queue;

import java.util.function.Supplier;

import org.springframework.messaging.Message;

import es.um.atica.umubus.domain.events.Event;

public interface ProcessorEvent<T extends Message<Event>> extends Supplier<Message<Event>>{

}

package es.um.atica.umubus.domain.queue;

import java.util.function.Consumer;

import es.um.atica.umubus.domain.events.Event;

public interface ConsumerEvent<T extends Event> extends Consumer<Event> {

}

package es.um.atica.umubus_lib.domain.queue;

import java.util.function.Consumer;

import es.um.atica.umubus_lib.domain.events.Event;

public interface ConsumerEvent<T extends Event> extends Consumer<Event> {

}

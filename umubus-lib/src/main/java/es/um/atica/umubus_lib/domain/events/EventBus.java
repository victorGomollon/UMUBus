package es.um.atica.umubus_lib.domain.events;

import java.util.Collection;

import es.um.atica.umubus_lib.domain.ddd.AggregateRoot;

public interface EventBus {

//    void publish(Event e);
	void publish(String e);

    default void publish(Collection<Event> events) {
//        events.stream().forEach(this::publish);
    }

    default void publish(EventCollection collection) {
        collection.publish(this);
    }

    default void publish(AggregateRoot aggregate) {
        publish(aggregate.getEvents());
    }
}
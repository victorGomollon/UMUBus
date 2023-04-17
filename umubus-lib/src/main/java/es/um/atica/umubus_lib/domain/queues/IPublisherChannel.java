package es.um.atica.umubus_lib.domain.queues;

import es.um.atica.umubus_lib.domain.events.Event;

public interface IPublisherChannel {

	void sendMessage(Event event);

}

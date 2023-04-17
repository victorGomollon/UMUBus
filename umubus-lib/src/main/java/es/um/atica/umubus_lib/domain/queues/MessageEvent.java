package es.um.atica.umubus_lib.domain.queues;

import java.time.Instant;
import java.util.Date;

import es.um.atica.umubus_lib.domain.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MessageEvent {
	private int id;
	private Event event;
	private Date dateBegin;
	
	private MessageEvent(Event event) {
		this.id = 1;
		this.event = event;
		dateBegin = new Date(Instant.now().getEpochSecond());
	}
	public static MessageEvent of(Event event) {
		return new MessageEvent(event);
	}
}

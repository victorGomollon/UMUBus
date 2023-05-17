package es.um.atica.umubus.domain.model;

import java.sql.Timestamp;
import es.um.atica.umubus.domain.events.Event;

public class MessageFallBack {

    private String id;
    private Timestamp sendDate;
    private Event event;

    private MessageFallBack(String id, Timestamp sendDate, Event event) {
        this.id = id;  this.sendDate = sendDate; this.event = event;
    }

    public static MessageFallBack of (String id, Timestamp sendDate, Event event) {
        return new MessageFallBack(id, sendDate, event);
    }

    public String getId() { return id; }
    public Timestamp getSendDate() { return sendDate; }
    public Event getEvent() { return event; }
}
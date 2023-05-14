package es.um.atica.umubus.domain.model;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

import org.springframework.messaging.Message;
import es.um.atica.umubus.domain.events.Event;

public class MessageFallBack {

    private String id;
    private String idMessage;
//    private OffsetDateTime sendDate;
    private Timestamp sendDate;
    private Event event;
    private Message<Event> message;
    private String exceptionType;

    private MessageFallBack(String id, String idMessage, Timestamp sendDate, Event event, Message<Event> message, String exceptionType) {
        this.id = id; this.idMessage = idMessage; this.sendDate = sendDate; this.event = event; this.message = message; this.exceptionType=exceptionType;
    }

    public static MessageFallBack of (String id, String idMessage, Timestamp sendDate, Event event, Message<Event> message, String exceptionType) {
        return new MessageFallBack(id, idMessage, sendDate, event, message, exceptionType);
    }

    public String getId() { return id; }
    public String getIdMessage() { return idMessage; }
    public Timestamp getSendDate() { return sendDate; }
    public Event getEvent() { return event; }
    public Message<Event> getMessage() { return message; }
    public String getExceptionType() { return exceptionType; }

//    public void createUser() {
//        this.addEvent(UserCreated.of(this));
//    }
//
//    public void updateUserName(UserName name) {
//        this.name = name;
//        this.addEvent(UserUpdated.of(this,"name changed"));
//    }
//
//    public void updateUserAge(UserAge age) {
//        this.age = age;
//        this.addEvent(UserUpdated.of(this,"age changed"));
//    }
//
//    public void deleteUser() {
//        this.addEvent(UserDeleted.of(this));
//    }
}
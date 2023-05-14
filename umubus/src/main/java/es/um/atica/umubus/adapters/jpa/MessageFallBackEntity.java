package es.um.atica.umubus.adapters.jpa;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.messaging.Message;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.model.MessageFallBack;

@Entity
@Table(name="MESSAGE_FALLBACK",schema="PRUEBAS")
public class MessageFallBackEntity {
    private String id;
    private String idMessage;
    private Timestamp sendDate;
    private Event event;
    private Message<Event> message;
    private String exceptionType;

    private MessageFallBackEntity() {}
    private MessageFallBackEntity(String id, String idMessage, Timestamp sendDate, Event event, Message<Event> message, String exceptionType) {
    	this.id = id; this.idMessage = idMessage; this.sendDate = sendDate; this.event = event; this.message = message; this.exceptionType=exceptionType;
    }

    public static MessageFallBackEntity of (MessageFallBack mFB) {
        return new MessageFallBackEntity(
        		mFB.getId(),
        		mFB.getIdMessage(),
        		mFB.getSendDate(),
        		mFB.getEvent(),
        		mFB.getMessage(),
        		mFB.getExceptionType());
    }

    public MessageFallBack toModel() {
        return MessageFallBack.of(
        		this.id,
        		this.idMessage,
        		this.sendDate,
        		this.event,
        		this.message,
        		this.exceptionType);
    }

    @Id
    @Column(name = "ID_ORDER")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Column(name = "ID_MESSAGE")
    public String getIdMessage() { return idMessage; }
    public void setIdMessage(String idMessage) { this.idMessage = idMessage; }

    @Column(name = "SEND_DATE")
    public Timestamp getSendDate() { return sendDate; }
    public void setSendDate(Timestamp sendDate) { this.sendDate = sendDate; }
    
    @Column(name = "EVENT")
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    
    @Column(name = "MESSAGE")
    public Message<Event> getMessage() { return message; }
    public void setMessage(Message<Event> message) { this.message = message; }
    
    @Column(name = "EXCEPTION_TYPE")
    public String getExceptionType() { return exceptionType; }
    public void setExceptionType(String exceptionType) { this.exceptionType = exceptionType; }

}

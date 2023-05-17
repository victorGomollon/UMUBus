package es.um.atica.umubus.adapters.jpa;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.model.MessageFallBack;

@Entity
@Table(name="MESSAGE_FALLBACK")
public class MessageFallBackEntity {
    private String id;
    private Timestamp sendDate;
    private String event;

    private MessageFallBackEntity() {}
    private MessageFallBackEntity(String id, Timestamp sendDate, String event) {
    	this.id = id; this.sendDate = sendDate; this.event = event;
    }

    public static MessageFallBackEntity of (MessageFallBack mFB) {
        return new MessageFallBackEntity(
        		mFB.getId(),
        		mFB.getSendDate(),
        		new Gson().toJson(mFB.getEvent()));
    }

    public MessageFallBack toModel() {
        return MessageFallBack.of(
        		this.id,
        		this.sendDate,
        		new Gson().fromJson(this.event, Event.class));
    }

    @Id
    @Column(name = "ID")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Column(name = "SEND_DATE")
    public Timestamp getSendDate() { return sendDate; }
    public void setSendDate(Timestamp sendDate) { this.sendDate = sendDate; }
    
    @Column(name = "EVENT")
    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }
}

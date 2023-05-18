package es.um.atica.umubus.adapters.jpa;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.atica.umubus.domain.events.Event;
import es.um.atica.umubus.domain.model.MessageFallBack;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="MESSAGE_FALLBACK")
public class MessageFallBackEntity {
    
	@Id
    @Column(name = "ID")
    private String id;
	@Column(name = "SEND_DATE")
	private Timestamp sendDate;
	@Column(name = "EVENT")
	private String event;

    private MessageFallBackEntity() {}
    private MessageFallBackEntity(String id, Timestamp sendDate, String event) {
    	this.id = id; this.sendDate = sendDate; this.event = event;
    }

    public static MessageFallBackEntity of (MessageFallBack mFB){
        try {
			return new MessageFallBackEntity(
					mFB.getId(),
					mFB.getSendDate(),
					new ObjectMapper().writeValueAsString(mFB.getEvent()));
		} catch (JsonProcessingException e) {
			return null;
		}
    }

    public MessageFallBack toModel(){
        try {
			return MessageFallBack.of(
					this.id,
					this.sendDate,
					new ObjectMapper().readValue(this.event, Event.class));
		} catch (JsonProcessingException e) {
			return null;
		}
    }
}

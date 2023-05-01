package es.um.atica.umubus_lib.domain.queue;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.atica.umubus_lib.domain.ddd.MessageUMU;
import es.um.atica.umubus_lib.domain.events.Event;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import io.cloudevents.core.format.EventFormat;
import io.cloudevents.core.provider.EventFormatProvider;
import io.cloudevents.jackson.JsonFormat;
//import io.cloudevents.examples.springboot.User;
import io.cloudevents.jackson.PojoCloudEventDataMapper;
import static io.cloudevents.core.CloudEventUtils.mapData;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CloudEventMessageUMU implements MessageUMU {

	private CloudEvent messageCloud;
	private String type;
	
	private ObjectMapper objectMapper =  new ObjectMapper();
	
	private CloudEventMessageUMU(Event event) {
		
		//Serializar el cloudevent en formato json
//		EventFormat format = EventFormatProvider
//				  .getInstance()
//				  .resolveFormat(JsonFormat.CONTENT_TYPE);
//		
//		// Serialize event
//		byte[] serialized = format.serialize(messageCloud);
		
		PojoCloudEventData<Event> cloudEventData = PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes);
		
//		PojoCloudEventData<User> cloudEventData = mapData(inputEvent, PojoCloudEventDataMapper.from(objectMapper, User.class));
		
//		PojoCloudEventData<Event> cloudEventData = mapData (
//			    event,
////			    PojoCloudEventDataMapper.from(objectMapper, event.getClass())
//			    PojoCloudEventDataMapper.from(objectMapper, Event.class)
//			);
		
		LocalDateTime localDateTime = LocalDateTime.now();
				
		this.type = event.getType();
		
		this.messageCloud = CloudEventBuilder.v1()
			    .withId(event.getId())
			    .withType(event.getType())
			    .withSource(URI.create("http://localhost"))
			    .withTime(localDateTime.atOffset(ZoneOffset.of("+01:00")))
//			    .withDataContentType("application/json")
			    .withData(cloudEventData)
			    .build();
	}
	
	public static CloudEventMessageUMU of(Event event) {
		return new CloudEventMessageUMU(event);
	}
	
	public Event getEvent() {
		PojoCloudEventData<Event> cloudEventData = mapData(messageCloud, PojoCloudEventDataMapper.from(objectMapper, Event.class));
		return cloudEventData.getValue();
	}
}

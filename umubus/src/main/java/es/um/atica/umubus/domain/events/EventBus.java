package es.um.atica.umubus.domain.events;

import java.util.Collection;
import java.sql.Timestamp;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.um.atica.umubus.domain.model.AggregateRoot;

public interface EventBus {

    void publish(Event e);
    
    void publishMessageFB(Event event, String idMessage, Timestamp sendDate);
    
    default void publish(Collection<Event> events) {
        events.stream().forEach(this::publish);
    }

    default void publish(EventCollection collection) {
        collection.publish(this);
    }

    default void publish(AggregateRoot aggregate) {
        publish(aggregate.getEvents());
    }
    //Esto es para que el consumidor haga su routingKeyExpression automaticamente
    default String eventType(byte[] bytes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,Object> map = objectMapper.readValue(new String(bytes), new TypeReference<Map<String,Object>>(){});
            return map.get("type").toString();
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
        }
        return "unknown";
    }

}
package es.um.atica.umubus_pruebas.users.adapters.queues;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.queues.MessageEvent;
import es.um.atica.umubus_lib.domain.queues.UMUBUSInputChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBinding(UMUBUSInputChannel.class)
public class RabbitConsumerEvent {
	@StreamListener(target = UMUBUSInputChannel.INPUT)
    public void consumer(MessageEvent messageEvent) {
        log.info("Event receive: {}", messageEvent);
	}
}
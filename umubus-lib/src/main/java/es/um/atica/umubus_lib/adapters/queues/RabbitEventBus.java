package es.um.atica.umubus_lib.adapters.queues;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.events.EventBus;
import es.um.atica.umubus_lib.domain.queues.MessageEvent;
import es.um.atica.umubus_lib.domain.queues.UMUBUSOutputChannel;
//import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Primary
@EnableBinding(UMUBUSOutputChannel.class)
public class RabbitEventBus implements EventBus {
    private final UMUBUSOutputChannel source;

    RabbitEventBus(UMUBUSOutputChannel source) {
        this.source = source;
    }
    @Override
    public void publish(Event event) {
        log.info("Sending event: {}", event);
        MessageEvent messageEvent = MessageEvent.of(event);
        source.output().send(MessageBuilder.withPayload(messageEvent).build());
        log.info("Message sent successfully");
    }
}
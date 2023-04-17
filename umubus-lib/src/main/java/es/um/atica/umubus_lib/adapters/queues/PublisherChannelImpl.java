package es.um.atica.umubus_lib.adapters.queues;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import es.um.atica.umubus_lib.domain.events.Event;
import es.um.atica.umubus_lib.domain.queues.IPublisherChannel;
import es.um.atica.umubus_lib.domain.queues.UMUBUSOutputChannel;
//import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableBinding(UMUBUSOutputChannel.class)
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PublisherChannelImpl implements IPublisherChannel {
    private final UMUBUSOutputChannel source;

    PublisherChannelImpl(UMUBUSOutputChannel source) {
        this.source = source;
    }
    @Override
    public void sendMessage(Event event) {
        log.info("Sending event: {}", event);
        source.output().send(MessageBuilder.withPayload(event).build());
        log.info("Message sent successfully");
    }
}
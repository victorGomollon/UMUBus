package es.um.atica.umubus_lib.domain.queues;

import org.springframework.messaging.MessageChannel;
import org.springframework.cloud.stream.annotation.Input;

public interface UMUBUSInputChannel {
    String INPUT = "UMUBUSChannel";
    @Input(UMUBUSInputChannel.INPUT)
    MessageChannel input();
}

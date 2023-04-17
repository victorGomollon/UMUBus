package es.um.atica.umubus_lib.domain.queues;

import org.springframework.messaging.MessageChannel;
import org.springframework.cloud.stream.annotation.Output;

public interface UMUBUSOutputChannel {
    String OUTPUT = "UMUBUSChannel";
    @Output(UMUBUSOutputChannel.OUTPUT)
    MessageChannel output();
}

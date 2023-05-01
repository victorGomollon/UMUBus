package es.um.atica.umubus_lib.domain.queue;

import java.util.function.Supplier;

import es.um.atica.umubus_lib.domain.events.Event;

public interface ProcessorEvent<T extends CloudEventMessageUMU> extends Supplier<CloudEventMessageUMU>{

}

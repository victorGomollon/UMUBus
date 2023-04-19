package es.um.atica.umubus_pruebas.users.adapters.queues;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import es.um.atica.umubus_pruebas.users.domain.event.CrearUsuarioEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrearUsuarioEventConsumer {
//	@RabbitListener(queues = "CrearUsuarioEvent", containerFactory = "customRabbitListenerContainerFactory")
	@RabbitListener(queues = "UMUBUS-queue", containerFactory = "customRabbitListenerContainerFactory")
	@RabbitHandler
	public void onMessageFromRabbitMQ(CrearUsuarioEvent evento)
			throws InterruptedException {
//	public void onMessageFromRabbitMQ(@Payload String payload, Message message, Channel channel)
//			throws InterruptedException {
		try {
			log.info("XXXXXXXXXXXXXXXXXXXX" + evento.getUserId());
		} catch (Exception e) {
			log.error("Error", e);
		}
	}
}

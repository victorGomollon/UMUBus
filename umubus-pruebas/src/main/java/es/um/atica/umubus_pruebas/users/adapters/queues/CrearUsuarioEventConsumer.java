package es.um.atica.umubus_pruebas.users.adapters.queues;

import java.util.concurrent.ExecutorService;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;

import es.um.atica.umubus_pruebas.users.domain.event.CrearUsuarioEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrearUsuarioEventConsumer {
	
//	/** The executor service. */
	@Autowired
	@Qualifier("fixedThreadPoolConsumer")
	private ExecutorService executorService;
	
//	@Async
	@RabbitListener(queues = "UMUBUS-queue")
//	@RabbitHandler
	public void accept(String evento) throws InterruptedException {
		try {
//			log.info("XXXXXXXXXXXXXXXXXXXX" + evento.getUserId());
			executorService.execute(() -> {
				log.info("XXXXXXXXXXXXXXXXXXXX" + evento);
			});
		} catch (Exception e) {
			log.error("Error", e);
		}
	}
}

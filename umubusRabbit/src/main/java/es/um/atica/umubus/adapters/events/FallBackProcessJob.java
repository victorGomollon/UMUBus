package es.um.atica.umubus.adapters.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.um.atica.umubus.domain.events.EventBus;
import es.um.atica.umubus.domain.repository.MessageFallBackReadRepository;
import es.um.atica.umubus.domain.model.MessageFallBack;

@Component
@EnableScheduling
public class FallBackProcessJob {

	@Autowired
    private EventBus eventBus;
	
	@Autowired
    private MessageFallBackReadRepository messageFallBackReadRepository;
	
    @Scheduled(cron = "0 */10 * * * *")
    public void scheduledTask() {
    	Iterable<MessageFallBack> listMFB = messageFallBackReadRepository.findAllMessageFB();
    	if(listMFB.iterator().hasNext()) {
    		listMFB.forEach( mFB -> eventBus.publishMessageFB(mFB.getEvent(), mFB.getId(), mFB.getSendDate()));
    	}
    }
}

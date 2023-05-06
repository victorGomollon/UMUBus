//package es.um.atica.umubus.adapters.queue;
//
//import org.springframework.amqp.core.ExchangeTypes;
//import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
//import org.springframework.cloud.stream.binder.rabbit.config.RabbitBinderConfiguration;
//import org.springframework.cloud.stream.binder.rabbit.properties.RabbitConsumerProperties;
//
//import es.um.atica.umubus.domain.events.Event;
//import es.um.atica.umubus.domain.queue.ConsumerEvent;
//
//public class RabbitConsumerEvent<T extends Event> extends ExtendedConsumerProperties<RabbitConsumerProperties> 
//								 implements ConsumerEvent<Event>{
//
//	public RabbitConsumerEvent(RabbitConsumerProperties extension) {
//		super(extension);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void accept(Event t) {
//		// TODO Auto-generated method stub
//		System.err.println(">>>EVENTO USER ????: "+t);
//	}
//
//	//En esta clase tenemos que rellenar el properties de bindingRoutingKey para el nuevo exchange para cada consumer
//	//La idea es que al cargar el contexto del consumidor cree automaticamente el binding con todas sus propiedades....
//	private void setBindingProperties(Event event) {
//		this.getExtension().setExchangeType(ExchangeTypes.TOPIC);
//		this.getExtension().setBindingRoutingKey(event.getClass().getName());
//		this.getExtension().setBindingRoutingKeyDelimiter(",");
//		this.getExtension().setQueueNameGroupOnly(true);
//	}
//}

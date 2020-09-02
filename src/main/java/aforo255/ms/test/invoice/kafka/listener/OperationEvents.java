
package aforo255.ms.test.invoice.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aforo255.ms.test.invoice.entity.Invoice;
import aforo255.ms.test.invoice.entity.Operation;
import aforo255.ms.test.invoice.service.InvoiceService;

@Service
public class OperationEvents {

	@Autowired
	private InvoiceService repository;

	@Autowired
	ObjectMapper objectMapper;

	private Logger log = LoggerFactory.getLogger(OperationEvents.class);

	public void processTransactionEvent(ConsumerRecord<Integer, String> consumerRecord)
			throws JsonMappingException, JsonProcessingException {
		double newAmount = 0;
		Invoice invoice = new Invoice();

		Operation event = objectMapper.readValue(consumerRecord.value(), Operation.class);
		log.info("transactionEvent: {} ", event.getInvoiceId());
		invoice = repository.findById(event.getInvoiceId());
		
		newAmount = invoice.getAmount() - event.getAmount();
		if(newAmount>=0) {
			log.info("\n===> Invoice: Pagando Factura: " + event.getInvoiceId() +" de "+invoice.getAmount()+ " con "+ event.getAmount());
			invoice.setAmount(newAmount);
			repository.save(invoice);
		}else {
			log.info("\n===> Invoice: Factura Paga en totalidad!! " + event.getInvoiceId());
		}
	}

}

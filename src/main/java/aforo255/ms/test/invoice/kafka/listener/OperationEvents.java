
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
		Invoice account = new Invoice();

		Operation event = objectMapper.readValue(consumerRecord.value(), Operation.class);
		log.info("transactionEvent: {} ", event.getInvoiceId());
		account = repository.findById(event.getInvoiceId());
		newAmount = account.getAmount() + event.getAmount();
		account.setAmount(newAmount);
		log.info("Actualizando Saldo Factura " + event.getInvoiceId());
		repository.save(account);

	}

}

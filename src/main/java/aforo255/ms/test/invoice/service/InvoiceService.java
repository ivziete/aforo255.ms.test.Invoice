package aforo255.ms.test.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aforo255.ms.test.invoice.entity.Invoice;
import aforo255.ms.test.invoice.kafka.repository.InvoiceRepository;
@Service
public class InvoiceService  {

	@Autowired
	InvoiceRepository invoiceRepo;	

	public List<Invoice> findAll() {
		return (List<Invoice>) invoiceRepo.findAll();
	}

	public Invoice findById(Integer id) {
		return invoiceRepo.findById(id).orElse(null);
	}

	public Invoice save(Invoice account) {
		return invoiceRepo.save(account);
	}

}

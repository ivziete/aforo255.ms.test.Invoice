package aforo255.ms.test.invoice.kafka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aforo255.ms.test.invoice.entity.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

}

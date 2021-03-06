
package aforo255.ms.test.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import aforo255.ms.test.invoice.entity.Invoice;
import aforo255.ms.test.invoice.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	private InvoiceService service ;
	
	@GetMapping("listar")
	public List<Invoice> listar(){
		
		return (List<Invoice>)service.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Invoice detalle (@PathVariable Integer id ) {
		Invoice invoice = service.findById(id);
		return invoice ;
		
	}
	
	
	
}
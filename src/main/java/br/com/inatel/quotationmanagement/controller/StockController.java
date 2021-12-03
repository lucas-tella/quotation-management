package br.com.inatel.quotationmanagement.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.quotationmanagement.dto.ErrorDto;
import br.com.inatel.quotationmanagement.form.StockForm;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.repository.StockRepo;
import br.com.inatel.quotationmanagement.service.DateFormatService;
import br.com.inatel.quotationmanagement.service.StockService;

@RestController
@RequestMapping("/quotations")
public class StockController {

	@Autowired
	private StockRepo stockRepository;
	@Autowired
	private StockService stockService;
	@Autowired
	private DateFormatService dateFormatService;

	@PostMapping
	@Transactional
	public ResponseEntity<?> StockPost(@RequestBody @Valid StockForm form) {
		Stock stock = new Stock(form);
		boolean isDateValid = dateFormatService.validatingStockDates(stock);
		if (!isDateValid) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
					(new ErrorDto(400, "Stock date is not valid."));
		}
		boolean isStockNameValid = stockService.validateStockName(
				form.getStockId());
		if (!isStockNameValid) {
			stockService.createExternalStock(form.getStockId());
		}
		Stock newStock = stockService.createStock(stock);
		return ResponseEntity.status(HttpStatus.CREATED).body(newStock);
		}
	
//	improvement: add put/patch mapping for register updates.
	
	@GetMapping
	public List<Stock> list(String id) {
		List<Stock> stocks = stockRepository.findAll();
		return stocks;
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable String id) {
		Optional<Stock> foundStock = stockRepository.findByStockId(id);
		if (!foundStock.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body
					(new ErrorDto(404, "Stock not found."));
		}
		return ResponseEntity.status(HttpStatus.OK).body(foundStock.get());
	}
}
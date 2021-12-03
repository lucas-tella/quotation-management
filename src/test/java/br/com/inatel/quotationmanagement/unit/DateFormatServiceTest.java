package br.com.inatel.quotationmanagement.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.service.DateFormatService;

class DateFormatServiceTest {
	
	@Test
	void validatingThatYearHasFourDigits() {
		Stock stock = new Stock();
		DateFormatService dateFormatService = new DateFormatService();
		Map<String, Double> quote = new HashMap<String, Double>();
		quote.put("1234-12-12", 12.0);
		stock.setQuote(quote);	
		
		boolean valid = dateFormatService.validatingStockDates(stock);

		assertTrue(valid);
	}
	
	@Test
	void validatingYearMonthDayFormat() {
		Stock stock = new Stock();
		DateFormatService dateFormatService = new DateFormatService();
		Map<String, Double> quote = new HashMap<String, Double>();
		quote.put("12-12-1234", 12.0);
		quote.put("12-1234-12", 12.0);
		quote.put("1234-12-12", 12.0);
		stock.setQuote(quote);
		
		boolean valid = dateFormatService.validatingStockDates(stock);
		
		assertFalse(valid);
	}
	
	@Test
	void validatingThatMonthHasTwoDigitsMax() {
		Stock stock = new Stock();
		DateFormatService dateFormatService = new DateFormatService();
		Map<String, Double> quote = new HashMap<String, Double>();
		quote.put("1234-123-12", 12.0);
		stock.setQuote(quote);
		
		boolean valid = dateFormatService.validatingStockDates(stock);
		
		assertFalse(valid);
	}
	
	@Test
	void validatingThatDayHasTwoDigitsMax() {
		Stock stock = new Stock();
		DateFormatService dateFormatService = new DateFormatService();
		Map<String, Double> quote = new HashMap<String, Double>();
		quote.put("1234-12-123", 12.0);
		stock.setQuote(quote);
		
		boolean valid = dateFormatService.validatingStockDates(stock);
		
		assertFalse(valid);
	}
}

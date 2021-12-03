package br.com.inatel.quotationmanagement.unit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.repository.StockRepo;
import br.com.inatel.quotationmanagement.service.StockService;
import util.StockSample;

@ExtendWith(SpringExtension.class)
class StockServiceTest {
	
	@InjectMocks
	StockService stockService;
	@Mock
	StockRepo stockRepo;
	
	@Test
	void shouldRegisterQuote() {
		BDDMockito.when(stockRepo.findById(
				ArgumentMatchers.anyString())).thenReturn(
				Optional.empty());
		
		Stock stockSample = StockSample.createStockSample();
		Stock stock = stockService.createStock(stockSample);
		
		assertTrue(!stock.getQuote().isEmpty());
	}
	
	@Test
	void shouldRegisterTwoOrMoreQuotesAtOnce() {
		BDDMockito.when(stockRepo.findById(
				ArgumentMatchers.anyString())).thenReturn(
				Optional.of(StockSample.createThreeStockSamples()));
		
		Stock stockSamples = StockSample.createThreeStockSamples();
		Stock stock = stockService.createStock(stockSamples);

		assertTrue(stock.getQuote().size()==3);
	}
}

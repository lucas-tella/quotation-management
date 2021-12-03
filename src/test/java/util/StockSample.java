package util;

import java.util.HashMap;
import java.util.Map;

import br.com.inatel.quotationmanagement.model.Stock;

public class StockSample {
	
	public static Stock createStockSample() {
		Stock stock = new Stock(); 
		stock.setId("id");
		Map<String, Double> quote = new HashMap<String, Double>();
		quote.put("1111-11-11", 11.0);
		stock.setQuote(quote);
		return stock;
	}
	
	public static Stock createThreeStockSamples() {
		Stock stock = new Stock();
		stock.setId("id");
		Map<String, Double> quote = new HashMap<String, Double>();
		quote.put("1111-11-11", 11.0);
		quote.put("2222-12-22", 22.0);
		quote.put("3333-03-30", 33.0);
		stock.setQuote(quote);
		return stock;
	}
}

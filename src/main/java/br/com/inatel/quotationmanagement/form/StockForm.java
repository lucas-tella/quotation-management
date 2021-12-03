package br.com.inatel.quotationmanagement.form;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

public class StockForm {
	
	@NotEmpty
	private String stockId;
	@NotEmpty
	private Map<String, Double> quotes;
	
	public StockForm(String stockId, Map<String, Double> quotes) {
		super();
		this.stockId = stockId;
		this.quotes = quotes;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public Map<String, Double> getQuote() {
		return quotes;
	}
	public void setQuote(Map<String, Double> quote) {
		this.quotes = quote;
	}
}

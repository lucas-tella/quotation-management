package br.com.inatel.quotationmanagement.model;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.GenericGenerator;

import br.com.inatel.quotationmanagement.form.StockForm;

@Entity
public class Stock {

	private String stockId;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name= "ID")
	private String id;
	
	@ElementCollection
	@CollectionTable(name = "QUOTES", joinColumns = {
	@JoinColumn(name = "ID", referencedColumnName = "ID") })
	@MapKeyColumn(name = "data")
	@Column(name = "quote_VALUE")
	private Map<String, Double> quote;

	public Stock () {}
	
	public void AddQuote(String data, Double value) {
		this.quote.put(data, value);
	}
	
	public Stock(String name, String stockId,
			Map<String, Double> quote, Stock stock) {
		this.stockId = name;
		this.id = stockId;
		this.quote = quote;
	}
	
	public Stock(StockForm form) {
		this.stockId = form.getStockId();
		this.quote = form.getQuote();
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String name) {
		this.stockId = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String stockId) {
		this.id = stockId;
	}

	public Map<String, Double> getQuote() {
		return quote;
	}

	public void setQuote(Map<String, Double> quote) {
		this.quote = quote;
	}
}

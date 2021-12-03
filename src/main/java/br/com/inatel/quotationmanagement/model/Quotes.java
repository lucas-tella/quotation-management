package br.com.inatel.quotationmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name= "QUOTES")
public class Quotes {

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name= "data")
	private String data;
	
	@Column(name= "quote_VALUE")
	private Double quoteValue;

	public Quotes () {}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Quotes(Long id, String data, Stock stockId) {
		super();
		this.data = data;
	}
}

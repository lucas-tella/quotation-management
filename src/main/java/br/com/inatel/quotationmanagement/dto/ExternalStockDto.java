package br.com.inatel.quotationmanagement.dto;

public class ExternalStockDto {
	private String id;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExternalStockDto(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	public ExternalStockDto() {}
}
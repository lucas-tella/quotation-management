package br.com.inatel.quotationmanagement.service;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.inatel.quotationmanagement.dto.ExternalStockDto;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.repository.StockRepo;

@Service
public class StockService {

	@Autowired
	private StockRepo repository;
	@Autowired
	private CacheService cache;
	@Value("${externalapiurl}")
	private String apiUrl;

	@Transactional
	public Stock createStock(Stock stock) {
		Optional<Stock> foundStock = repository.findByStockId(
				stock.getStockId());
		if (!foundStock.isPresent()) {
			repository.save(stock);
			return stock;
		}
		Map<String, Double> newQuotes = stock.getQuote();
		Stock updatedStock = foundStock.get();
		for (Map.Entry<String, Double> newQuote : newQuotes.entrySet()) {
			updatedStock.AddQuote(newQuote.getKey(),
					Math.round(newQuote.getValue() * 100.0) / 100.0);
		}
		
		return updatedStock;
	}

	public boolean validateStockName(String stockName) {
		ExternalStockDto[] externalStockDto = cache.callApi();
		for (ExternalStockDto dto : externalStockDto) {
			if (dto.getId().equals(stockName)) {
				return true;
			}
		}
		return false;
	}
	
	public void createExternalStock(String stockId) {
		RestTemplate restTemplate = new RestTemplate();
		ExternalStockDto externalStockDto = new ExternalStockDto(
				stockId, "Standard description.");
		restTemplate.postForObject(apiUrl + "/stock",
				externalStockDto, ExternalStockDto.class);
	}
}

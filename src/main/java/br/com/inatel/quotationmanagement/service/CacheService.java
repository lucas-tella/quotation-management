package br.com.inatel.quotationmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.inatel.quotationmanagement.dto.ExternalStockDto;

@Service
public class CacheService {
	
	@Value("${externalapiurl}")
	private String apiUrl;
	
	Logger logger = LoggerFactory.getLogger(CacheService.class);

	@Cacheable(value= "standardCache")
	public ExternalStockDto[] callApi() {
		RestTemplate restTemplate = new RestTemplate();
		ExternalStockDto[] externalStockDto = restTemplate.getForObject(
				apiUrl + "/stock", ExternalStockDto[].class);
		logger.info("caching ok");
		return externalStockDto;
	}
	
	@CacheEvict(value= "standardCache")
	public void cleaningCache() {
		logger.info("cleaning cache");
	}
}

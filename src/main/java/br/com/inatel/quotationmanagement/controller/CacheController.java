package br.com.inatel.quotationmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.quotationmanagement.service.CacheService;

@RestController
public class CacheController {
	
	@Autowired
	private CacheService cacheService;
	
	@DeleteMapping("/stockcache")
	public void cleanCache() {
		cacheService.cleaningCache();
	}
}
package br.com.inatel.quotationmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.quotationmanagement.model.Stock;

	public interface StockRepo extends JpaRepository<Stock, String> {
		Optional<Stock> findByStockId(String id);
	}
package br.com.inatel.quotationmanagement.service;


import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.inatel.quotationmanagement.model.Stock;

@Service
public class DateFormatService {
	
	public boolean validatingStockDates(Stock stock) {
		Map<String, Double> newQuotes = stock.getQuote();
		for (Map.Entry<String, Double> newQuote : newQuotes.entrySet()) {
			if (!isDateValid(newQuote.getKey())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isDateValid(String date) {
		String dateFormat = "yyyy-MM-dd";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
	}
}
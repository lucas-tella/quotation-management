package br.com.inatel.quotationmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import br.com.inatel.quotationmanagement.config.SendNotification;

@SpringBootApplication
@EnableCaching
public class QuotationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotationManagementApplication.class, args);
	}
	
	@Bean(initMethod= "runAfterObjectCreated")
	public SendNotification getFunnyBean() {
		return new SendNotification();
	}
}
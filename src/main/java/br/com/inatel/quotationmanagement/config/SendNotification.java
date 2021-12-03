package br.com.inatel.quotationmanagement.config;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SendNotification {
	
	@Value("${externalapiurl}")
	private String apiUrl;
	
	Logger logger = LoggerFactory.getLogger(SendNotification.class);
	
	public void runAfterObjectCreated() {
		String json = "{\"host\":\"localhost\", \"port\": \"8081\"}";
		HttpEntity<String> entity = new HttpEntity<String>(json, headers());
		try {			
			new RestTemplate().postForEntity(new URI (
					apiUrl + "/notification"), entity, null);
			System.out.println(apiUrl + "/notification");
		} catch(Exception e) {
			logger.error("API conection error");
		}
	}

    private static HttpHeaders headers(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
} 
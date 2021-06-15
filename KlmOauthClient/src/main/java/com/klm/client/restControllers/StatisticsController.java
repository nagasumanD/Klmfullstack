package com.klm.client.restControllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.klm.client.models.StatisticsRequest;
import com.klm.client.models.StatisticsResponse;
import com.klm.client.services.TravelService;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class StatisticsController {

	Logger logger=LoggerFactory.getLogger(StatisticsController.class);
	@Autowired
	private TravelService service;

	@PostMapping("/logginResponseTime")
	public Mono<String> logRequestResponse(@RequestBody StatisticsRequest log)
			throws FileNotFoundException, IOException {
		service.requestdata(log);
		return Mono.just("Saved");
	}

	@GetMapping("/getLoggedData")
	public Mono<StatisticsResponse> getLoggedData() {
		Mono<StatisticsResponse> logResponse = null;
			try {
				logResponse= Mono.just(service.getStatistics());
			} catch (FileNotFoundException e) {
				logger.error("File not find Exception: "+e.getStackTrace());
			} catch (IOException e) {
				logger.error("IOException : "+e.getStackTrace());
				e.printStackTrace();
			}
			
		return logResponse;
		
	}
}

package com.klm.client.restControllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klm.client.models.Embedded;
import com.klm.client.services.TravelService;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class AirportsController {

	@Autowired
	private TravelService service;

	@GetMapping("/airports")
	public Mono<Embedded> getAirportsList(
			@RequestParam Optional<Integer> size,
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<String> lang,
			@RequestParam Optional<String> term) {
		
		return Mono.just(service.gerAirportsData(size,page,lang,term));
	}

}


package com.klm.client.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klm.client.models.Fares;
import com.klm.client.services.TravelService;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class FaresController {

	@Autowired
	private TravelService service;

	@GetMapping("/fare/{source}/{destination}")
	public Mono<Fares> offeredFare(@PathVariable("source") String source,
			@PathVariable("destination") String destination, @RequestParam(defaultValue = "EUR") String currency) {
		return Mono.just(service.getFares(source, destination, currency));
	}

}

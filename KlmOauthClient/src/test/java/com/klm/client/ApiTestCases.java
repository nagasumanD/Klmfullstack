package com.klm.client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.klm.client.models.Embedded;
import com.klm.client.models.Fares;
import com.klm.client.models.StatisticsRequest;
import com.klm.client.models.StatisticsResponse;
import com.klm.client.repository.StatisticsRepository;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient(timeout = "600000")
public class ApiTestCases {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	private StatisticsRepository repo;

	// Testing for getting airports list
	@Test
	public void getAirportsTest() {
		webTestClient.get().uri("/airports").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBody(Embedded.class)
				.consumeWith(res -> assertTrue(res.getResponseBody().getPage().getTotalPages() > 0));
	}

	// implementing test case to post request response time to store data.
	@Test
	public void storeLogDataTest() {

		StatisticsRequest request = new StatisticsRequest("testingid", "testing", 200, 3);
		webTestClient.post().uri("/logginResponseTime").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(request), StatisticsRequest.class).exchange()
				.expectStatus().isOk().expectBody(String.class).consumeWith(res -> {
					assertTrue(res.getResponseBody().equalsIgnoreCase("saved"));
				});

	}

	// implementing test cases for getting the statistics data
	@Test
	public void getStaticsDataTest() {
		this.repo.save(new StatisticsRequest("1233333", "testing", Integer.valueOf(200), Integer.valueOf(2000)));

		webTestClient.get().uri("/getLoggedData").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBody(StatisticsResponse.class)
				.consumeWith(res -> assertTrue(res.getResponseBody().getTotalRequest() > 0.0));

	}

	// Test for getting offered prices between the ports
	@Test
	public void getFaresTest() {

		webTestClient.get().uri(uribuilder -> uribuilder.path("/fare/JRO/YOW").queryParam("currency", "EUR").build())
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(Fares.class)
				.consumeWith(res -> assertEquals(res.getResponseBody().getCurrency(), "EUR"));
	}

}

package com.klm.client.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.klm.client.dao.TravelDao;
import com.klm.client.models.Embedded;
import com.klm.client.models.Fares;
import com.klm.client.models.StatisticsRequest;
import com.klm.client.models.StatisticsResponse;

@Service
public class TravelServiceImpl implements TravelService {

	@Value("${url.getAirPortsList}")
	private String getAirportsurl;
	@Value("${url.getFareUrl}")
	private String getFareUrl;

	@Autowired
	private WebClient webClient;

	@Autowired
	private TravelDao dao;

	@Override
	public Embedded gerAirportsData(Optional<Integer> size, Optional<Integer> page, Optional<String> lang,
			Optional<String> term) {

		return webClient.get()
				.uri(uri -> uri.scheme("http").host("localhost").port(8080).path("/airports")
						.queryParamIfPresent("size", size).queryParamIfPresent("page", page)
						.queryParamIfPresent("lang", lang).queryParamIfPresent("term", term).build())
				.retrieve().bodyToMono(Embedded.class).block();
	}

	@Override
	public Fares getFares(String origin, String destination, String currency) {

		return webClient
				.get().uri(uri -> uri.scheme("http").host("localhost").port(8080)
						.path("/fares/" + origin + "/" + destination).queryParam("currency", currency).build())
				.retrieve().bodyToMono(Fares.class).block();
	}

	@Override
	public void requestdata(StatisticsRequest reqsts) throws FileNotFoundException, IOException {

		dao.requestdata(reqsts);

	}

	@Override
	public StatisticsResponse getStatistics() throws FileNotFoundException, IOException {

		return dao.getStatistics();

	}
}

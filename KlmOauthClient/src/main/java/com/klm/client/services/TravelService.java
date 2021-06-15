package com.klm.client.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import com.klm.client.models.AirportsParms;
import com.klm.client.models.Embedded;
import com.klm.client.models.Fares;
import com.klm.client.models.StatisticsRequest;
import com.klm.client.models.StatisticsResponse;

public interface TravelService {

	Embedded gerAirportsData(Optional<Integer> size,
			Optional<Integer> page,
			Optional<String> lang,
			Optional<String> term);

	Fares getFares(String orgin, String distination, String currency);

	void requestdata(StatisticsRequest reqsts) throws FileNotFoundException, IOException;

	StatisticsResponse getStatistics() throws FileNotFoundException, IOException;

}

package com.klm.client.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.klm.client.models.StatisticsRequest;
import com.klm.client.models.StatisticsResponse;

public interface TravelDao {
	StatisticsRequest requestdata(StatisticsRequest reqsts) throws FileNotFoundException, IOException;

	StatisticsResponse getStatistics() throws FileNotFoundException, IOException;

}

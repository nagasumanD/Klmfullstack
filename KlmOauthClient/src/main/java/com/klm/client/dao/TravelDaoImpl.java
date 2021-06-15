package com.klm.client.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.klm.client.models.StatisticsRequest;
import com.klm.client.models.StatisticsResponse;
import com.klm.client.repository.StatisticsRepository;

@Repository
public class TravelDaoImpl implements TravelDao {
	Logger logger = LoggerFactory.getLogger(TravelDaoImpl.class);
	private StatisticsRepository repo;

	public TravelDaoImpl(StatisticsRepository repo) {
		this.repo = repo;

	}

	@Override
	public StatisticsRequest requestdata(StatisticsRequest reqsts) {

		return this.repo.save(reqsts);
	}

	@Override
	public StatisticsResponse getStatistics() {

		long total = this.repo.findAll().size();
		long total4XX = this.repo.findResponseCodeCount(400, 499).orElseGet(() -> 0l).longValue();
		long total5xx = this.repo.findResponseCodeCount(500, 599).orElseGet(() -> 0l).longValue();
		long total2xx = this.repo.findResponseCodeCount(200, 299).orElseGet(() -> 0l).longValue();
		long totalResponseTime = this.repo.toalResponseTime().orElseGet(() -> 0l).longValue();
		long maxResposne = this.repo.findMaximumResponseTime().orElseGet(() -> 0l).longValue();
		long minReponse = this.repo.findMinimumResponseTime().orElseGet(() -> 0l).longValue();

		StatisticsResponse logResponse = new StatisticsResponse(total, total2xx, total4XX, total5xx,
				total == 0 ? 0 : totalResponseTime / total, minReponse, maxResposne);
		return logResponse;
	}

}

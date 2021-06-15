package com.klm.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klm.client.models.StatisticsRequest;

public interface StatisticsRepository extends JpaRepository<StatisticsRequest, String> {

	@Query(value = "select count(*) from statistics_request where response_code >= ?1 and response_code <= ?2", nativeQuery = true)
	Optional<Long> findResponseCodeCount(Integer startCode, Integer endCode);

	@Query(value = "select max(response_time) from statistics_request", nativeQuery = true)
	Optional<Long> findMaximumResponseTime();

	@Query(value = "select min(response_time) from statistics_request", nativeQuery = true)
	Optional<Long> findMinimumResponseTime();

	@Query(value = "SELECT sum(response_time) FROM STATISTICS_REQUEST", nativeQuery = true)
	Optional<Long> toalResponseTime();

}

package com.klm.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticsResponse {
	private long totalRequest;
	private long requestOk;
	private long request4xx;
	private long request5xx;
	private long averageRespTime;
	private long minResptime;
	private long maxResptime;

}

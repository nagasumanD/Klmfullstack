package com.klm.client.models;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtomicStatistics {

	private AtomicLong totalRequest;
	private AtomicLong requestOk;
	private AtomicLong request4xx;
	private AtomicLong request5xx;
	private AtomicLong averageRespTime;
	private AtomicInteger minResptime;
	private AtomicInteger maxResptime;
}

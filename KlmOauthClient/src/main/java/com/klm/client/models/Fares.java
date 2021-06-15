package com.klm.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fares {
	private double amount;
	private String currency;
	private String origin;
	private String destination;

}

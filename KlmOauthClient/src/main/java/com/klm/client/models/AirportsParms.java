package com.klm.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportsParms {
	private int size;
	private int page;
	private String lang;
	private String term;
    

}

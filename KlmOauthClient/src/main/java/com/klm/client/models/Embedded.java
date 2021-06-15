package com.klm.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Embedded {
	private Outerembdded _embedded;
	private Pagination page;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Outerembdded {
	private AirPortList[] locations;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class AirPortList {
	private String code;
	private String name;
	private String description;
	private Coordinates coordinates;
	private SuperParent parent;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class SuperParent {
	private String code;
	private String name;
	private String description;
	private Coordinates coordinates;
	private Parent parent;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Parent {
	private String code;
	private String name;
	private String description;
	private Coordinates coordinates;
	private AirPort parent;

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class AirPort {
	private String code;
	private String name;
	private String description;
	private Coordinates coordinates;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Coordinates {
	private String latitude;
	private String longitude;

}



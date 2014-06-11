package com.csobrero.challenge.rs.wrapper;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.csobrero.challenge.bean.Airport;

/**
 * Wrapper for binding an airport collection.
 * 
 * @author Christian
 *
 */
@XmlRootElement
public class Airports {

	private Collection<Airport> airports;

	public Airports() {
	}

	public Airports(Collection<Airport> airports) {
		super();
		this.airports = airports;
	}

	public Collection<Airport> getAirports() {
		return airports;
	}

	public void setAirports(Collection<Airport> airports) {
		this.airports = airports;
	}
}

package com.csobrero.challenge.core;

import java.util.List;

import com.csobrero.challenge.bean.FlightFilter;
import com.csobrero.challenge.bean.Ticket;
/**
 * Back-end service for looking into DB / INDEX or any other available top back end layer
 * 
 * @author Christian
 *
 */
public interface FlightSearchEngine extends SearchEngine {

	/**
	 * Search method based on filter conditions
	 * 
	 * @param flightFilter
	 * @return list of tickets available
	 */
	public List<Ticket> search(final FlightFilter flightFilter);
	
}

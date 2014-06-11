/**
 * 
 */
package com.csobrero.challenge.core.impl;

import java.util.Arrays;
import java.util.List;

import com.csobrero.challenge.bean.Airport;
import com.csobrero.challenge.bean.Flight;
import com.csobrero.challenge.bean.FlightFilter;
import com.csobrero.challenge.bean.Leg;
import com.csobrero.challenge.bean.Ticket;
import com.csobrero.challenge.core.FlightSearchEngine;

/**
 * Fast Mock provided in order to allow front-end development start asap.
 * 
 * @author Christian
 *
 */
public class FlightSearchMockImpl implements FlightSearchEngine {

	/**
	 * @see com.csobrero.challenge.core.FlightSearchEngine#search(com.csobrero.challenge.bean.FlightFilter)
	 */
	@Override
	public List<Ticket> search(final FlightFilter flightFilter) {
		
		Ticket ticket1=null, ticket2=null;
		
		if(flightFilter.getFrom().equals("EZE") && flightFilter.getTo().equals("LHR")){
			
			//** BUILDING 2 LEG FLIGHT ON GOING **//
			Leg legA = new Leg(new Flight("AA123", "American Airlines"),
					new Airport("EZE", "Buenos Aires", "Buenos Aires", "Argentina", "Ezeiza Ministro Pistarini International Airport"),
					new Airport("CDG", null, "Paris", "France", "Charles De Gaulle"),
					flightFilter.getDeparting(),
					flightFilter.getDeparting());
			Leg legB = new Leg(new Flight("AA123", "American Airlines"),
					new Airport("CDG", null, "Paris", "France", "Charles De Gaulle"),
					new Airport("LHR", "London", "England", "United Kingdom", "Heathrow"),
					flightFilter.getDeparting(),
					flightFilter.getDeparting());
			Leg legC = new Leg(new Flight("AA456", "American Airlines"),
					new Airport("LHR", "London", "England", "United Kingdom", "Heathrow"),
					new Airport("EZE", "Buenos Aires", "Buenos Aires", "Argentina", "Ezeiza Ministro Pistarini International Airport"),
					flightFilter.getReturning(),
					flightFilter.getReturning());
			
			List<Leg> legs = Arrays.asList(new Leg[]{legA,legB,legC});			
			
			ticket1 = new Ticket(legs, 1399.60f);
			
			//** BUILDING A DIRECT FLIGHT **//
			legA = new Leg(new Flight("AR1010", "Aerolineas Argentinas"),
					new Airport("EZE", "Buenos Aires", "Buenos Aires", "Argentina", "Ezeiza Ministro Pistarini International Airport"),
					new Airport("LHR", "London", "England", "United Kingdom", "Heathrow"),
					flightFilter.getDeparting(),
					flightFilter.getDeparting());
			legB = new Leg(new Flight("AR2020", "Aerolineas Argentinas"),
					new Airport("LHR", "London", "England", "United Kingdom", "Heathrow"),
					new Airport("EZE", "Buenos Aires", "Buenos Aires", "Argentina", "Ezeiza Ministro Pistarini International Airport"),
					flightFilter.getReturning(),
					flightFilter.getReturning());
			
			legs = Arrays.asList(new Leg[]{legA,legB});			
			
			ticket2 = new Ticket(legs, 1650f);
					
		}
		
		
		return Arrays.asList(new Ticket[]{ticket1,ticket2});
	}

}

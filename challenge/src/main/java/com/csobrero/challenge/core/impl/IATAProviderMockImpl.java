package com.csobrero.challenge.core.impl;

import java.lang.reflect.Field;
import java.util.List;

import com.csobrero.challenge.bean.Airport;
import com.csobrero.challenge.core.IATAProvider;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Mock Class used before implementation of the real service.
 * 
 * @author Christian
 *
 */
public class IATAProviderMockImpl implements IATAProvider {

	private static List<Airport> Airports;
	
	static{
		Airports = ImmutableList.of(
		new Airport(0, "EZE", "Buenos Aires", "Buenos Aires", "Argentina", "Ezeiza Ministro Pistarini International Airport"),
		new Airport(1, "MIA", "Miami", "FL", "USA" , "Miami International Airport"),
		new Airport(2, "CDG", null, "Paris", "France", "Charles De Gaulle"),
		new Airport(3, "LHR", "London", "England", "United Kingdom", "Heathrow"),
		new Airport(4, "LTN", "London", "England", "United Kingdom",  "Luton International"));
	}
	
	public List<Airport> lookup(final String term) {
		
		Iterable<Airport> iterables = Iterables.filter(Airports, new Predicate<Airport>() {

			public boolean apply(Airport input) {
				for (Field field : Airport.class.getDeclaredFields()) {//search fields through reflection
					if(field.getType().isAssignableFrom(String.class)){
						field.setAccessible(true);
						try {
							if (field.get(input) != null
									&& field.get(input).toString()
											.toLowerCase()
											.contains(term.toLowerCase()))
								return true;
						} catch (Exception e) {//I'm not expecting exceptions here!
							throw new RuntimeException(e);
						}
					}
				}
				return false;
			}
		});
		
		return Lists.newArrayList(iterables);
	}

}

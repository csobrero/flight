/**
 * 
 */
package com.csobrero.challenge.core;

import java.util.List;

import com.csobrero.challenge.bean.Airport;

/**
 * Provider resolver for IATA Codes.
 * 
 * @author Christian
 * 
 */
public interface IATAProvider extends Provider {
	
	/**
	 * 
	 * @param <b>term</b> is a 3 letter word at least
	 * @return <i>list</i> of airport beans or an empty list if can not find it.
	 */
	List<Airport> lookup(String term);
	
}

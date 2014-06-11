/**
 * 
 */
package com.csobrero.challenge.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Bussines model {@link Airport} bean
 * 
 * @author Christian
 * 
 */
@XmlType(propOrder={"id", "iataCode", "city", "state", "country", "description"})
public class Airport {

	private Long id;
	private String iataCode;
	private String city;
	private String state;
	private String country;
	private String description;
	
	public Airport() {}

	public Airport(long id, String iataCode, String city, String state,
			String country, String description) {
		this(iataCode, city, state, country, description);
	}

	public Airport(String iataCode, String city, String state,
			String country, String description) {
		super();
		this.iataCode = iataCode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlAttribute
	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

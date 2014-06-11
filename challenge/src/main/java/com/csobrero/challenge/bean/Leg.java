package com.csobrero.challenge.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"id", "flight", "departing", "origin", "arriving", "destination"})
public class Leg {

	private Long id;
	private Flight flight;
	private Airport origin;
	private Airport destination;
	private Date departing;
	private Date arriving;
	
	public Leg() {
	}

	public Leg(Flight flight, Airport origin, Airport destination,
			Date departing, Date arriving) {
		super();
		this.flight = flight;
		this.origin = origin;
		this.destination = destination;
		this.departing = departing;
		this.arriving = arriving;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Date getDeparting() {
		return departing;
	}

	public void setDeparting(Date departing) {
		this.departing = departing;
	}

	public Date getArriving() {
		return arriving;
	}

	public void setArriving(Date arriving) {
		this.arriving = arriving;
	}
	
	
	
}

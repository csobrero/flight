package com.csobrero.challenge.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Flight filter business model.
 * 
 * @author Christian
 *
 */
@XmlRootElement
public class FlightFilter {

	private String from;
	private String to;
	private Date departing;
	private Date returning;
	private int adults;
	private int children;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getDeparting() {
		return departing;
	}
	public void setDeparting(Date departing) {
		this.departing = departing;
	}
	public Date getReturning() {
		return returning;
	}
	public void setReturning(Date returning) {
		this.returning = returning;
	}
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	
	
	
}

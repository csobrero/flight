/**
 * 
 */
package com.csobrero.challenge.rs;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.csobrero.challenge.bean.FlightFilter;

/**
 * Wrapper request for flight filter BUT we should use a handler crafted from a factory from request->filter
 * 
 * @author Christian
 * 
 */
@XmlRootElement
public class FlightFilterRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	private Date departDate;
	private Date returnDate;
	private int adults;
	private int children;

	public FlightFilterRequest() {
	}

	/**
	 * Quick n' dirty approach!! A handler class should do this for decoupling 
	 * flightFilter which a business class!!
	 * 
	 * @return
	 */
	public FlightFilter retrieveFilter() {
		FlightFilter filter = new FlightFilter();
		filter.setFrom(from.substring(0, 3));
		filter.setTo(to.substring(0, 3));
		filter.setDeparting(departDate);
		filter.setReturning(returnDate);
		filter.setAdults(adults);
		filter.setChildren(children);
		return filter;
	}
	
	public Date getDepartDate() {
		return departDate;
	}

	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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

}

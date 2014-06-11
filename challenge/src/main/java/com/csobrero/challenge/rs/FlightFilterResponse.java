/**
 * 
 */
package com.csobrero.challenge.rs;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.csobrero.challenge.bean.Ticket;

/**
 * Flight response
 * 
 * @author Christian
 *
 */
@XmlRootElement
public class FlightFilterResponse {

	private Collection<Ticket> tickets;

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}

package com.csobrero.challenge.bean;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Bussines model {@link Ticket} bean
 * 
 * @author Christian
 * 
 */
@XmlRootElement
@XmlType(propOrder={"id", "price", "legs", "customer"})
public class Ticket {

	private Long id;
	private Collection<Leg> legs;
	private Customer customer;
	private float price;

	public Ticket() {
	}

	public Ticket(Collection<Leg> legs, float price) {
		super();
		this.legs = legs;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Leg> getLegs() {
		return legs;
	}

	public void setLegs(Collection<Leg> legs) {
		this.legs = legs;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@XmlAttribute
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}

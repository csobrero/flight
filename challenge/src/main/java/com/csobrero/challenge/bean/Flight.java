package com.csobrero.challenge.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Bussines model {@link Flight} bean
 * 
 * @author Christian
 * 
 */
@XmlRootElement
@XmlType(propOrder={"id", "code", "name"})
public class Flight {

	private Long id;
	private String code;
	private String name;
	
	public Flight() {
	}

	public Flight(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	@XmlAttribute
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlAttribute
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

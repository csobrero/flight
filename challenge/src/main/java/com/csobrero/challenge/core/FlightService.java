package com.csobrero.challenge.core;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.ElementClass;

import com.csobrero.challenge.rs.FlightFilterRequest;
import com.csobrero.challenge.rs.FlightFilterResponse;
import com.csobrero.challenge.rs.wrapper.Airports;

/**
 * Rest Service for search flights.
 * 
 * @author Christian
 *
 */
@Path("/search")
public interface FlightService {

	/**
	 * Perfom a flight search given filtering conditions
	 * 
	 * @param flightFilterRequest flight conditions to look for
	 * @return tickets availables
	 */
	@POST
	@Path("flight")
	@Consumes("application/xml")
	@Produces("application/xml")
	@ElementClass(response = FlightFilterResponse.class)
	public FlightFilterResponse search(@Description("Flight Filter Request") final FlightFilterRequest flightFilterRequest);

	/**
	 * IATA lookup resolver taking as input just 3 letters belonging to any airport
	 * 
	 * @param airport 3 consecutives lettes from airport location
	 * @return IATA code
	 */
	@GET
	@Path("/iatacode/{airportName}")
	@Produces("application/xml")
	public Airports lookupIATACode(@PathParam("airportName") final String airport);

	
	/**
	 * QR code generator based on base64 string
	 * 
	 * @param base64 input string
	 * @return qrcode as bytearray.
	 */
	@GET
	@Path("/qrcode/{base64}")
	public byte[] getQRCodeFromString(@PathParam("base64") final String base64);
	
}

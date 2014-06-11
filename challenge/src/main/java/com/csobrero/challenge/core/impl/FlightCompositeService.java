/**
 * 
 */
package com.csobrero.challenge.core.impl;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.csobrero.challenge.bean.FlightFilter;
import com.csobrero.challenge.bean.Ticket;
import com.csobrero.challenge.core.FlightSearchEngine;
import com.csobrero.challenge.core.FlightService;
import com.csobrero.challenge.core.IATAProvider;
import com.csobrero.challenge.core.Provider;
import com.csobrero.challenge.rs.FlightFilterRequest;
import com.csobrero.challenge.rs.FlightFilterResponse;
import com.csobrero.challenge.rs.wrapper.Airports;
import com.csobrero.challenge.util.ImageFormat;
import com.csobrero.challenge.util.ImageSize;
import com.csobrero.challenge.util.Utils;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.zxing.BarcodeFormat;

/**
 * Implementation class for Flight Search
 * 
 * @author Christian
 * 
 */

public final class FlightCompositeService implements FlightService {

	private FlightSearchEngine searchEngine;
	private List<? extends Provider> providers;

	public FlightFilterResponse search(final FlightFilterRequest request) {

		FlightFilter filter = request.retrieveFilter();
		
		List<Ticket> tickets = searchEngine.search(filter);
		
		FlightFilterResponse response = new FlightFilterResponse();
		response.setTickets(tickets);

		return response;
	}

	public Airports lookupIATACode(final String term) {

		if (Strings.isNullOrEmpty(term) || term.length() < 3)
			return null;

		Iterable<IATAProvider> iterable = Iterables.filter(providers,
				IATAProvider.class);

		IATAProvider iataProvider = Iterables.getFirst(iterable, null);

		return new Airports(iataProvider.lookup(term));
	}

	public byte[] getQRCodeFromString(final String base64) {

        Validate.notEmpty(base64);

        String decoded = null;
        try {
            decoded = new String(Utils.decodeBase64(base64.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("invalid content parameter");
        }

        return Utils.generateByteCode(BarcodeFormat.QR_CODE, ImageFormat.JPG, ImageSize.MEDIUM, decoded);
    }
	
	public FlightSearchEngine getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(FlightSearchEngine searchEngine) {
		this.searchEngine = searchEngine;
	}

	public List<? extends Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<? extends Provider> providers) {
		this.providers = providers;
	}

	

}

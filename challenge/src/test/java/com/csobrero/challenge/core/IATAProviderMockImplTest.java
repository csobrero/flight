package com.csobrero.challenge.core;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;

import com.csobrero.challenge.bean.Airport;
import com.csobrero.challenge.core.impl.IATAProviderMockImpl;

public class IATAProviderMockImplTest {

	@Test
	public void testEmptyList() {
		IATAProviderMockImpl provider = new IATAProviderMockImpl();
		List<Airport> list = provider.lookup("abc");
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testReturnResults() {
		IATAProviderMockImpl provider = new IATAProviderMockImpl();
		List<Airport> list = provider.lookup("lon");
		assertTrue(!list.isEmpty());
		for (Airport airportBean : list) {
			System.out.println(ReflectionToStringBuilder.toString(airportBean, ToStringStyle.MULTI_LINE_STYLE));
		}
	}

}

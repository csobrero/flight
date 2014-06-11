package com.csobrero.challenge.web.application;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.csobrero.challenge.web.page.MainPage;

public class FlightServiceWebApplication extends WebApplication{

	@Override
	public Class<? extends Page> getHomePage() {
		return MainPage.class;
	}
	
	@Override
	protected void init() {
		super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

}

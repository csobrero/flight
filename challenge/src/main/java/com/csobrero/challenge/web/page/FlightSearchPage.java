package com.csobrero.challenge.web.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.time.Duration;

import com.csobrero.challenge.bean.Airport;
import com.csobrero.challenge.core.FlightService;
import com.csobrero.challenge.rs.FlightFilterRequest;
import com.csobrero.challenge.rs.FlightFilterResponse;
import com.csobrero.challenge.rs.wrapper.Airports;
import com.csobrero.challenge.util.Utils;

/**
 * We should split control from model but for testing purposes is ok. Wicket is cool but weird sorry!
 * 
 * @author Christian
 *
 */
public class FlightSearchPage extends AbstractPage {
	
	private static final long serialVersionUID = 1L;
	
	private FlightFilterRequest bean = new FlightFilterRequest();
	
	@SpringBean(name="restClient")
	private FlightService restClient;
	
	public FlightSearchPage(final PageReference modalWindowPage, final ModalWindow window) {

        // create feedback panel to show errors
        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        add(feedback);

        // add form with markup id setter so it can be updated via ajax
        Form<FlightFilterRequest> form =
                new Form<FlightFilterRequest>("form", new CompoundPropertyModel<FlightFilterRequest>(bean));
        add(form);
        form.setOutputMarkupId(true);

        FormComponent<?> fc;

        // adding form components
        fc = new AutoCompleteTextField<String>("fromIATACode",
				new PropertyModel<String>(bean, "from"))
	            {
					private static final long serialVersionUID = 1L;

					@Override
	                protected Iterator<String> getChoices(final String input)
	                {
						return lookupIATACode(input);
	                }
	            };
        fc.setRequired(true);
        autoTextSelectedValue(fc);
        form.add(fc);

		fc = new AutoCompleteTextField<String>("toIATACode",
				new PropertyModel<String>(bean, "to"))
            {
				private static final long serialVersionUID = 1L;

				@Override
                protected Iterator<String> getChoices(final String input)
                {
					return lookupIATACode(input);
                }
            };
		fc.setRequired(true);
		autoTextSelectedValue(fc);
		form.add(fc);

		fc = new DateTextField("departDate", new PropertyModel<Date>(bean,
				"departDate"), buildStyleDateConverter());
		fc.add(buildDatePicker());
		fc.setRequired(true);
		form.add(fc);

		fc = new DateTextField("returnDate", new PropertyModel<Date>(bean,
				"returnDate"), buildStyleDateConverter());
		fc.add(buildDatePicker());
		fc.setRequired(true);
		form.add(fc);

		fc = new DropDownChoice<Integer>("adults", new PropertyModel<Integer>(bean, "adults"),
                		Arrays.asList(new Integer[]{0,1,2,3,4,5,6}));
        fc.setRequired(true);
        form.add(fc);

        fc = new DropDownChoice<Integer>("children", new PropertyModel<Integer>(bean, "children"), 
                		Arrays.asList(new Integer[]{0,1,2,3,4,5,6}));
        fc.setRequired(true);
        form.add(fc);

        // attach an ajax validation behavior to all form component's onkeydown
        // event and throttle it down to once per second

        AjaxFormValidatingBehavior.addToAllFormComponents(form, "onkeyup", Duration.ONE_SECOND);

        // add a button that can be used to submit the form via ajax
        form.add(new AjaxButton("submit", form) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // repaint the feedback panel so that it is hidden
            	FlightFilterResponse response = getRestClient().search(bean);
            	
				if (modalWindowPage != null && response != null) {
					String xmlResponse = Utils.JaxbMarshall(response).toString();
					((MainPage) modalWindowPage.getPage())
							.setFlightDetails(xmlResponse);
					((MainPage) modalWindowPage.getPage()).setQRCodeUrl("http://localhost:8080/rs/service/search/qrcode/"
							+ new String(Utils.encodeBase64(Utils.buildResponse(response).getBytes())));

				}
                
                target.add(feedback);
                window.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // repaint the feedback panel so errors are shown
                target.add(feedback);
            }
        });

    }

	private void autoTextSelectedValue(final FormComponent<?> fc) {
		final Label label = new Label("selected"+fc.getId(), fc.getDefaultModel());
		label.setOutputMarkupId(true);
		Form<?> f = (Form<?>)this.get("form");
		f.add(label);
		
		fc.add(new AjaxFormSubmitBehavior(f, "onchange")
		{
			private static final long serialVersionUID = 1L;

			@Override
		    protected void onSubmit(AjaxRequestTarget target)
		    {
		        target.add(label);
		    }

		    @Override
		    protected void onError(AjaxRequestTarget target)
		    {
		    }
		});
	}

	private DatePicker buildDatePicker() {
		DatePicker datePicker = new DatePicker()
        {
			private static final long serialVersionUID = 1L;

			@Override
            protected String getAdditionalJavaScript()
            {
                return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
            }
        };
        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);
		return datePicker;
	}

	private StyleDateConverter buildStyleDateConverter() {
		StyleDateConverter sdc = new StyleDateConverter("S-", true)
        {
			private static final long serialVersionUID = 1L;

			@Override
            public Locale getLocale()
            {
                return Session.get().getLocale();
            }
        };
		return sdc;
	}
	
	private Iterator<String> lookupIATACode(final String input) {
		List<String> list = Collections.emptyList();
        
        Airports airports;
        if(!Strings.isEmpty(input) && input.length()>2 
        		&& (airports = getRestClient().lookupIATACode(input))!=null && airports.getAirports()!=null) //call rest service
		{
        	list = new ArrayList<String>(10);
			for (Airport airport : airports.getAirports()) {
				list.add(airport.getIataCode()+", "
				+ airport.getCity()+", "+ airport.getState()+", "+ airport.getCountry()
				+", ("+airport.getDescription()+")");
			}
		}

        return list.iterator();
	}

	public FlightService getRestClient() {
		return restClient;
	}

	public void setRestClient(FlightService restClient) {
		this.restClient = restClient;
	}

}

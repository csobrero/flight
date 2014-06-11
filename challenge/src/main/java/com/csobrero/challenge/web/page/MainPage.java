package com.csobrero.challenge.web.page;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class MainPage extends AbstractPage {

	private static final long serialVersionUID = 1L;

	private String flightDetails;
	private String QRCodeUrl;

	public MainPage(final PageParameters parameters) {

		final MultiLineLabel multiLineLabel = new MultiLineLabel(
				"multiLineLabel", new PropertyModel<String>(this, "flightDetails"));
		add(multiLineLabel);
		multiLineLabel.setOutputMarkupId(true);
		
		final Image imgResult;
        add(imgResult = new Image("QRCodeIMG", ""));
        imgResult.add(new AttributeModifier("src", new AbstractReadOnlyModel<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public final String getObject() {
                return QRCodeUrl;
            }
        }));
        imgResult.setOutputMarkupId(true);

		// Flight Search Service MODAL
		final ModalWindow modal;
		add(modal = new ModalWindow("flightSearchModal"));
		modal.setCookieName("flightSearchModal-1");

		modal.setPageCreator(new ModalWindow.PageCreator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Page createPage() {
				return new FlightSearchPage(MainPage.this.getPageReference(),
						modal);
			}
		});
		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClose(AjaxRequestTarget target) {

				target.add(multiLineLabel);
				target.add(imgResult);

			}
		});
		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				return true;
			}
		});

		add(new AjaxLink<Void>("showFlightSearchModal") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if(getFlightDetails()!=null){
					setFlightDetails(null);
					setQRCodeUrl(null);
					target.add(multiLineLabel);
					target.add(imgResult);
				}
				modal.show(target);
			}
		});

	}

	public String getFlightDetails() {
		return flightDetails;
	}

	public void setFlightDetails(String flightDetails) {
		this.flightDetails = flightDetails;
	}

	public String getQRCodeUrl() {
		return QRCodeUrl;
	}

	public void setQRCodeUrl(String qRCodeUrl) {
		QRCodeUrl = qRCodeUrl;
	}

}

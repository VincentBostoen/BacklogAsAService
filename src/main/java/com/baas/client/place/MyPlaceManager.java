package com.baas.client.place;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class MyPlaceManager extends PlaceManagerImpl {

	  private PlaceRequest defaultPlaceRequest;

	@Inject
	  public MyPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter, @DefaultPlace String defaultPlace) {
	    super(eventBus, tokenFormatter);
	    this.defaultPlaceRequest = new PlaceRequest(defaultPlace);
	  }

	  @Override
	  public void revealDefaultPlace() {
	    revealPlace(defaultPlaceRequest);
	  }
}

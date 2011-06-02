package com.baas.client;

import com.baas.client.gin.BaasGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class Baas implements EntryPoint {

	public final BaasGinjector ginjector = GWT.create(BaasGinjector.class);

	public void onModuleLoad() {

		DelayedBindRegistry.bind(ginjector);

		ginjector.getPlaceManager().revealCurrentPlace();
	}
}

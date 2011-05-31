package com.perso.baas.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.perso.baas.client.gin.BaasGinjector;

public class Baas implements EntryPoint {

	public final BaasGinjector ginjector = GWT.create(BaasGinjector.class);

	public void onModuleLoad() {

		DelayedBindRegistry.bind(ginjector);

		ginjector.getPlaceManager().revealCurrentPlace();
	}
}

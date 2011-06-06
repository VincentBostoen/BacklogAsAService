package com.baas.server.guice;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceConfig extends GuiceServletContextListener {

	private static final String BAAS_SERVER_RESOURCES_PACKAGE = "com.baas.server.resources";
	private static final String JERSEY_API_JSON_POJO_MAPPING_FEATURE = "com.sun.jersey.api.json.POJOMappingFeature";
	private static final String JERSEY_CONFIG_PROPERTY_PACKAGES = "com.sun.jersey.config.property.packages";
	
	Logger log = Logger.getLogger(GuiceConfig.class.getName());

	protected Injector getInjector() {
		final Map<String, String> params = new HashMap<String, String>();

		params.put(JERSEY_CONFIG_PROPERTY_PACKAGES, BAAS_SERVER_RESOURCES_PACKAGE);
		params.put(JERSEY_API_JSON_POJO_MAPPING_FEATURE, "true");

		return Guice.createInjector(new ServerModule(), new DispatchServletModule());
	}
}

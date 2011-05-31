package com.perso.baas.server.guice;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.perso.baas.server.resources.ResourcePaths;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceConfig extends GuiceServletContextListener {

	private static final String BAAS_SERVER_RESOURCES_PACKAGE = "com.perso.baas.server.resources";
	private static final String JERSEY_API_JSON_POJO_MAPPING_FEATURE = "com.sun.jersey.api.json.POJOMappingFeature";
	private static final String JERSEY_CONFIG_PROPERTY_PACKAGES = "com.sun.jersey.config.property.packages";
	
	Logger log = Logger.getLogger(GuiceConfig.class.getName());

	protected Injector getInjector() {
		final Map<String, String> params = new HashMap<String, String>();

		params.put(JERSEY_CONFIG_PROPERTY_PACKAGES, BAAS_SERVER_RESOURCES_PACKAGE);
		params.put(JERSEY_API_JSON_POJO_MAPPING_FEATURE, "true");

		return Guice.createInjector(new GuiceModule(), new ServletModule() {
			@Override
			protected void configureServlets() {
				serve(ResourcePaths.BACKLOG_BASE_URL + "*").with(GuiceContainer.class, params);
			}
		});
	}
}

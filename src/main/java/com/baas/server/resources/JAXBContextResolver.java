package com.baas.server.resources;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import com.google.inject.Singleton;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

@Provider
@Singleton
public class JAXBContextResolver implements ContextResolver<JAXBContext> {
	
	/** Package that contains object that can be mapped */
	private static final String SHARED_OBJECT_PACKAGE = "com.baas.shared";
	
	private JAXBContext context;
	
	public JAXBContextResolver() throws Exception {
		this.context = new JSONJAXBContext(JSONConfiguration.natural().build(), SHARED_OBJECT_PACKAGE);
	}

	@Override
	public JAXBContext getContext(Class<?> arg0) {
		return context;
	}
}

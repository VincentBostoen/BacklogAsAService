package com.baas.client;

import com.google.gwt.junit.client.GWTTestCase;

public abstract class AbstractGWTTestCase extends GWTTestCase {

	/* (non-Javadoc)
	 * @see com.google.gwt.junit.client.GWTTestCase#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "com.baas.NoOpEntryPoint";
	}


}

package com.baas.client.resources;

import java.util.List;

import javax.ws.rs.GET;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.baas.shared.Backlog;

public interface BacklogsResource extends RestService {

	/**
	 * PATH to the backlogsd resource
	 */
	public static final String BACKLOGS_LIST = "resources/backlogs/list";

	@GET
	public void get(MethodCallback<List<Backlog>> callback);
}

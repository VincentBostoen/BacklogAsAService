package com.baas.client.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.baas.shared.core.Backlog;

public interface BacklogResource extends RestService {

	/**
	 * PATH to the backlog resource
	 */
	public static final String BACKLOG_PATH = "resources/backlog";

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long backlogId, MethodCallback<Boolean> callback);

	@POST
	public void post(Backlog backlog, MethodCallback<Backlog> callback);
}

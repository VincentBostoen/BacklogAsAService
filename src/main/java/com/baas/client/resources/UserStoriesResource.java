package com.baas.client.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.baas.shared.UserStory;

public interface UserStoriesResource extends RestService {
	
	/**
	 * Path to the stories list resource
	 */
	public static final String STORIES_RESOURCE_PATH = "resources/stories";

	@GET
	@Path("/frombacklog/{id}")
	public void list(@PathParam("id") long backlogId, MethodCallback<List<UserStory>> callback);
}

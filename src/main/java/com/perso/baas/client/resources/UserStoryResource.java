package com.perso.baas.client.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.perso.baas.shared.UserStory;

public interface UserStoryResource extends RestService {
	
	/**
	 * Path to the story resource
	 */
	public static final String STORY_RESOURCE_PATH = "resources/story";
	
	@GET
	@Path("/{id}")
	public void get(@PathParam("id") String storyId, MethodCallback<UserStory> callback);

	@POST
	public void post(UserStory story, MethodCallback<UserStory> callback);
}

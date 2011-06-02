package com.baas.client.place;

/**
 * This aims to contains all history token places
 */
public class PlaceTokens {
	/** HomePage */
	public static final String HOME = "home";
	/** Backlog consultation */
	public static final String BACKLOG = "backlog";
	/** Story consultation */
	public static final String STORY = "story";
	public static final String STORIES = "stories";

	public static final String ACTION_PARAM_KEY = "action";
	public static final String EDIT_ACTION_PARAM_KEY = "edit";
	public static final String NEW_ACTION_PARAM_KEY = "new";
	public static final String DELETE_ACTION_PARAM_KEY = "delete";
	public static final String LIST_STORIES_ACTION_PARAM_KEY = "listStories";
	public static final String SCRUM_BOARD = "scrumBoard";
	
	public static String getHome() {
		return HOME;
	}
}
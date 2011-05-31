package com.perso.baas.client.presenter;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.perso.baas.client.place.PlaceTokens;
import com.perso.baas.client.resources.BacklogResource;
import com.perso.baas.client.resources.UserStoriesResource;
import com.perso.baas.shared.UserStory;

public class UserStoriesPresenter extends Presenter<UserStoriesPresenter.MyView, UserStoriesPresenter.MyProxy> {

	public interface MyView extends View {
		public void setBacklog(String selectedBacklog);
		public void setStories(List<UserStory> stories);
		public SingleSelectionModel<UserStory> getSelectionModel();
		public Button getDeleteStoryButton();
		public Button getNewStoryButton();
	}

	@ProxyCodeSplit
	@NameToken(PlaceTokens.STORIES)
	public interface MyProxy extends ProxyPlace<UserStoriesPresenter> {
	}

	private UserStoriesResource userStoriesService;
	private BacklogResource backlogService;
	private PlaceManager placeManager;
	
	@Inject
	public UserStoriesPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		
		Resource userStoriesResource = new Resource(GWT.getModuleBaseURL() + UserStoriesResource.STORIES_RESOURCE_PATH);
		this.userStoriesService = GWT.create(UserStoriesResource.class);
		((RestServiceProxy)userStoriesService).setResource(userStoriesResource);
		
		Resource backlogResource = new Resource(GWT.getModuleBaseURL() + BacklogResource.BACKLOG_PATH);
		this.backlogService = GWT.create(BacklogResource.class);
		((RestServiceProxy)backlogService).setResource(backlogResource);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String selectedBacklog = placeManager.getCurrentPlaceHierarchy().get(0).getParameter("backlogId", null);
		getView().setBacklog(selectedBacklog);
		getBacklog(Long.parseLong(selectedBacklog));
	}

	private void getBacklog(long selectedBacklog) {
		userStoriesService.list(selectedBacklog, new MethodCallback<List<UserStory>>() {
			@Override
			public void onFailure(Method method, Throwable exception) {
			}

			@Override
			public void onSuccess(Method method, List<UserStory> stories) {
				getView().setStories(stories);
			}
		});
	}
	
	@Override
	protected void onBind() {
		super.onBind();
		final SingleSelectionModel<UserStory> selectionModel = getView().getSelectionModel();
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						UserStory story = selectionModel.getSelectedObject();
						if (story != null) {
							PlaceRequest myRequest = new PlaceRequest(PlaceTokens.STORY);
							myRequest = myRequest.with("storyId", story.getId() + "");
							myRequest = myRequest.with(PlaceTokens.ACTION_PARAM_KEY, PlaceTokens.EDIT_ACTION_PARAM_KEY);
							placeManager.revealRelativePlace(myRequest);
						}
					}
				});
		getView().getNewStoryButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest myRequest = new PlaceRequest(PlaceTokens.STORY);
				myRequest = myRequest.with(PlaceTokens.ACTION_PARAM_KEY, PlaceTokens.NEW_ACTION_PARAM_KEY);
				placeManager.revealRelativePlace(myRequest);
			}
		});
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();
		SingleSelectionModel<UserStory> selectionModel = getView().getSelectionModel();
		UserStory selectedObject = selectionModel.getSelectedObject();
		if(selectedObject != null){
			selectionModel.setSelected(selectedObject, false);
		}
	}
	
}

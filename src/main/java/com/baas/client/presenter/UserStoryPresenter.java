package com.baas.client.presenter;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.baas.client.place.PlaceTokens;
import com.baas.client.resources.UserStoryResource;
import com.baas.shared.UserStory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class UserStoryPresenter extends Presenter<UserStoryPresenter.MyView, UserStoryPresenter.MyProxy> {

	public interface MyView extends View {
		public void setStory(UserStory story);
		public UserStory getStory();
		public Button getCancelButton();
		public Button getConfirmButton();
	}

	@ProxyCodeSplit
	@NameToken(PlaceTokens.STORY)
	public interface MyProxy extends ProxyPlace<UserStoryPresenter>{}
	
	private UserStoryResource service;
	private PlaceManager placeManager;

	@Inject
	public UserStoryPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy);
		
		this.placeManager = placeManager;
		
		Resource resource = new Resource(GWT.getModuleBaseURL() + UserStoryResource.STORY_RESOURCE_PATH);
		this.service = GWT.create(UserStoryResource.class);
		((RestServiceProxy)this.service).setResource(resource);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
	
	@Override
	protected void onBind() {
		super.onBind();
		getView().getCancelButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				placeManager.revealRelativePlace(-1);
			}
		});
		
		getView().getConfirmButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				UserStory story = getView().getStory();

				Resource resource = new Resource(GWT.getModuleBaseURL() + UserStoryResource.STORY_RESOURCE_PATH + "/" + story.getId());
				((RestServiceProxy)UserStoryPresenter.this.service).setResource(resource);
				service.post(story, new MethodCallback<UserStory>() {
					@Override
					public void onFailure(Method method, Throwable exception) {
					}

					@Override
					public void onSuccess(Method method, UserStory story) {
						placeManager.revealRelativePlace(-1);
						Window.alert("La story " + story.getName() + " a été mise à jour avec succès.");
					}
				});
			}
		});
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String selectedStory = request.getParameter("storyId", null);
		String selectedBacklogId = placeManager.getCurrentPlaceHierarchy().get(0).getParameter("backlogId", null);
		
		String action = request.getParameter(PlaceTokens.ACTION_PARAM_KEY, PlaceTokens.LIST_STORIES_ACTION_PARAM_KEY);
		if(action.equals(PlaceTokens.EDIT_ACTION_PARAM_KEY) && selectedStory != null){
			editStory(selectedStory);
		}else if(action.equals(PlaceTokens.NEW_ACTION_PARAM_KEY)){
			newStory(selectedBacklogId);
		}
	}

	private void newStory(String backlogId) {
		if(backlogId == null ){
			Window.alert("Erreur lors de la récupération du backlog dans lequel créer la story");
			return;
		}
			
		UserStory userStory = new UserStory();
		userStory.setBacklogId(Long.parseLong(backlogId));
		getView().setStory(userStory);
	}

	private void editStory(String selectedStory) {
		Resource resource = new Resource(GWT.getModuleBaseURL() + UserStoryResource.STORY_RESOURCE_PATH);
		((RestServiceProxy)UserStoryPresenter.this.service).setResource(resource);
		this.service.get(selectedStory, new MethodCallback<UserStory>() {
			@Override
			public void onFailure(Method method, Throwable exception) {
			}

			@Override
			public void onSuccess(Method method, UserStory story) {
				getView().setStory(story);
			}
		});
	}
}

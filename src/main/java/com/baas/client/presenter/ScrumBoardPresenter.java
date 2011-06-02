package com.baas.client.presenter;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.baas.client.place.PlaceTokens;
import com.baas.client.resources.BacklogResource;
import com.baas.client.resources.UserStoriesResource;
import com.baas.shared.UserStory;
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

public class ScrumBoardPresenter extends Presenter<ScrumBoardPresenter.MyView, ScrumBoardPresenter.MyProxy> {

	public interface MyView extends View {
		void setStories(List<UserStory> stories);
	}

	@ProxyCodeSplit
	@NameToken(PlaceTokens.SCRUM_BOARD)
	public interface MyProxy extends ProxyPlace<ScrumBoardPresenter> {
	}

	private PlaceManager placeManager;
	private UserStoriesResource userStoriesService;
	
	@Inject
	public ScrumBoardPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		
		Resource userStoriesResource = new Resource(GWT.getModuleBaseURL() + UserStoriesResource.STORIES_RESOURCE_PATH);
		this.userStoriesService = GWT.create(UserStoriesResource.class);
		((RestServiceProxy)userStoriesService).setResource(userStoriesResource);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String selectedBacklog = placeManager.getCurrentPlaceHierarchy().get(0).getParameter("backlogId", null);
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
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}

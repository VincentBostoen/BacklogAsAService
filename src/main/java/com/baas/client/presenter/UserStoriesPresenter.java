package com.baas.client.presenter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baas.client.place.PlaceTokens;
import com.baas.client.presenter.event.backlog.BacklogSelectedEvent;
import com.baas.shared.DeleteUserStoriesAction;
import com.baas.shared.DeleteUserStoriesResult;
import com.baas.shared.GetStoryListAction;
import com.baas.shared.GetStoryListResult;
import com.baas.shared.core.UserStory;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class UserStoriesPresenter extends Presenter<UserStoriesPresenter.MyView, UserStoriesPresenter.MyProxy> {

	public interface MyView extends View {
		public void setBacklog(String selectedBacklog);
		public void setStories(List<UserStory> stories);
		public SelectionModel<UserStory> getSelectionModel();
		public Button getDeleteStoryButton();
		public Button getNewStoryButton();
		public CellTable<UserStory> getStoriesTable();
	}

	@ProxyCodeSplit
	@NameToken(PlaceTokens.STORIES)
	public interface MyProxy extends ProxyPlace<UserStoriesPresenter> {
	}

	private PlaceManager placeManager;
	private DispatchAsync dispatcher;
	private Set<UserStory> selectedStories;
	
	@Inject
	public UserStoriesPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String selectedBacklog = placeManager.getCurrentPlaceHierarchy().get(0).getParameter("backlogId", null);
		Long selectedBacklogId = null;
		if(selectedBacklog != null){
			selectedBacklogId = Long.parseLong(selectedBacklog);
			BacklogSelectedEvent.fire(this, selectedBacklogId);
		}
		getView().setBacklog(selectedBacklog);
		getBacklog(Long.parseLong(selectedBacklog));
	}

	private void getBacklog(long selectedBacklog) {
		dispatcher.execute(new GetStoryListAction(selectedBacklog), new AsyncCallback<GetStoryListResult>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(GetStoryListResult result) {
				getView().setStories(result.getStories());
			}
		});
	}
	
	@Override
	protected void onBind() {
		super.onBind();
		((MultiSelectionModel<UserStory>)getView().getSelectionModel()).clear();
		getView().getDeleteStoryButton().setEnabled(false);
		getView().getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				selectedStories = ((MultiSelectionModel<UserStory>)event.getSource()).getSelectedSet();
				getView().getDeleteStoryButton().setEnabled(!selectedStories.isEmpty());
			}
		});
		
		getView().getStoriesTable().addCellPreviewHandler(new CellPreviewEvent.Handler<UserStory>() {
			@Override
			public void onCellPreview(CellPreviewEvent<UserStory> event) {
				if(event.getNativeEvent().getType().equals(ClickEvent.getType().getName()) && event.getContext().getColumn() != 0){
					UserStory selectedStory = event.getValue();
					if (selectedStory != null) {
						PlaceRequest myRequest = new PlaceRequest(PlaceTokens.STORY);
						myRequest = myRequest.with("storyId", selectedStory.getId() + "");
						myRequest = myRequest.with(PlaceTokens.ACTION_PARAM_KEY, PlaceTokens.EDIT_ACTION_PARAM_KEY);
						placeManager.revealRelativePlace(myRequest);
				}
			}
		}});

		getView().getNewStoryButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest myRequest = new PlaceRequest(PlaceTokens.STORY);
				myRequest = myRequest.with(PlaceTokens.ACTION_PARAM_KEY, PlaceTokens.NEW_ACTION_PARAM_KEY);
				placeManager.revealRelativePlace(myRequest);
			}
		});
		
		getView().getDeleteStoryButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				deleteSelectedStories();
			}
		});
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();
	}

	private void deleteSelectedStories() {
		Set<Long> idsToDelete = new HashSet<Long>();
		for (UserStory story : selectedStories) {
			idsToDelete.add(story.getId());
		}
		dispatcher.execute(new DeleteUserStoriesAction(idsToDelete), new AsyncCallback<DeleteUserStoriesResult>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(DeleteUserStoriesResult result) {
				getView().getStoriesTable().getVisibleItems().remove(selectedStories);
			}
		});
	}
}

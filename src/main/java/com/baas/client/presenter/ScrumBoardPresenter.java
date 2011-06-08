package com.baas.client.presenter;

import java.util.List;

import com.baas.client.place.PlaceTokens;
import com.baas.shared.GetStoryListAction;
import com.baas.shared.GetStoryListResult;
import com.baas.shared.core.UserStory;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

public class ScrumBoardPresenter extends Presenter<ScrumBoardPresenter.MyView, ScrumBoardPresenter.MyProxy> {

	public interface MyView extends View {
		void setStories(List<UserStory> stories);
	}

	@ProxyCodeSplit
	@NameToken(PlaceTokens.SCRUM_BOARD)
	public interface MyProxy extends ProxyPlace<ScrumBoardPresenter> {
	}

	private PlaceManager placeManager;
	private DispatchAsync dispatcher;
	
	@Inject
	public ScrumBoardPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String selectedBacklog = placeManager.getCurrentPlaceHierarchy().get(0).getParameter("backlogId", null);
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
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}

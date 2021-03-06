package com.baas.client.presenter;

import com.baas.client.place.PlaceTokens;
import com.baas.client.presenter.event.backlog.BacklogDeletedEvent;
import com.baas.client.presenter.event.backlog.BacklogDeletedHandler;
import com.baas.client.presenter.event.backlog.BacklogSelectedEvent;
import com.baas.client.presenter.event.backlog.BacklogUpdatedEvent;
import com.baas.client.presenter.event.backlog.BacklogUpdatedHandler;
import com.baas.shared.core.Backlog;
import com.baas.shared.dispatch.DeleteBacklogAction;
import com.baas.shared.dispatch.DeleteBacklogResult;
import com.baas.shared.dispatch.GetBacklogAction;
import com.baas.shared.dispatch.GetBacklogResult;
import com.baas.shared.dispatch.UpdateBacklogAction;
import com.baas.shared.dispatch.UpdateBacklogResult;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class BacklogPresenter extends Presenter<BacklogPresenter.MyView, BacklogPresenter.MyProxy> implements BacklogUpdatedHandler, BacklogDeletedHandler{

	public interface MyView extends View {
		public void setBacklog(Backlog selectedBacklog);
		public Button getCancelButton();
		public Button getConfirmButton();
		public Backlog getBacklog();
	}

	@ProxyCodeSplit
	@NameToken(PlaceTokens.BACKLOG)
	public interface MyProxy extends ProxyPlace<BacklogPresenter> {
	}

	private PlaceManager placeManager;
	private DispatchAsync dispatcher;
	
	@Inject
	public BacklogPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
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
		String selectedBacklog = request.getParameter("backlogId", null);
		Long selectedBacklogId = null;
		if(selectedBacklog != null){
			selectedBacklogId = Long.parseLong(selectedBacklog);
			BacklogSelectedEvent.fire(this, selectedBacklogId);
		}
		
		String action = request.getParameter(PlaceTokens.ACTION_PARAM_KEY, PlaceTokens.LIST_STORIES_ACTION_PARAM_KEY);
		if(action.equals(PlaceTokens.LIST_STORIES_ACTION_PARAM_KEY)){
			PlaceRequest myRequest = new PlaceRequest(PlaceTokens.STORIES);
			placeManager.revealRelativePlace(myRequest);
		}else if(action.equals(PlaceTokens.DELETE_ACTION_PARAM_KEY) && selectedBacklogId != null){
			deleteBacklog(selectedBacklogId);
		}else if(action.equals(PlaceTokens.EDIT_ACTION_PARAM_KEY) && selectedBacklogId != null){
			editBacklog(selectedBacklogId);
		}else if(action.equals(PlaceTokens.NEW_ACTION_PARAM_KEY)){
			newBacklog();
		}
	}

	private void newBacklog() {
		getView().setBacklog(null);		
	}

	private void deleteBacklog(final long selectedBacklog) {
		dispatcher.execute(new DeleteBacklogAction(selectedBacklog), new AsyncCallback<DeleteBacklogResult>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(DeleteBacklogResult result) {
				BacklogDeletedEvent.fire(BacklogPresenter.this, selectedBacklog);
			}
		});
	}
	
	private void editBacklog(long selectedBacklog) {
		dispatcher.execute(new GetBacklogAction(selectedBacklog), new AsyncCallback<GetBacklogResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(GetBacklogResult result) {
				getView().setBacklog(result.getBacklog());
			}
		});
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
				Backlog backlog = getView().getBacklog();

				updateBacklog(backlog);
			}
		});
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();
	}

	@ProxyEvent
	@Override
	public void onBacklogDeleted(BacklogDeletedEvent event) {
		Window.alert("Le backlog a été supprimer avec succès.");
	}

	@ProxyEvent
	@Override
	public void onBacklogUpdated(BacklogUpdatedEvent event) {
		Window.alert("Le backlog " + event.getBacklog().getProjectName() + " a été mis à jour avec succès.");
	}

	private void updateBacklog(Backlog backlog) {
		dispatcher.execute(new UpdateBacklogAction(backlog), new AsyncCallback<UpdateBacklogResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(UpdateBacklogResult result) {
				BacklogUpdatedEvent.fire(BacklogPresenter.this, result.getUpdatedBacklog());
			}
		});
	}
}

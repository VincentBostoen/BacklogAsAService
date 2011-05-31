package com.perso.baas.client.presenter;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.perso.baas.client.place.PlaceTokens;
import com.perso.baas.client.presenter.event.RevealMenuEvent;
import com.perso.baas.client.presenter.event.RevealMenuHandler;
import com.perso.baas.client.presenter.event.backlog.BacklogUpdatedEvent;
import com.perso.baas.client.presenter.event.backlog.BacklogUpdatedHandler;
import com.perso.baas.client.presenter.event.backlogs.BacklogsListUpdatedEvent;
import com.perso.baas.client.presenter.event.backlogs.BacklogsListUpdatedHandler;
import com.perso.baas.client.resources.BacklogsResource;
import com.perso.baas.shared.Backlog;

public class MenuPresenter extends
		Presenter<MenuPresenter.MyView, MenuPresenter.MyProxy> implements
		RevealMenuHandler, BacklogUpdatedHandler, BacklogsListUpdatedHandler {

	public interface MyView extends View {
		void setBacklogs(List<Backlog> backlogs);
		ListBox getBacklogList();
		Anchor getListStoriesAnchor();
		Anchor getScrumBoardAnchor();
		Anchor getEditBacklogAnchor();
		Anchor getDeleteBacklogAnchor();
		Anchor getNewBacklogAnchor();
	}

	@ProxyStandard
	public interface MyProxy extends Proxy<MenuPresenter> {
	}

	private BacklogsResource backlogsService;
	private PlaceManager placeManager;
	private List<Backlog> backlogs;

	@Inject
	public MenuPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		Resource resource = new Resource(GWT.getModuleBaseURL() + BacklogsResource.BACKLOGS_LIST);

		this.backlogsService = GWT.create(BacklogsResource.class);
		((RestServiceProxy) backlogsService).setResource(resource);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMenuContent,
				this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		backlogsService.get(new MethodCallback<List<Backlog>>() {
			@Override
			public void onFailure(Method method, Throwable exception) {
			}

			@Override
			public void onSuccess(Method method, List<Backlog> backlogs) {
				BacklogsListUpdatedEvent.fire(MenuPresenter.this, backlogs);
			}
		});
		initHandlers();
	}

	private void initHandlers() {
		getView().getDeleteBacklogAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int selectedIndex = getView().getBacklogList()
						.getSelectedIndex();
				if (selectedIndex > 0) {
					PlaceRequest myRequest = new PlaceRequest(PlaceTokens.BACKLOG);
					myRequest = myRequest.with("backlogId", getView().getBacklogList().getValue(selectedIndex));
					myRequest = myRequest.with("action", "delete");
					placeManager.revealPlace(myRequest);
				} else {
					Window.alert("Veuillez sélectionner un backlog à supprimer");
				}
			}
		});
		getView().getEditBacklogAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int selectedIndex = getView().getBacklogList().getSelectedIndex();
				if (selectedIndex > 0) {
					PlaceRequest myRequest = new PlaceRequest(PlaceTokens.BACKLOG);
					myRequest = myRequest.with("backlogId", getView().getBacklogList().getValue(selectedIndex));
					myRequest = myRequest.with("action", "edit");
					placeManager.revealPlace(myRequest);
				} else {
					Window.alert("Veuillez sélectionner un backlog à éditer");
				}
			}
		});
		getView().getListStoriesAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int selectedIndex = getView().getBacklogList().getSelectedIndex();
				if (selectedIndex > 0) {
					PlaceRequest myRequest = new PlaceRequest(PlaceTokens.STORIES);
					placeManager.revealRelativePlace(myRequest);
				} else {
					Window.alert("Veuillez sélectionner un backlog");
				}
			}
		});
		getView().getNewBacklogAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest myRequest = new PlaceRequest(PlaceTokens.BACKLOG);
				myRequest = myRequest.with("action", PlaceTokens.NEW_ACTION_PARAM_KEY);
				placeManager.revealPlace(myRequest);
			}
		});
		getView().getScrumBoardAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int selectedIndex = getView().getBacklogList().getSelectedIndex();
				if (selectedIndex > 0) {
					PlaceRequest myRequest = new PlaceRequest(PlaceTokens.SCRUM_BOARD);
					placeManager.revealRelativePlace(myRequest);
				} else {
					Window.alert("Veuillez sélectionner un backlog");
				}
			}
		});
	}

	private void setBacklogs(List<Backlog> backlogs) {
		this.backlogs = backlogs;
		getView().getBacklogList().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String backlog = ((ListBox) event.getSource()).getValue(((ListBox) event.getSource()).getSelectedIndex());

				changePlaceFromSelectedBacklog(backlog);
			}
		});
		getView().setBacklogs(backlogs);
	}

	private void changePlaceFromSelectedBacklog(String backlog) {
		if (backlog == null) {
			return;
		}
		PlaceRequest myRequest = new PlaceRequest(PlaceTokens.BACKLOG);
		myRequest = myRequest.with("backlogId", backlog);
		placeManager.revealPlace(myRequest);
	}

	@ProxyEvent
	@Override
	public void onRevealMenu(RevealMenuEvent event) {
		forceReveal();
	}

	/**
	 * Return ids of backlogs that are already present in the backlogs list
	 * @return
	 */
	private List<Long> getBacklogIds() {
		Function<Backlog, Long> backlogIds = new Function<Backlog, Long>() {
			@Override
			public Long apply(Backlog backlog) {
				return backlog.getId();
			}
		};
		
		return Lists.transform(backlogs, backlogIds);
	}

	/**
	 * Method that is called when a backlog has been created or updated
	 */
	@ProxyEvent
	@Override
	public void onBacklogUpdated(BacklogUpdatedEvent event) {
		Backlog backlog = event.getBacklog();
		List<Long> backlogIds = getBacklogIds();
		int backlogIndex = getView().getBacklogList().getSelectedIndex();
		//If it's an update
		if(backlogIds.contains(backlog.getId())){
			getView().getBacklogList().removeItem(backlogIndex);
		}
		getView().getBacklogList().insertItem(backlog.getProjectName(), null, backlog.getId().toString(), backlogIndex);
		getView().getBacklogList().setSelectedIndex(backlogIndex);
	}
	
	/**
	 * Method that is callaed when the backlogs list is updated
	 */
	@ProxyEvent
	@Override
	public void onBacklogsListUpdated(BacklogsListUpdatedEvent event) {
		setBacklogs(event.getBacklogs());
	}
}

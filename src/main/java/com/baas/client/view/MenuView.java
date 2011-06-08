package com.baas.client.view;

import java.util.List;

import com.baas.client.presenter.MenuPresenter;
import com.baas.shared.core.Backlog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class MenuView extends ViewImpl implements MenuPresenter.MyView {

	private static final String NO_SELECTION_TEXT = "Sélectionnez un backlog";

	interface MenuViewUiBinder extends UiBinder<Widget, MenuView> {
	}

	private static MenuViewUiBinder uiBinder = GWT
			.create(MenuViewUiBinder.class);

	public final Widget widget;
		
	@UiField
	ListBox backlogList;
	@UiField
	Anchor scrumBoardAnchor;
	@UiField
	Anchor deleteBacklogAnchor;
	@UiField
	Anchor editBacklogAnchor;
	@UiField
	Anchor listStoriesAnchor;
	@UiField
	Anchor newBacklogAnchor;

	@Inject
	public MenuView() {
		widget = uiBinder.createAndBindUi(this);
	}


	public void setBacklogs(List<Backlog> backlogs) {
		backlogList.clear();
		backlogList.addItem(NO_SELECTION_TEXT, null, null);
		for (Backlog backlog : backlogs) {
			backlogList.addItem(backlog.getProjectName(), backlog.getId().toString());
		}
	}

	public ListBox getBacklogList() {
		return backlogList;
	}
	
	@Override
	public Anchor getListStoriesAnchor() {
		return listStoriesAnchor;
	}
	
	@Override
	public Anchor getScrumBoardAnchor() {
		return scrumBoardAnchor;
	}
	
	@Override
	public Anchor getEditBacklogAnchor() {
		return editBacklogAnchor;
	}
	
	@Override
	public Anchor getDeleteBacklogAnchor() {
		return deleteBacklogAnchor;
	}
	
	@Override
	public Anchor getNewBacklogAnchor() {
		return newBacklogAnchor;
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
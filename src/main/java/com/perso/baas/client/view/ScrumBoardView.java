package com.perso.baas.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.perso.baas.client.presenter.ScrumBoardPresenter;
import com.perso.baas.client.ui.components.ScrumBoardElement;
import com.perso.baas.shared.UserStory;

/**
 * View in which is displayed a Backlog
 */
public class ScrumBoardView extends ViewImpl implements ScrumBoardPresenter.MyView {

	interface ScrumBoardViewBinder extends UiBinder<Widget, ScrumBoardView> {
	}

	private static ScrumBoardViewBinder uiBinder = GWT.create(ScrumBoardViewBinder.class);

	public final Widget widget;
	
	@UiField
	FlowPanel scrumBoard;

	@Inject
	public ScrumBoardView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setStories(List<UserStory> stories) {
		for (UserStory userStory : stories) {
			scrumBoard.add(new ScrumBoardElement(userStory));
		}
	}
}

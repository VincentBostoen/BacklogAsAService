package com.baas.client.view;

import com.baas.client.presenter.MainPagePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class MainPageView extends ViewImpl implements MainPagePresenter.MyView {

	interface MainPageViewUiBinder extends UiBinder<Widget, MainPageView> {
	}

	private static MainPageViewUiBinder uiBinder = GWT.create(MainPageViewUiBinder.class);

	@UiField 
	SimplePanel contentPanel;
	@UiField
	SimplePanel menuPanel;
	
	public final Widget widget;

	@Inject
	public MainPageView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == MainPagePresenter.TYPE_SetMainContent) {
			setMainContent(content);
		} else if(slot == MainPagePresenter.TYPE_SetMenuContent) {
			setMenuContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
	
	private void setMenuContent(Widget content) {
		menuPanel.clear();

		if (content != null) {
			menuPanel.add(content);
		}
	}

	private void setMainContent(Widget content) {
		contentPanel.clear();

		if (content != null) {
			contentPanel.add(content);
		}
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public boolean hasMenuContent() {
		return menuPanel.getWidget() != null;
	}
}

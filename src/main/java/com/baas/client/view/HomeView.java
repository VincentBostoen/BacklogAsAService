package com.baas.client.view;

import com.baas.client.presenter.HomePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements HomePresenter.MyView {

	interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
	}

	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

	public final Widget widget;

	@Inject
	public HomeView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}

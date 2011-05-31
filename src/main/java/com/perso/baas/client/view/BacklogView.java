package com.perso.baas.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.perso.baas.client.presenter.BacklogPresenter;
import com.perso.baas.shared.Backlog;

/**
 * View in which is displayed a Backlog
 */
public class BacklogView extends ViewImpl implements BacklogPresenter.MyView {

	interface BacklogViewUiBinder extends UiBinder<Widget, BacklogView> {
	}

	private static BacklogViewUiBinder uiBinder = GWT
			.create(BacklogViewUiBinder.class);

	public final Widget widget;
	private Backlog backlog;

	/** Field container selected backlog name */
	@UiField
	TextBox backlogName;
	@UiField
	Button cancelButton;
	@UiField
	Button confirmButton;

	@Inject
	public BacklogView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public Button getCancelButton() {
		return cancelButton;
	}
	
	public Button getConfirmButton() {
		return confirmButton;
	}
	
	/**
	 * Fill what should be with the selected backlog
	 */
	@Override
	public void setBacklog(Backlog selectedBacklog) {
		clear();
		if(selectedBacklog == null){
			selectedBacklog = new Backlog();
		}
		this.backlog = selectedBacklog;
		if(this.backlog.getProjectName() != null) {
			backlogName.setText(selectedBacklog.getProjectName());
		}
	}

	private void clear() {
		backlogName.setText("");		
	}

	@Override
	public Backlog getBacklog() {
		this.backlog.setProjectName(backlogName.getText());
		return this.backlog;
	}
}

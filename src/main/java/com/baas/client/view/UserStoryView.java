package com.baas.client.view;

import com.baas.client.presenter.UserStoryPresenter;
import com.baas.shared.Complexity;
import com.baas.shared.UserStory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class UserStoryView extends ViewImpl implements
		UserStoryPresenter.MyView {

	interface UserStoryViewUiBinder extends UiBinder<Widget, UserStoryView> {
	}

	private static UserStoryViewUiBinder uiBinder = GWT
			.create(UserStoryViewUiBinder.class);

	public final Widget widget;

	@UiField
	Button cancelButton;
	@UiField
	Button confirmButton;
	@UiField
	TextBox storyName;
	@UiField
	TextBox moduleName;
	@UiField
	TextArea description;
	@UiField
	ListBox complexityList;
	@UiField
	TextBox businessValue;

	private UserStory story;

	@Inject
	public UserStoryView() {
		widget = uiBinder.createAndBindUi(this);
		setComplexities();
	}

	@Override
	public void setStory(UserStory story) {
		this.story = story;
		clear();
		if (story.getName() != null) {
			storyName.setText(story.getName());
		}
		if (story.getModuleName() != null) {
			moduleName.setText(story.getModuleName());
		}
		if (story.getDescription() != null) {
			description.setText(story.getDescription());
		}
		if (story.getComplexity() != null) {
			for (int i = 0; i < complexityList.getItemCount(); i++) {
				if (complexityList.getItemText(i).equals(
						story.getComplexity().getName())) {
					complexityList.setSelectedIndex(i);
				}
			}
		}
		if (story.getBusinessValue() != null) {
			businessValue.setText(story.getBusinessValue() + "");
		}
	}

	private void clear() {
		storyName.setText("");
		moduleName.setText("");
		description.setText("");
		if (complexityList.getItemCount() > 0) {
			complexityList.setSelectedIndex(0);
		}
		businessValue.setText("");
	}

	@Override
	public UserStory getStory() {
		story.setBusinessValue(Integer.parseInt(businessValue.getText()));
		story.setComplexity(Complexity.valueOf(complexityList
				.getValue(complexityList.getSelectedIndex())));
		story.setName(storyName.getText());
		story.setModuleName(moduleName.getText());
		story.setDescription(description.getText());
		return story;
	}

	private void setComplexities() {
		for (Complexity complexity : Complexity.values()) {
			complexityList.addItem(complexity.getName(), complexity.getId());
		}
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public Button getConfirmButton() {
		return confirmButton;
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}

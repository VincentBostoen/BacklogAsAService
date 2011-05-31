package com.perso.baas.client.ui.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.perso.baas.shared.UserStory;

public class ScrumBoardElement extends Composite {

	interface ScrumBoardUiBinder extends UiBinder<Widget, ScrumBoardElement> {
	}
	
	private static ScrumBoardUiBinder uiBinder = GWT.create(ScrumBoardUiBinder.class);

	@UiField
	Label storyNumber;
	@UiField
	Label storyName;
	@UiField
	Label storyComplexity;
	@UiField
	Label storyBusinessValue;
	
	public ScrumBoardElement() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ScrumBoardElement(UserStory userStory) {
		this();
		storyNumber.setText("");
		storyName.setText(userStory.getName());
		storyComplexity.setText(userStory.getComplexity() + "");
		storyBusinessValue.setText(userStory.getBusinessValue() + "");
	}
}

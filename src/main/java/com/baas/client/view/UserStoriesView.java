package com.baas.client.view;

import java.util.List;

import com.baas.client.presenter.UserStoriesPresenter;
import com.baas.shared.core.UserStory;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * View in which is displayed a Backlog
 */
public class UserStoriesView extends ViewImpl implements UserStoriesPresenter.MyView {

	interface UserStoriesViewBinder extends UiBinder<Widget, UserStoriesView> {
	}

	private static UserStoriesViewBinder uiBinder = GWT
			.create(UserStoriesViewBinder.class);

	public final Widget widget;

	/** Field container selected backlog name */
	@UiField
	Label backlogName;
	/** Number of stories assigned to the backlog */
	@UiField
	Label nbStories;
	/** Number of stories for the active iteration */
	@UiField
	Label nbStoriesActual;
	/** Number of stories done */
	@UiField
	Label nbStoriesDone;
	/** Number of stories that have not been started */
	@UiField
	Label nbStoriesTodo;
	/** Widget that contains the table in which are displayed stories */
	@UiField
	Button deleteStoryButton;
	@UiField
	Button newStoryButton;
	/** Table in which are displayed stories */
	@UiField(provided = true)
	CellTable<UserStory> storiesTable;
	/** Table pager */
	@UiField(provided = true)
	SimplePager pager;
	/** SelectionModel associated to the table */
	private SelectionModel<UserStory> selectionModel;


	@Inject
	public UserStoriesView() {
		initTable();
		widget = uiBinder.createAndBindUi(this);
	}


	/**
	 * Construct the table, create columns, add it to the table, add table to
	 * parent container
	 */
	private void initTable() {
		storiesTable = new CellTable<UserStory>(UserStory.KEY_PROVIDER);
		
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	    pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	    pager.setDisplay(storiesTable);
	    
	    selectionModel = new MultiSelectionModel<UserStory>(UserStory.KEY_PROVIDER);
	    storiesTable.setSelectionModel(selectionModel, DefaultSelectionEventManager.<UserStory> createCheckboxManager());
	    storiesTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	    storiesTable.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
	        
		initColumns();
	}


	private void initColumns() {
		final CheckboxCell selectionCell = new CheckboxCell(false, false);
	    final Column<UserStory, Boolean> checkColumn = new Column<UserStory, Boolean>(selectionCell){
	    	@Override
		      public Boolean getValue(UserStory object) {
		        return selectionModel.isSelected(object);
		      }
	    };
		
	    storiesTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
	    storiesTable.setColumnWidth(checkColumn, 40, Unit.PX);
	    
		TextColumn<UserStory> sprintNumberColumn = new TextColumn<UserStory>() {
			@Override
			public String getValue(UserStory story) {
				return story.getSprintNumber() + "";
			}
		};
		TextColumn<UserStory> storyNumberColumn = new TextColumn<UserStory>() {
			@Override
			public String getValue(UserStory story) {
				return "";
			}
		};
		TextColumn<UserStory> nameColumn = new TextColumn<UserStory>() {
			@Override
			public String getValue(UserStory story) {
				return story.getName();
			}
		};
		TextColumn<UserStory> descriptionColumn = new TextColumn<UserStory>() {
			@Override
			public String getValue(UserStory story) {
				return story.getDescription();
			}
		};
		TextColumn<UserStory> statusColumn = new TextColumn<UserStory>() {
			@Override
			public String getValue(UserStory story) {
				return "En cours";
			}
		};
		TextColumn<UserStory> complexityColumn = new TextColumn<UserStory>() {
			@Override
			public String getValue(UserStory story) {
				return story.getComplexity().getName();
			}
		};
		TextColumn<UserStory> businessValueColumn = new TextColumn<UserStory>() {
			@Override
			public String getValue(UserStory story) {
				return story.getBusinessValue() + "";
			}
		};
		
		storiesTable.addColumn(sprintNumberColumn, "Itération");
		storiesTable.addColumn(storyNumberColumn, "N° Story");
		storiesTable.addColumn(nameColumn, "Intitulé");
		storiesTable.addColumn(descriptionColumn, "Description");
		storiesTable.addColumn(statusColumn, "Status");
		storiesTable.addColumn(complexityColumn, "Complexité");
		storiesTable.addColumn(businessValueColumn, "Valeur métier");
	}

	/**
	 * Set the stories, fill the table and the labels that are based on stories
	 * information
	 */
	@Override
	public void setStories(List<UserStory> stories) {
		clear();
		nbStories.setText(stories.size() + "");

		storiesTable.setRowCount(stories.size(), true);
		storiesTable.setRowData(0, stories);
	}

	/**
	 * Clear the view
	 */
	private void clear() {
		nbStories.setText("");
		nbStoriesActual.setText("");
		nbStoriesDone.setText("");
		nbStoriesTodo.setText("");
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public SelectionModel<UserStory> getSelectionModel() {
		return selectionModel;
	}
	
	/**
	 * Fill what should be with the selected backlog
	 */
	@Override
	public void setBacklog(String selectedBacklog) {
		backlogName.setText(selectedBacklog);
	}

	public Button getNewStoryButton() {
		return newStoryButton;
	}


	public Button getDeleteStoryButton() {
		return deleteStoryButton;
	}

	public CellTable<UserStory> getStoriesTable() {
		return storiesTable;
	}
}

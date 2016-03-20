package ui.Controllers;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.ArrayList;
import java.util.logging.Logger;
import ui.Controllers.MainGUIController;
import logic.Task;
import shared.LogHandler;

public class ListInterfaceController {

	private static final String COMPLETED_TAB = "Completed";
	private static final String INBOX_TAB = "Inbox";
	private static final String INDEX_ID = "taskIndex";
	private static final String TASK_ID = "taskName";
	private static final String DATE_ID = "taskDate";
	private static final String TIME_ID = "taskTime";
	private static final String DUE = "Due by ";

	private static final boolean OPEN_LIST = true;
	private static final boolean CLOSE_LIST = false;

	private MainGUIController main;

	@FXML 
	private ListView<HBox> todoList;
	@FXML 
	private HBox todoListContainer;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab tabInbox = new Tab(INBOX_TAB);
	private Tab tabComplete = new Tab(COMPLETED_TAB);


	private static ObservableList<HBox> tasks =
			FXCollections.observableArrayList();
	private static ObservableList<HBox> completed =
			FXCollections.observableArrayList();
	private static ArrayList<Tab> tabs =
			new ArrayList<Tab>();

	private ArrayList<Task> operatingTaskFromLogic;
	private Logger log = LogHandler.retriveLog();
	
	private TaskFilter taskFilter = new TaskFilter();

	public void init(MainGUIController mainController) {
		main = mainController;
		hideToDoList();
		loopTaskList();
		initTabPane();
	}

	private void initTabPane() {
		tabs.add(tabInbox);
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if (newValue.getContent() == null) {
					if(newValue.equals(tabInbox)) {
						displayPending();
					} else if (newValue.equals(tabComplete)) {
						displayCompleted();
					}
					newValue.setContent(todoList);
					oldValue.setContent(null);
				}   
			}
		});
	}

	public void loopTaskList() {
		tasks.clear();
		operatingTaskFromLogic = main.getFromLogic();

		log.info("operatingTaskFromLogic empty? " + operatingTaskFromLogic.isEmpty());

		if(!operatingTaskFromLogic.isEmpty()) {
			openToDoList();
		}

		displayTaskList();

		if(tasks.isEmpty()) {
			closeToDoList();
		}
	}

	private void animateToDoList(boolean isOpen) {
		if(isOpen) {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), todoListContainer);
			st.setFromX(0);
			st.setToX(1);
			st.setCycleCount(1);
			st.play();
			todoListContainer.setManaged(true);
		} else {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), todoListContainer);
			st.setFromX(1);
			st.setToX(0);
			st.setCycleCount(1);
			st.play();
			todoListContainer.setManaged(false);
		}
	}

	private void displayTaskList() {
		formatTaskToListCell(operatingTaskFromLogic);
		todoList.setItems(tasks);
		loadClassList();
	}

	private void formatTaskToListCell(ArrayList<Task> taskList) {	
		for(Task taskObj: taskList) {
			HBox taskRow = new HBox(10);
			//CheckBox isCompleted = new CheckBox();
			Label taskIndex = new Label(taskObj.get_index() + ".");
			Label taskName = new Label(taskObj.get_task());
			Label taskTime;
			if(taskObj.get_time().isEmpty()) {
				taskTime = new Label(taskObj.get_time());
			} else {
				taskTime = new Label(DUE + taskObj.get_time());
			}
			Label taskDate;
			if(!taskObj.get_date().isEmpty() && taskObj.get_time().isEmpty()) {
				taskDate = new Label(DUE + taskObj.get_date());
			} else {
				taskDate = new Label(taskObj.get_date());
			} 
			setProperties(taskIndex, taskName, taskTime, taskDate, taskRow);

			if(todoListContainer.getScaleX() == 0) {
				animateToDoList(OPEN_LIST);
			}
			//isCompleted.setOnAction(e -> handleCheckedBox(isCompleted, taskRow));
			taskRow.getChildren().addAll(taskIndex, taskName, taskTime, taskDate);
			
			taskFilter.sortTasksByClasses(taskObj, taskRow);
		}	

		taskFilter.addSortedClasses(tasks);
	}

	/*
	private void handleCheckedBox(CheckBox cb, HBox hb) {
		if(cb.isSelected()) {
			loadClassList();
			// main.loadCompleted();
			completed.add(hb);
			tasks.remove(hb);
		}

		if(!cb.isSelected()) {
			completed.remove(hb);
			tasks.add(hb);
			// main.clearCompleted();
		}
	}*/

	private void setProperties(Label index, Label name, Label date, Label time, HBox task) {
		task.setPrefWidth(600);

		HBox.setHgrow(name, Priority.ALWAYS);
		index.setPrefWidth(30);
		index.setMaxWidth(Double.MAX_VALUE);
		index.setId(INDEX_ID);

		HBox.setHgrow(name, Priority.ALWAYS);
		name.setPrefWidth(400);
		name.setMaxWidth(Double.MAX_VALUE);
		name.setId(TASK_ID);

		HBox.setHgrow(date, Priority.ALWAYS);
		date.setPrefWidth(80);
		date.setId(DATE_ID);

		HBox.setHgrow(time, Priority.ALWAYS);
		time.setPrefWidth(100);
		time.setId(TIME_ID);
	}

	private void loadClassList() {
		log.info("Tab Size? " + tabs.size());
		if(tabs.size() == 1 && !operatingTaskFromLogic.isEmpty()) {
			tabs.add(tabComplete);
			tabPane.getTabs().add(tabComplete);
		} 
	}

	private void openToDoList() {
		if(tasks.isEmpty() && completed.isEmpty()) {	
			if(main.isMainPaneManaged()) {
				todoListContainer.setManaged(true);
				todoListContainer.setOpacity(1);

				main.removeMainPane();
				animateToDoList(OPEN_LIST);
			}
		}
	}

	public void closeToDoList() {
		if(todoListContainer.getScaleX() == 1) {
			animateToDoList(CLOSE_LIST);
		}
	}

	public void hideToDoList() {
		todoListContainer.setManaged(false);
		todoListContainer.setOpacity(0);
	}
	
	public TabPane getTabPane() {
		return tabPane;
	}

	public void displayPending() {
		todoList.setItems(tasks);
	}

	public void displayCompleted() {
		todoList.setItems(completed);
	}

	public ObservableList<HBox> getTasks() {
		return tasks;
	}

	public ObservableList<HBox> getCompleted() {
		return completed;
	}

}
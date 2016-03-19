package ui.Controllers;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

public class ListInterfaceController {

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
	private Tab tabInbox = new Tab("Inbox");
	private Tab tabComplete = new Tab("Completed");


	private static ObservableList<HBox> tasks =
			FXCollections.observableArrayList();
	private static ObservableList<HBox> completed =
			FXCollections.observableArrayList();
	private static ArrayList<HBox> tasksToday = new ArrayList<HBox>();
	private static ArrayList<HBox> tasksTomorrow = new ArrayList<HBox>();
	private static ArrayList<HBox> tasksOthers= new ArrayList<HBox>();
	private static ArrayList<HBox> tasksFloat = new ArrayList<HBox>();
	private static ArrayList<Tab> tabs =
			new ArrayList<Tab>();

	private ArrayList<Task> operatingTaskFromLogic;
	private Logger log = LogHandler.retriveLog();

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
		//operatingTaskFromLogic = main.getFromLogic();
		formatTaskToListCell(operatingTaskFromLogic);
		todoList.setItems(tasks);
		loadClassList();
	}

	private void formatTaskToListCell(ArrayList<Task> taskList) {
		HBox taskClassToday = new HBox();
		Label taskToday = new Label("Today");
		taskToday.setId("taskToday");
		taskClassToday.getChildren().add(taskToday);
		
		HBox taskClassTomorrow = new HBox();
		Label taskTomorrow = new Label("Tomorrow");
		taskTomorrow.setId("taskTomorrow");
		taskClassTomorrow.getChildren().add(taskTomorrow);
		
		HBox taskClassOthers = new HBox();
		Label taskOthers = new Label("Follow Up");
		taskOthers.setId("taskOthers");
		taskClassOthers.getChildren().add(taskOthers);
		
		HBox taskClassFloat = new HBox();
		Label taskFloat = new Label("Floating");
		taskFloat.setId("taskFloat");
		taskClassFloat.getChildren().add(taskFloat);
		
		LocalDate localDate = new LocalDate();
		String dateToday = localDate.toString("dd/MM/yy");
		String dateTomorrow = localDate.plusDays(1).toString("dd/MM/yy");
		
		for(Task taskObj: taskList) {
			HBox taskRow = new HBox(10);
			CheckBox isCompleted = new CheckBox();
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
			isCompleted.setOnAction(e -> handleCheckedBox(isCompleted, taskRow));
			taskRow.getChildren().addAll(isCompleted, taskIndex, taskName, taskTime, taskDate);
			
			
			if(taskObj.get_date().equals(dateToday) 
					|| (!taskObj.get_time().isEmpty() && taskObj.get_date().isEmpty())) {
				if(!tasksToday.contains(taskClassToday)) {
					tasksToday.add(taskClassToday);
					
				}
				//System.out.println(tasksToday.size());
				tasksToday.add(taskRow);
			} else if(taskObj.get_date().equals(dateTomorrow)) {
				if(!tasksTomorrow.contains(taskClassTomorrow)) {
					tasksTomorrow.add(taskClassTomorrow);
				}
				tasksTomorrow.add(taskRow);
			} else if(taskObj.get_time().isEmpty() && taskObj.get_date().isEmpty()) {
				if(!tasksFloat.contains(taskClassFloat)) {
					tasksFloat.add(taskClassFloat);
				}
				tasksFloat.add(taskRow);
			} else {
				if(!tasksOthers.contains(taskClassOthers)) {
					tasksOthers.add(taskClassOthers);
				}
				tasksOthers.add(taskRow);
			}
		}
		
		int taskNum = 1;
		while(taskNum <= tasksToday.size() + tasksTomorrow.size() +
				tasksFloat.size() + tasksOthers.size()) {
			if(taskNum <= tasksToday.size()) {
				tasks.add(tasksToday.get(taskNum-1));
			} else if(taskNum >= tasksToday.size() && 
					(taskNum <= tasksTomorrow.size()+tasksToday.size())) {
				tasks.add(tasksTomorrow.get(taskNum-tasksToday.size()-1));
			} else if(taskNum >= tasksToday.size()+tasksTomorrow.size() && 
					(taskNum <= tasksOthers.size()+tasksTomorrow.size()+tasksToday.size())) {
				tasks.add(tasksOthers.get(taskNum-tasksToday.size()-tasksTomorrow.size()-1));
			} else if(taskNum >= tasksToday.size()+tasksTomorrow.size()+tasksOthers.size()) {
				tasks.add(tasksFloat.get(taskNum-tasksToday.size()-tasksTomorrow.size()-tasksOthers.size()-1));
			} 
			taskNum++;
		}
		tasksToday.clear();
		tasksTomorrow.clear();
		tasksOthers.clear();
		tasksFloat.clear();
		
	}

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
	}

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
		//todoList.setItems(completed);
	}

	public ObservableList<HBox> getTasks() {
		return tasks;
	}

	public ObservableList<HBox> getCompleted() {
		return completed;
	}

}
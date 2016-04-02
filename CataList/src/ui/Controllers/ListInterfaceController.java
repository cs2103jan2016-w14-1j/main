package ui.Controllers;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
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
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import ui.Controllers.MainGUIController;
import logic.Task;
import shared.LogHandler;

public class ListInterfaceController extends NotificationRenderer {

	private static final int REMINDER_TIME = 15;
	private static final int TIME_FLAG = 1;
	private static final int DAY_FLAG = 0;
	private static final int INIT_SCROLL = 0;
	
	private static final String COMPLETED_TAB = "Completed";
	private static final String INBOX_TAB = "All Tasks";
	private static final String PENDING_TAB = "Pending";
	
	private static final String INDEX_ID = "taskIndex";
	private static final String TASK_ID = "taskName";
	private static final String DATE_ID = "taskDate";
	private static final String TIME_ID = "taskTime";
	private static final String COMPLETED_TASK_ID = "completedTaskName";
	
	private static final String EMPTY_LIST_FEEDBACK = "Your task list is empty.";
	private static final String EMPTY_LIST_MESSAGE = "Take a break and enjoy your day! You deserve it!";
	private static final String EMPTY_LIST_ID = "emptyRow";
	private static final String DUE = "Due by ";
	private static final String NULL = "";
	
	private static final String DATE_FORMAT = "dd/MM/yy";
	private static final String TIME_FORMAT = "HHmm";

	private static final boolean OPEN_LIST = true;
	private static final boolean CLOSE_LIST = false;

	private MainGUIController main;

	@FXML private ListView<HBox> todoList;
	@FXML private HBox todoListContainer;
	@FXML private TabPane tabPane;
	@FXML private Tab tabInbox = new Tab(INBOX_TAB);
	
	private Tab tabComplete = new Tab(COMPLETED_TAB);
	private Tab tabPending = new Tab(PENDING_TAB);

	private static ObservableList<HBox> tasks =
			FXCollections.observableArrayList();
	private static ObservableList<HBox> completed =
			FXCollections.observableArrayList();
	private static ArrayList<Tab> tabs =
			new ArrayList<Tab>();

	private ArrayList<Task> operatingTasksFromLogic = new ArrayList<Task>();
	private ArrayList<Task> pendingTasksFromLogic = new ArrayList<Task>();
	private ArrayList<Task> completedTasksFromLogic = new ArrayList<Task>();
	
	private int previousTasksSize;
	private int previousCompletedSize;
	private HBox scrollSelection = new HBox();
	private Logger log = LogHandler.retriveLog();
	private TaskFilter taskFilter = new TaskFilter();
	private NotificationRenderer notification = new NotificationRenderer();

	public void init(MainGUIController mainController) {
		main = mainController;

		initTabPane();
		loopTaskList();
		hideToDoList();
		loopCheckTasksForReminder();
		setTaskIntoViewIndex(INIT_SCROLL);
	}

	private void initTabPane() {
		tabs.add(tabInbox);
		//tabs.add(tabPending);
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
		previousTasksSize = operatingTasksFromLogic.size();
		previousCompletedSize = completedTasksFromLogic.size();
		
		tasks.clear();
		completed.clear();
		
		operatingTasksFromLogic = main.getOperatingTasksFromLogic();
		completedTasksFromLogic = main.getCompletedTasksFromLogic();
		pendingTasksFromLogic = main.getPendingTasksFromLogic();

		log.info("operatingTaskFromLogic empty? " + operatingTasksFromLogic.isEmpty());
		
		openToDoList();
		displayTaskList();
	}

	private void loopCheckTasksForReminder() {
		Timer checkTasks = new Timer(true);
		checkTasks.schedule(new TimerTask() {
			@Override
			public void run() {  
				Platform.runLater(new Runnable() {
					public void run() {
						checkTasksForReminder();
					}
				});

			}
		}, 0, 60000);
	}

	private void animateToDoList(boolean isOpen) {
		if(isOpen) {
			ScaleTransition st = new ScaleTransition(Duration.millis(400), todoListContainer);
			st.setFromX(0);
			st.setToX(1);
			st.setCycleCount(1);
			st.setDelay(Duration.millis(200));
			st.play();
			todoListContainer.setManaged(true);
		} else {
			ScaleTransition st = new ScaleTransition(Duration.millis(400), todoListContainer);
			st.setFromX(1);
			st.setToX(0);
			st.setCycleCount(1);
			st.setDelay(Duration.millis(200));
			st.play();
			todoListContainer.setManaged(false);
		}
	}

	private void displayTaskList() {
		formatPendingTaskToListCell(operatingTasksFromLogic);
		setTaskIntoViewObject(scrollSelection);
		openCompleteTab();
		formatCompletedTaskToListCell(completedTasksFromLogic);
	}

	private void formatPendingTaskToListCell(ArrayList<Task> taskList) {
		int index = 0;
		for(Task taskObj: taskList) {
			index++;
			HBox taskRow = new HBox(10);
			Label taskIndex = new Label("  " + index + ".");
			Label taskName = new Label(taskObj.get_task());
			Label taskTime;
			if(taskObj.get_startTime().isEmpty()) {
				taskTime = new Label(taskObj.get_startTime());
			} else {
				taskTime = new Label(DUE + taskObj.get_startTime());
			}
			Label taskDate;
			if(!taskObj.get_startDate().isEmpty() && taskObj.get_startTime().isEmpty()) {
				taskDate = new Label(DUE + taskObj.get_startDate());
			} else {
				taskDate = new Label(taskObj.get_startDate());
			} 
			setProperties(taskIndex, taskName, taskTime, taskDate, taskRow);

			if(todoListContainer.getScaleX() == 0) {
				animateToDoList(OPEN_LIST);
			}
			
			if(index == taskList.size() && taskList.size() != previousTasksSize) {
				scrollSelection = taskRow;
			} 
			
			taskRow.getChildren().addAll(taskIndex, taskName, taskTime, taskDate);
			taskFilter.sortTasksByClasses(taskObj, taskRow);
		}	
		taskFilter.addSortedClasses(tasks);
		insertFeedbackForEmptyList(taskList);
		todoList.setItems(tasks);
	}
	
	private void formatCompletedTaskToListCell(ArrayList<Task> taskList) {
		int index = 0;
		for(Task taskObj: taskList) {
			index++;
			HBox taskRow = new HBox(10);
			Label taskIndex = new Label("  " + index + ".");
			Label taskName = new Label(taskObj.get_task());
			Label taskTime;
			if(taskObj.get_startTime().isEmpty()) {
				taskTime = new Label(taskObj.get_startTime());
			} else {
				taskTime = new Label(DUE + taskObj.get_startTime());
			}
			Label taskDate;
			if(!taskObj.get_startDate().isEmpty() && taskObj.get_startTime().isEmpty()) {
				taskDate = new Label(DUE + taskObj.get_startDate());
			} else {
				taskDate = new Label(taskObj.get_startDate());
			} 
			setProperties(taskIndex, taskName, taskTime, taskDate, taskRow);
			taskName.setId(COMPLETED_TASK_ID);
			taskRow.getChildren().addAll(taskIndex, taskName, taskTime, taskDate);
			completed.add(taskRow);
		}
	}
	
	private void setTaskIntoViewIndex(int index) {
		if(tasks.size() > 1) {
			todoList.scrollTo(index);
		}
	}
	
	private void setTaskIntoViewObject(HBox obj) {
		if(tasks.contains(obj)) {
			todoList.scrollTo(obj);
		}
	}

	private void insertFeedbackForEmptyList(ArrayList<Task> taskList) {
		if(taskList.isEmpty() ) {
			HBox emptyRow = new HBox();
			VBox rowContainer = new VBox(10);
			Label feedback = new Label(EMPTY_LIST_FEEDBACK);
			Label message = new Label(EMPTY_LIST_MESSAGE);
			emptyRow.setId(EMPTY_LIST_ID);
			rowContainer.setId(EMPTY_LIST_ID);
			rowContainer.getChildren().addAll(feedback, message);
			emptyRow.getChildren().add(rowContainer);
			tasks.add(emptyRow);
		}
	}

	private void setProperties(Label index, Label name, Label date, Label time, HBox task) {
		task.setPrefWidth(600);

		HBox.setHgrow(name, Priority.ALWAYS);
		index.setPrefWidth(50);
		index.setMaxWidth(Double.MAX_VALUE);
		index.setId(INDEX_ID);

		HBox.setHgrow(name, Priority.ALWAYS);
		name.setPrefWidth(380);
		name.setMaxWidth(Double.MAX_VALUE);
		name.setId(TASK_ID);

		HBox.setHgrow(date, Priority.ALWAYS);
		date.setPrefWidth(80);
		date.setId(DATE_ID);

		HBox.setHgrow(time, Priority.ALWAYS);
		time.setPrefWidth(100);
		time.setId(TIME_ID);
	}

	private void checkTasksForReminder() {
		if(!operatingTasksFromLogic.isEmpty()) {
			int todoTime = 0;
			int todoDay = 0;
			LocalDateTime localDateTime = new LocalDateTime();
			LocalDate localDate = localDateTime.toLocalDate();
			LocalTime localTime = localDateTime.toLocalTime().plusMinutes(REMINDER_TIME);
			for(Task taskObj: operatingTasksFromLogic) {
				if(taskObj.get_startDate().equals(localDate.toString(DATE_FORMAT)) ||
						(taskObj.get_startDate().equals(NULL) && !taskObj.get_startTime().equals(NULL))) {
					if(taskObj.get_startTime().equals(localTime.toString(TIME_FORMAT))) {
						todoTime++;
					}
					todoDay++;
				}
			}
			
			if(todoTime > 0) {
				notification.loadNotification(todoTime, TIME_FLAG);
			} else if(todoDay > 0 && todoTime == 0) {
				notification.loadNotification(todoDay, DAY_FLAG);
			}
		}
	}

	private void openCompleteTab() {
		//log.info("Tab Size? " + tabs.size());
		//System.out.println(tabs.size());
		if(tabs.size() == 1 && !completedTasksFromLogic.isEmpty()) {
			tabs.add(tabComplete);
			tabPane.getTabs().add(tabComplete);
		} 
	}

	public void openToDoList() {
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
		if(tasks.size() <= 1) {
			todoListContainer.setManaged(false);
			todoListContainer.setOpacity(0);
			closeToDoList();
		}
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

	public ListView<HBox> getList() {
		return todoList;
	}
}
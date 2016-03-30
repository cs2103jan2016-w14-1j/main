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
	private static final String TODAY_TAB = "Today: ";
	private static final String TOMORROW_TAB = "Tomorrow: ";
	private static final String FLOAT_TAB = "Tentative: ";
	private static final String OTHERS_TAB = "Upcoming: ";
	private static final String OVERDUE_TAB = "Overdue: ";

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
	private Tab tabIncomplete = new Tab(PENDING_TAB);
	private Tab tabToday;
	private Tab tabTomorrow;
	private Tab tabOthers;
	private Tab tabFloat;
	private Tab tabOverdue;

	private static ObservableList<HBox> pendingTasks = FXCollections.observableArrayList();
	private static ObservableList<HBox> completed = FXCollections.observableArrayList();
	private static ArrayList<Tab> tabs = new ArrayList<Tab>();

	private ArrayList<Task> operatingTasksFromLogic = new ArrayList<Task>();
	private ArrayList<Task> pendingTasksFromLogic = new ArrayList<Task>();
	private ArrayList<Task> completedTasksFromLogic = new ArrayList<Task>();

	private static ObservableList<HBox> todayTasks =
			FXCollections.observableArrayList();
	private static ObservableList<HBox> tomorrowTasks =
			FXCollections.observableArrayList();
	private static ObservableList<HBox> otherTasks =
			FXCollections.observableArrayList();
	private static ObservableList<HBox> floatingTasks =
			FXCollections.observableArrayList();
	private static ObservableList<HBox> overdueTasks =
			FXCollections.observableArrayList();

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
		previousCompletedSize = completedTasksFromLogic.size();

		pendingTasks.clear();
		completed.clear();

		//operatingTasksFromLogic = main.getPendingTasksFromLogic();
		//System.out.println(operatingTasksFromLogic.size() + "b");
		operatingTasksFromLogic = main.getOperatingTasksFromLogic();
		completedTasksFromLogic = main.getCompletedTasksFromLogic();
		//pendingTasksFromLogic = main.getPendingTasksFromLogic();

		//log.info("operatingTaskFromLogic empty? " + operatingTasksFromLogic.isEmpty());

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
		operateTabs();
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

			if(index == taskList.size() && taskList.size() != previousTasksSize) {
				scrollSelection = taskRow;
				previousTasksSize = operatingTasksFromLogic.size();
			} 

			taskRow.getChildren().addAll(taskIndex, taskName, taskTime, taskDate);
			taskFilter.sortTasksByClasses(taskObj, taskRow);
		}	
		getTasksForCategories();
		taskFilter.addSortedClasses(pendingTasks);
		insertFeedbackForEmptyList(taskList);
		todoList.setItems(pendingTasks);
	}

	private void formatCompletedTaskToListCell(ArrayList<Task> taskList) {
		int index = 0;
		for(Task taskObj: taskList) {
			index++;
			HBox taskRow = new HBox(10);
			Label taskIndex = new Label("  " + index + ".");
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
			taskName.setId(COMPLETED_TASK_ID);
			taskRow.getChildren().addAll(taskIndex, taskName, taskTime, taskDate);
			completed.add(taskRow);
		}
	}

	private void getTasksForCategories() {
		taskFilter.getTasksToday(todayTasks);
		taskFilter.getTasksTomorrow(tomorrowTasks);
		taskFilter.getTasksOthers(otherTasks);
		taskFilter.getTasksFloat(floatingTasks);
		taskFilter.getTasksOverdue(overdueTasks);

	}

	private void setTaskIntoViewIndex(int index) {
		if(pendingTasks.size() > 1) {
			todoList.scrollTo(index);
		}
	}

	private void setTaskIntoViewObject(HBox obj) {
		if(pendingTasks.contains(obj)) {
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
			pendingTasks.add(emptyRow);
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
				if(taskObj.get_date().equals(localDate.toString(DATE_FORMAT)) ||
						(taskObj.get_date().equals(NULL) && !taskObj.get_time().equals(NULL))) {
					if(taskObj.get_time().equals(localTime.toString(TIME_FORMAT))) {
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

	private void operateTabs() {
		//System.out.println(otherTasks.size() + "wasd123");
		tabs.clear();
		tabs.add(tabInbox);
		
		if(!tabs.contains(COMPLETED_TAB) && !completedTasksFromLogic.isEmpty()) {
			tabs.add(tabComplete);
		} else if(tabs.contains(tabOverdue) && completedTasksFromLogic.isEmpty()){
			tabs.remove(tabOverdue);
		}

		if(!tabs.contains(tabOverdue) && !overdueTasks.isEmpty()) {
			tabOverdue = new Tab(OVERDUE_TAB + overdueTasks.size());
			tabs.add(tabOverdue);
		} else if(tabs.contains(tabOverdue) && overdueTasks.isEmpty()) {
			tabs.remove(tabOverdue);
		}

		if(!tabs.contains(tabToday) && !todayTasks.isEmpty()) {
			tabToday = new Tab(TODAY_TAB + todayTasks.size());
			tabs.add(tabToday);
		} else if(tabs.contains(tabToday) && todayTasks.isEmpty()) {
			tabs.remove(tabToday);
		}

		if(!tabs.contains(tabTomorrow) && !tomorrowTasks.isEmpty()) {
			tabTomorrow = new Tab(TOMORROW_TAB + tomorrowTasks.size());
			tabs.add(tabTomorrow);
		} else if(tabs.contains(tabTomorrow) && tomorrowTasks.isEmpty()) {
			tabs.remove(tabTomorrow);
		}

		if(!tabs.contains(tabOthers) && !otherTasks.isEmpty()) {
			tabOthers = new Tab(OTHERS_TAB + otherTasks.size());
			//System.out.println(tabPane.getTabs().size() + "wasd");
			tabs.add(tabOthers);
		} else if(tabs.contains(tabOthers) && otherTasks.isEmpty()) {
			tabs.remove(tabOthers);
		}

		if(!tabs.contains(tabFloat) && !floatingTasks.isEmpty()) {
			tabFloat = new Tab(FLOAT_TAB + floatingTasks.size());
			tabs.add(tabFloat);
		} else if(tabs.contains(tabFloat) && floatingTasks.isEmpty()) {
			tabs.remove(tabFloat);
		}
		
		tabPane.getTabs().setAll(tabs);
	}

	public void openToDoList() {
		if(pendingTasks.isEmpty() && completed.isEmpty()) {	
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
		if(pendingTasks.size() <= 1) {
			todoListContainer.setManaged(false);
			todoListContainer.setOpacity(0);
			closeToDoList();
		}
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void displayPending() {
		todoList.setItems(pendingTasks);
	}

	public void displayCompleted() {
		todoList.setItems(completed);
	}

	public ObservableList<HBox> getTasks() {
		return pendingTasks;
	}

	public ObservableList<HBox> getCompleted() {
		return completed;
	}

	public ListView<HBox> getList() {
		return todoList;
	}
}
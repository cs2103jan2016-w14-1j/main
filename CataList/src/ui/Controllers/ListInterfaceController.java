package ui.Controllers;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
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

public class ListInterfaceController {
    
	private static final String INDEX_ID = "taskIndex";
	private static final String TASK_ID = "taskName";
	private static final String DATE_ID = "taskDate";
	private static final String TIME_ID = "taskTime";
	
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
    private Tab tabInbox;
    private Tab tabComplete = new Tab("Completed");
    
    
    private static ObservableList<HBox> tasks =
            FXCollections.observableArrayList();
    private static ObservableList<HBox> completed =
            FXCollections.observableArrayList();
    private static ArrayList<Tab> tabs =
            new ArrayList<Tab>();
    
    private ArrayList<Task> operatingTaskFromLogic;
    private Logger log = LogHandler.retriveLog();
    
    public void init(MainGUIController mainController) {
        main = mainController;
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        todoListContainer.setManaged(false);
        todoListContainer.setOpacity(0);
        loopTaskList();
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                System.out.println("Tab selected: " + newValue.getText());
                System.out.println(newValue.getContent());
                if (newValue.getContent() == null) {
                	  System.out.println(newValue.equals(tabInbox));
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
    	operatingTaskFromLogic = main.refreshList();
    	
    //	log.info("operatingTaskFromLogic empty? " + operatingTaskFromLogic.isEmpty());
    	
    	if(!operatingTaskFromLogic.isEmpty()) {
    		openToDoList();
    	}
    	
    	displayTaskList();
    	
    	if(tasks.isEmpty()) {
    		closeToDoList();
    	}
    }
    
    public void displayPending() {
        todoList.setItems(tasks);
    }
    
    public void displayCompleted() {
        todoList.setItems(completed);
    }
    
	public void closeToDoList() {
		if(todoListContainer.getScaleX() == 1) {
			animateToDoList(CLOSE_LIST);
		}
	}
	
	public ObservableList<HBox> getTasks() {
		return tasks;
	}
	
	public ObservableList<HBox> getCompleted() {
		return completed;
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
    	operatingTaskFromLogic = main.refreshList();
    	formatTaskToListCell(operatingTaskFromLogic);
        todoList.setItems(tasks);
        loadClassList();
    }

	private void formatTaskToListCell(ArrayList<Task> taskList) {
		for(Task taskObj: taskList) {
    		HBox taskRow = new HBox(10);
    		CheckBox isCompleted = new CheckBox();
    		Label taskIndex = new Label(taskObj.get_index() + ".");
        	Label taskName = new Label(taskObj.get_task());
        	Label taskTime = new Label(taskObj.get_time());
        	Label taskDate = new Label(taskObj.get_date());
        	
        	setProperties(taskIndex, taskName, taskTime, taskDate, taskRow);
        	
        	if(todoListContainer.getScaleX() == 0) {
        		animateToDoList(OPEN_LIST);
        	}
        	
        	tabs.add(tabInbox);
        	isCompleted.setOnAction(e -> handleCheckedBox(isCompleted, taskRow));
        	
        	taskRow.getChildren().addAll(isCompleted, taskIndex, taskName, taskTime, taskDate);
        	
        	tasks.add(taskRow);
    	}
	}
	
	private void loadClassList() {
		System.out.println(tabs.size());
		if(tabs.size() == 2 && !operatingTaskFromLogic.isEmpty()) {
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
    
    private void setProperties(Label ti, Label tn, Label td, Label tt, HBox hb) {
    	hb.setPrefWidth(600);
    	
    	HBox.setHgrow(tn, Priority.ALWAYS);
    	ti.setPrefWidth(30);
    	ti.setMaxWidth(Double.MAX_VALUE);
    	ti.setId(INDEX_ID);
    	
    	HBox.setHgrow(tn, Priority.ALWAYS);
    	tn.setPrefWidth(400);
    	tn.setMaxWidth(Double.MAX_VALUE);
    	tn.setId(TASK_ID);
    	
    	HBox.setHgrow(td, Priority.ALWAYS);
    	td.setPrefWidth(60);
    	td.setId(DATE_ID);
  
    	HBox.setHgrow(tt, Priority.ALWAYS);
    	tt.setPrefWidth(100);
    	tt.setId(TIME_ID);
    }
}
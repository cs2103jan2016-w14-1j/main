package ui.Controllers;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.jdom2.JDOMException;
import ui.Controllers.MainGUIController;
import logic.Task;
import shared.LogHandler;

public class ListInterfaceController {
    
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
    
    private static ObservableList<HBox> tasks =
            FXCollections.observableArrayList();
    private static ObservableList<HBox> completed =
            FXCollections.observableArrayList();
    
    private ArrayList<Task> operatingTaskFromLogic;
    private Logger log = LogHandler.retriveLog();
    
    public void init(MainGUIController mainController) {
        main = mainController;
        todoListContainer.setManaged(false);
        todoListContainer.setOpacity(0);
        loopTaskList();
    }
    
    public void loopTaskList() {
    	tasks.clear();
    	operatingTaskFromLogic = main.refreshList();
    	
    	log.info("operatingTaskFromLogic empty? " + operatingTaskFromLogic.isEmpty());
    	if(!operatingTaskFromLogic.isEmpty()) {
    		openToDoList();
    	}
    	
    	displayTaskList();
    	
    	log.info("tasks empty? " + tasks.isEmpty());
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
		if(todoList.getParent().getScaleX() == 1) {
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
			ScaleTransition st = new ScaleTransition(Duration.millis(500), todoList.getParent());
			st.setFromX(0);
			st.setToX(1);
			st.setCycleCount(1);
			st.play();
			todoList.getParent().setManaged(true);
		} else {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), todoList.getParent());
			st.setFromX(1);
			st.setToX(0);
			st.setCycleCount(1);
			st.play();
			todoList.getParent().setManaged(false);
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
        	Label taskName = new Label(taskObj.get_task());
        	Label taskTime = new Label(taskObj.get_time());
        	Label taskDate = new Label(taskObj.get_date());
        	
        	setProperties(taskName, taskTime, taskDate, taskRow);
        	
        	if(todoList.getParent().getScaleX() == 0) {
        		animateToDoList(OPEN_LIST);
        	}
       
        	isCompleted.setOnAction(e -> handleCheckedBox(isCompleted, taskRow));
        	
        	taskRow.getChildren().addAll(isCompleted, taskName, taskTime, taskDate);
        	
        	tasks.add(taskRow);
    	}
	}

	private void loadClassList() {
		/*
		if(main.isClassEmpty() && !_storage.getToBeDoneList().isEmpty()) {
			main.refreshClassList();
		} */
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
            main.loadCompleted();
            completed.add(hb);
            tasks.remove(hb);
        }
        
        if(!cb.isSelected()) {
            completed.remove(hb);
            tasks.add(hb);
            main.clearCompleted();
        }
    }
    
    private void setProperties(Label tn, Label td, Label tt, HBox hb) {
    	hb.setPrefWidth(600);
    	
    	HBox.setHgrow(tn, Priority.ALWAYS);
    	tn.setPrefWidth(380);
    	tn.setMaxWidth(Double.MAX_VALUE);
    	tn.setId(TASK_ID);
    	
    	HBox.setHgrow(td, Priority.ALWAYS);
    	td.setPrefWidth(110);
    	td.setId(DATE_ID);
  
    	HBox.setHgrow(tt, Priority.ALWAYS);
    	tt.setPrefWidth(110);
    	tt.setId(TIME_ID);
    }
}
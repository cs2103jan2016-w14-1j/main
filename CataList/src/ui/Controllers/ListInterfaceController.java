package Controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;

import Controllers.MainGUIController;
import storage.StorageReader;
import logic.Task;

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
    
    public static ObservableList<HBox> tasks =
            FXCollections.observableArrayList();
    public static ObservableList<HBox> completed =
            FXCollections.observableArrayList();
    
    private Storage _storage = new Storage();
    
    public void init(MainGUIController mainController) throws IOException, JDOMException {
        main = mainController;
        todoListContainer.setManaged(false);
        todoListContainer.setOpacity(0);
        loopTaskList();
    }
    
    public void loopTaskList() throws IOException, JDOMException {
    	tasks.clear();
    	_storage.loadTask();
    	
    	if(!_storage.getToBeDoneList().isEmpty()) {
    		initToDoList();
    	}
    	
    	displayTaskList();
    	closeList();
    }
    
    public void displayPending() {
        todoList.setItems(tasks);
    }
    
    public void displayCompleted() {
        todoList.setItems(completed);
    }
    
	private void closeList() {
		if(tasks.isEmpty()) {
			animateToDoList(CLOSE_LIST);
    	}
	}

	private void animateToDoList(Boolean command) {
		if(!command) {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), todoList.getParent());
			st.setFromX(1);
			st.setToX(0);
			st.setCycleCount(1);
			st.play();
		} else {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), todoList.getParent());
			st.setFromX(0);
			st.setToX(1);
			st.setCycleCount(1);
			st.play();
		}
	}
    
    private void displayTaskList() throws IOException, JDOMException {
    	ArrayList<Task> taskList = _storage.getToBeDoneList();
    	addTaskToListCell(taskList);
        todoList.setItems(tasks);
        loadClassList();
    }

	private void addTaskToListCell(ArrayList<Task> taskList) {
		for(Task taskObj: taskList) {
    		HBox taskRow = new HBox(10);
    		CheckBox isCompleted = new CheckBox();
        	Label taskName = new Label(taskObj.get_task());
        	Label taskTime = new Label(taskObj.get_time());
        	Label taskDate = new Label(taskObj.get_date());
        	
        	setProperties(taskName, taskTime, taskDate, taskRow);
        	animateToDoList(OPEN_LIST);
        	animateListCellFadeIn(taskRow);
        	
        	isCompleted.setOnAction(e -> handleCheckedBox(isCompleted, taskRow));
        	
        	taskRow.getChildren().addAll(isCompleted, taskName, taskTime, taskDate);
        	
        	tasks.add(taskRow);
    	}
	}

	private void loadClassList() {
		if(main.classListController.classes.isEmpty() && !_storage.getToBeDoneList().isEmpty()) {
			main.classListController.initEmptyClassList();
		}
	}

	private void initToDoList() {
		if(tasks.isEmpty() && completed.isEmpty()) {
			todoListContainer.setManaged(true);
			todoListContainer.setOpacity(1);
			
			if(main.welcomeMessage.isManaged()) {
				main.removeWelcomeMsg();
			
				animateToDoList(OPEN_LIST);
			}
		}
	}

	private void animateListCellFadeIn(HBox taskRow) {
		FadeTransition ft = new FadeTransition(Duration.millis(500), taskRow);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}
    
    private void handleCheckedBox(CheckBox cb, HBox hb) {
        if(cb.isSelected()) {
            main.classListController.initCompletedClassList();
            completed.add(hb);
            tasks.remove(hb);
        }
        
        if(!cb.isSelected()) {
            completed.remove(hb);
            tasks.add(hb);
            main.classListController.clearCompletedClassList();
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
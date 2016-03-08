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

import java.io.IOException;

import org.jdom2.JDOMException;

import Controllers.MainGUIController;
import storage.StorageReader;
import logic.Task;

public class ListInterfaceController {
    
	private static final String TASK_ID = "taskName";
	private static final String DATE_ID = "taskDate";
	private static final String TIME_ID = "taskTime";
	
    private MainGUIController main;
    
    @FXML 
    public ListView<HBox> todoList;
    
    public static ObservableList<HBox> tasks =
            FXCollections.observableArrayList();
    public static ObservableList<HBox> completed =
            FXCollections.observableArrayList();
    public int taskCount = 0;
    
    public void addTaskToList(String event, String time, String date) {
    	
    	initToDoList();
    	
    	HBox taskRow = new HBox(10);
    	CheckBox isCompleted = new CheckBox();
    	Label taskName = new Label(event);
    	Label taskTime = new Label(time);
    	Label taskDate = new Label(date);
    	setProperties(taskName, taskTime, taskDate, taskRow);
        
    	animateListCellFadeIn(taskRow);
    	
        taskRow.getChildren().addAll(isCompleted, taskName, taskTime, taskDate);
       
        isCompleted.setOnAction(e -> handleCheckedBox(isCompleted, taskRow));
        
        tasks.add(taskRow);
        todoList.setItems(tasks);
      
        taskCount++;
    }

	private void initToDoList() {
		if(tasks.isEmpty() && completed.isEmpty()) {
			todoList.getParent().setOpacity(1);
			
			main.removeWelcomeMsg();
			
			ScaleTransition st = new ScaleTransition(Duration.millis(800), todoList.getParent());
			st.setFromX(0);
			st.setToX(1);
			st.setCycleCount(1);
			st.play();
		}
	}

	private void animateListCellFadeIn(HBox taskRow) {
		FadeTransition ft = new FadeTransition(Duration.millis(500), taskRow);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}
    
    public void displayPending() {
        todoList.setItems(tasks);
    }
    
    public void displayCompleted() {
        todoList.setItems(completed);
    }
    
    private void handleCheckedBox(CheckBox cb, HBox hb) {
        if(cb.isSelected()) {
            main.classListController.initCompletedClassList();
            completed.add(hb);
            tasks.remove(hb);
            taskCount--;
        }
        
        if(!cb.isSelected()) {
            completed.remove(hb);
            tasks.add(hb);
            taskCount++;
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
    
    public void init(MainGUIController mainController) throws IOException, JDOMException {
        main = mainController;
        
        /****** temp init  *****/
        receiveFromStorage();
    }

    /**** temp method to initialize list *****/
	private void receiveFromStorage() throws IOException, JDOMException {
		Task[] listOfTasks = StorageReader.readFromStorage();
        if(listOfTasks.length == 0) {
        	todoList.getParent().setOpacity(0);
        } else {
        	for(Task t: listOfTasks) {
        		addTaskToList(t.get_task(), t.get_time(), t.get_date());
        	}
        	if(ClassController.classes.isEmpty()) {
				main.classListController.initEmptyClassList();
			}
        }
	}
}
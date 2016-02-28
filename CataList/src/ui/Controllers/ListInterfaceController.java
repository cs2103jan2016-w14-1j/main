package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import Controllers.MainGUIController;

public class ListInterfaceController {
    
	private static final String TASK_ID = "taskName";
	private static final String DATE_ID = "taskDate";
	private static final String TIME_ID = "taskTime";
	
    private MainGUIController main;
    
    @FXML 
    private ListView<HBox> todoList;
    
    public static ObservableList<HBox> tasks =
            FXCollections.observableArrayList();
    public static ObservableList<HBox> completed =
            FXCollections.observableArrayList();
    
    public void addTaskToList() {
    	HBox taskRow = new HBox(10);
    	CheckBox isCompleted = new CheckBox();
    	Label taskName = new Label(main.loadStringFromCommandLine());
    	Label taskTime = new Label(main.loadStringFromCommandLine());
    	Label taskDate = new Label(main.loadStringFromCommandLine());
    	setProperties(taskName, taskTime, taskDate, taskRow);
        
        taskRow.getChildren().addAll(isCompleted, taskName, taskTime, taskDate);
        
        isCompleted.setOnAction(e -> handleCheckedBox(isCompleted, taskRow));
        
        tasks.add(taskRow);
        todoList.setItems(tasks);
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
    	tn.setPrefWidth(350);
    	tn.setMaxWidth(Double.MAX_VALUE);
    	
    	HBox.setHgrow(td, Priority.ALWAYS);
    	td.setPrefWidth(100);
    	td.setId(DATE_ID);
  
    	HBox.setHgrow(tt, Priority.ALWAYS);
    	tt.setPrefWidth(100);
    	tt.setId(TIME_ID);
    }
    
    public void init(MainGUIController mainController) {
        main = mainController;
    }
}
package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import Controllers.MainGUIController;

public class ListInterfaceController {
    
    private MainGUIController main;
    
    @FXML private ListView todoList;
   
    public static ObservableList tasks =
            FXCollections.observableArrayList();
    public static ObservableList completed =
            FXCollections.observableArrayList();
    
    public void addTaskToList() {
        CheckBox task = new CheckBox(main.loadStringFromCommandLine());
        task.setOnAction(e -> handleCheckedBox(task));
        
        tasks.add(task);
        todoList.setItems(tasks);
    }
    
    public void displayPending() {
        todoList.setItems(tasks);
    }
    
    public void displayCompleted() {
        todoList.setItems(completed);
    }
    
    private void handleCheckedBox(CheckBox task) {
        if(task.isSelected()) {
            main.classListController.initCompletedClassList();
            completed.add(task);
            tasks.remove(task);
        }
        
        if(!task.isSelected()) {
            completed.remove(task);
            tasks.add(task);
            main.classListController.clearCompletedClassList();
        }
    }
    
    public void init(MainGUIController mainController) {
        main = mainController;
    }
}
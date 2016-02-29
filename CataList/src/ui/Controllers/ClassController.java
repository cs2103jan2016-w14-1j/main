package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import Controllers.MainGUIController;

public class ClassController {
    
    private MainGUIController main;
    
    @FXML private ListView<String> todoClass;
   
   public static ObservableList<String> classes =
            FXCollections.observableArrayList();
    
    public void initEmptyClassList() {
        if(classes.isEmpty()) {
            classes.add("today");
            todoClass.setItems(classes);
        }
    }
    
    public void initCompletedClassList() {
        
        if(ListInterfaceController.completed.isEmpty()) {
            classes.add("complete");
        }
    }
    
    public void clearCompletedClassList() {
        if(ListInterfaceController.completed.isEmpty()) {
            classes.remove("complete");
        }
    }
    
    public void init(MainGUIController mainController) {
        main = mainController;
    }
}
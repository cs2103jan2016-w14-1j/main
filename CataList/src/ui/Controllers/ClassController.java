package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import Controllers.MainGUIController;

/**
 *
 * @author ericewe
 */
public class ClassController {
    
    private MainGUIController main;
    
    @FXML private ListView todoClass;
   
   public static final ObservableList classes =
            FXCollections.observableArrayList();
    
    public void initEmptyClassList() {
        if(classes.isEmpty()) {
            classes.add("today");
            todoClass.setItems(classes);
        }
    }
    
    public void initCompletedClassList() {
        
        if(main.todoListController.completed.isEmpty()) {
            classes.add("complete");
        }
    }
    
    public void clearCompletedClassList() {
        if(main.todoListController.completed.isEmpty()) {
            classes.remove("complete");
        }
    }
    
    /**
     * Method to handle command line input
     * @command today, complete
     * @param event 
     */
    public void init(MainGUIController mainController) {
        main = mainController;
    }
}
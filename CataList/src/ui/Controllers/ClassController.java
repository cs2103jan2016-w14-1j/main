package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
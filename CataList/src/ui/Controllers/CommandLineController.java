/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
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
public class CommandLineController {
    
    private MainGUIController main;
    
    @FXML private Text feedback;
    @FXML public TextField userInput;
    String command = "";
    
    /**
     * Method to handle command line input
     * @command today, complete
     * @param event
     */
    @FXML private void handleSubmitButtonAction(ActionEvent event) {
        feedback.setText("Input entered");
        command = userInput.getText();
        userInput.clear();
        
        if(command.toLowerCase().equals("today") && !main.todoListController.tasks.isEmpty()) {
            main.todoListController.displayPending();
        } else if(command.toLowerCase().equals("complete") && !main.todoListController.completed.isEmpty()) {
            main.todoListController.displayCompleted();
        } else {
            main.todoListController.addTaskToList();
            
            if(main.classListController.classes.isEmpty()) {
                main.classListController.initEmptyClassList();
            }
        }
    }
        
        public void init(MainGUIController mainController) {
            main = mainController;
        }
}
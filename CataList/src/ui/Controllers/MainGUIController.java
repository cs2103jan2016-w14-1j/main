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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Controllers.CommandLineController;
import Controllers.ListInterfaceController;
import Controllers.TitleInterfaceController;
import Controllers.ClassController;

/**
 *
 * @author ericewe
 */
public class MainGUIController {
    @FXML CommandLineController commandLineController;
    @FXML ListInterfaceController todoListController;
    @FXML ClassController classListController;
    @FXML TitleInterfaceController titleController;
    
    /**
     * Method to handle command line input
     * @command today, complete
     * @param event 
     */
    public void initialize() {
        //System.out.println(this);
        //System.out.println();
       commandLineController.init(this);
       todoListController.init(this);
       classListController.init(this);
       titleController.init(this);
    }
    
    public String loadStringFromCommandLine() {
        return commandLineController.command;
    }
}
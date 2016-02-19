/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListInterface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.CheckBoxListCell;

/**
 *
 * @author ericewe
 */
public class ListInterfaceController {
    @FXML private Text feedback;
    @FXML private TextField userInput;
    @FXML private ListView todoList;
    @FXML private ListView todoClass;
    
    public static final ObservableList tasks = 
        FXCollections.observableArrayList();
    public static final ObservableList classes = 
        FXCollections.observableArrayList();
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        feedback.setText("Input entered");
        
        tasks.add(userInput.getText());
        todoList.setItems(tasks);
        
        
        if(classes.isEmpty()) {
            classes.add("Today");
            todoClass.setItems(classes);
        }

        userInput.clear();
    }

}
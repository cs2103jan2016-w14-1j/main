/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ListInterface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

/**
 *
 * @author ericewe
 */

public class ListInterfaceController implements Initializable {
    @FXML private Text feedback;
    @FXML private TextField userInput;
    @FXML private ListView todoList;
    @FXML private ListView<String> todoClass;
    
    public static final ObservableList tasks =
            FXCollections.observableArrayList();
    public static final ObservableList classes =
            FXCollections.observableArrayList();
    public static final ObservableList completed =
            FXCollections.observableArrayList();
    public static final ObservableList temp =
            FXCollections.observableArrayList();
    
    /**
     * Init method. No purpose for now but will handle mouse events.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    /**    todoClass.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                feedback.setText(newValue + " selected");
            }}); **/
    }
    
    /**
    @FXML protected void handleMouseClick (MouseEvent e) {
       feedback.setText(e.toString());
            tasks.removeAll();
            todoList.setItems(completed);
        
    }
    **/
    
    /**
     * Method to handle command line input
     * @command today, complete
     * @param event 
     */
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        feedback.setText("Input entered");
        
        String command = userInput.getText();
        
        /** temp parser **/
        if(command.toLowerCase().equals("today") && !tasks.isEmpty()) {
           // temp.addAll(completed);
           // completed.removeAll();
            todoList.setItems(tasks);
        } else if(command.toLowerCase().equals("complete") && !completed.isEmpty()) {
           // tasks.removeAll();
            todoList.setItems(completed);
        } else {
            CheckBox task = new CheckBox(command);
            task.setOnAction(e -> handleCheckedBox(task));
            
            tasks.add(task);
            todoList.setItems(tasks);
            
            if(classes.isEmpty()) {
                classes.add("Today");
                todoClass.setItems(classes);
            }
        }
        /*****************/
        
        userInput.clear();
    }
    
    
    /**
     * Method to handle CheckBox i.e. create and remove tick/untick boxes
     * @param task 
     */
    private void handleCheckedBox(CheckBox task) {
        if(task.isSelected()) {
            if(completed.isEmpty()) {
                classes.add("Complete");
            }
            completed.add(task);
            tasks.remove(task);
        }
        
        if(!task.isSelected()) {
            completed.remove(task);
            tasks.add(task);
            if(completed.isEmpty()) {
                classes.remove("Complete");
            }
        }
    }
}
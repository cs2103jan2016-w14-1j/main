package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Controllers.MainGUIController;

/**
 *
 * @author ericewe
 */
public class CommandLineController {
    
    private MainGUIController main;
    
    private static final String INITIALIZE = "";
    private static final String INIT_FEEDBACK = "Do you have anything on your mind?";
    
    
    @FXML 
    private Text feedback;
    @FXML 
    public TextField userInput;
    
    String command = INITIALIZE;
    
    /**
     * Method to handle command line input
     * @command today, complete
     * @param event
     */
    @FXML 
    private void handleSubmitButtonAction(ActionEvent event) {
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
            feedback.setText(INIT_FEEDBACK);
        }
}
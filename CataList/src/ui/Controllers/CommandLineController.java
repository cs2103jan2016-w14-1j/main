package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import java.io.IOException;
import Controllers.MainGUIController;
import Controllers.HelpCommands.CreateHelpWindow;

/**
 *
 * @author ericewe
 */
public class CommandLineController extends CreateHelpWindow {
    
    private MainGUIController main;
    
    private static final String INITIALIZE = "";
    private static final String INIT_FEEDBACK = "Do you have anything on your mind?";
    
    @FXML 
    private Text feedback;
    @FXML 
    public TextField userInput;
    
    String command = INITIALIZE;
    
    @FXML 
    private void handleSubmitButtonAction(ActionEvent event) throws IOException {
        feedback.setText("Input entered");
        command = userInput.getText();
        userInput.clear();
        
        if(command.toLowerCase().equals("today") && !ListInterfaceController.tasks.isEmpty()) {
            main.todoListController.displayPending();
        } else if(command.toLowerCase().equals("complete") && !ListInterfaceController.completed.isEmpty()) {
            main.todoListController.displayCompleted();
        } else if(command.toLowerCase().equals("help")) {
            createHelpWindow();
        } else {
            main.todoListController.addTaskToList();
            
            if(ClassController.classes.isEmpty()) {
                main.classListController.initEmptyClassList();
            }
        }
    }
        
    public void init(MainGUIController mainController) {
        main = mainController;
        feedback.setText(INIT_FEEDBACK);
    }
}
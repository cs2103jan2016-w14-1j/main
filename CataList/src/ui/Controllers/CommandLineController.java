package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import java.io.IOException;
import Controllers.MainGUIController;
import Controllers.ParseBackground;
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
        readUserInput();
        
        /**************** temp parser *******************/
        if(command.toLowerCase().equals("today") && !ListInterfaceController.tasks.isEmpty()) {
            main.todoListController.displayPending();
        } else if(command.toLowerCase().equals("complete") && !ListInterfaceController.completed.isEmpty()) {
            main.todoListController.displayCompleted();
        } else if(command.toLowerCase().equals("help")) {
           createHelpWindow();
    	} else {
    		if (getFirstWord(command).toLowerCase().equals("show")) {
            	main.mainAnchorPane.setId(ParseBackground.parseInput(removeFirstWord(command)));
    		} else {
    			main.todoListController.addTaskToList();

    			if(ClassController.classes.isEmpty()) {
    				main.classListController.initEmptyClassList();
    			}
    		}
        }
    }
    
    private void readUserInput() {
    	feedback.setText("Input entered");
        command = userInput.getText();
        userInput.clear();
    }
    
    private static String removeFirstWord(String userInput) {
		return userInput.replace(getFirstWord(userInput), "").trim();
	}

	
	private static String getFirstWord(String userInput) {
		return userInput.trim().split("\\s+")[0];
	}
        
    public void init(MainGUIController mainController) {
        main = mainController;
        feedback.setText(INIT_FEEDBACK);
    }
}
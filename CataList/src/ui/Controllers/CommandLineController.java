package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.JDOMException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import logic.LogicHandler;

public class CommandLineController {
    
    private MainGUIController main;
    
    private static final String INITIALIZE = "";
    private static final String INIT_FEEDBACK = "How can I help you?";
    private static final String MESSAGE_INVALID = "Invalid background";
    private final ArrayList<String> inputArray = new ArrayList<String>();
    
    @FXML 
    private Text feedback;
    @FXML 
    private TextField userInput;
    
    private String command = INITIALIZE;
    private int index = 0;
    
    public void init(MainGUIController mainController) {
        main = mainController;
        feedback.setText(INIT_FEEDBACK);
    }
    
    public void readUserInput() throws IOException, JDOMException {
        feedback.setText(uiToLogic());
        
        command = userInput.getText();
        userInput.clear();
        
        inputArray.add(command);
        index++;
    }
    
    @FXML 
    private void handleSubmitButtonAction(KeyEvent event) throws IOException, JDOMException {
    	
    	if (event.getCode() == KeyCode.ENTER) {
    		readUserInput();
    		
    		/**************** temp parser *******************/
    		if(command.toLowerCase().equals("inbox") && !main.isToDoListEmpty()) {
    			main.todoListController.displayPending();
    		} else if(command.toLowerCase().equals("complete") && !main.isCompletedEmpty()) {
    			main.todoListController.displayCompleted();
    		} else if(command.toLowerCase().equals("help")) {
    			main.supportFeatureController.loadHelpList();
    		} else if(command.toLowerCase().equals("calendar")) {
    			main.supportFeatureController.loadCalendar();
    		} else {
    			if (getFirstWord(command).toLowerCase().equals("show")) {
    				String id = ParseBackground.parseInput(removeFirstWord(command));
    				if(id.equals(MESSAGE_INVALID)) {
    					feedback.setText(MESSAGE_INVALID);
    				} else {
    					main.mainAnchorPane.setId(id);
    				}
    			} else {
    				
    				main.todoListController.loopTaskList();
    				main.classListController.loopClassList();

    			}
    		}
    	} else if (event.getCode() ==  KeyCode.UP) {
    			getPreviousCommand();
    		
    	} else if (event.getCode() ==  KeyCode.DOWN) {
    			getNextCommand();	
    	}
    }
    

	private void getNextCommand() {
		if(index >= 0 && index < inputArray.size()-1) {
			userInput.setText(inputArray.get(++index));
			if(index == inputArray.size()-1) {
				index++;
			}
		} else if(index == inputArray.size()-1) {
			userInput.clear();
		}
	}

	private void getPreviousCommand() {
		if(index > 0 && index <= inputArray.size()) {
			userInput.setText(inputArray.get(--index));
		}
	}

	private String uiToLogic() throws IOException, JDOMException {
		return LogicHandler.processCommand(userInput.getText());
	}
    
    private static String removeFirstWord(String userInput) {
		return userInput.replace(getFirstWord(userInput), INITIALIZE).trim();
	}

	
	private static String getFirstWord(String userInput) {
		return userInput.trim().split("\\s+")[0];
	}
}
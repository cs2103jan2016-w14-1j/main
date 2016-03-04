package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class CommandLineController extends CreateWindow {
    
    private MainGUIController main;
    
    private static final String INITIALIZE = "";
    private static final String INIT_FEEDBACK = "How can I help you?";
    
    private final ArrayList<String> inputArray = new ArrayList<String>();
    
    @FXML 
    private Text feedback;
    
    @FXML 
    public TextField userInput;
    
    String command = INITIALIZE;
    int index = 0;
    
    
    @FXML 
    private void handleSubmitButtonAction(KeyEvent event) throws IOException {
    	
    	if (event.getCode() == KeyCode.ENTER) {
    		readUserInput();
        
    		/**************** temp parser *******************/
    		if(command.toLowerCase().equals("inbox") && !ListInterfaceController.tasks.isEmpty()) {
    			main.todoListController.displayPending();
    		} else if(command.toLowerCase().equals("complete") && !ListInterfaceController.completed.isEmpty()) {
    			main.todoListController.displayCompleted();
    		} else if(command.toLowerCase().equals("help")) {
    			createWindow();
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
    	} else if (event.getCode() ==  KeyCode.UP) {
    		if (event.isAltDown()) {
    			getPreviousCommand();
    		}
    	} else if (event.getCode() ==  KeyCode.DOWN) {
    		if (event.isAltDown()) {
    			getNextCommand();
    		}
    	}
    }

	private void getNextCommand() {
		if(index >= 0 && index < inputArray.size()-1) {
			userInput.setText(inputArray.get(++index));
		} else if(index == inputArray.size()-1) {
			userInput.clear();
		}
	}

	private void getPreviousCommand() {
		if(index > 0 && index <= inputArray.size()) {
			userInput.setText(inputArray.get(--index));
		}
	}
    
    private void readUserInput() {
    	// integration code
        // feedback.setText(LogicMain.processCommand(userInput.getText()));
        
        command = userInput.getText();
        feedback.setText("\"" + command + "\" entered");
        userInput.clear();
        
        inputArray.add(command);
        index++;
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
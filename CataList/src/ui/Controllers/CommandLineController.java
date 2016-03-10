package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import Controllers.CalendarGenerator;
import logic.LogicHandler;

public class CommandLineController {
    
    private MainGUIController main;
    
    private static final String INITIALIZE = "";
    private static final String INIT_FEEDBACK = "How can I help you?";
    
    private static final String MESSAGE_INVALID = "Invalid background";
    private final String HELP_PAGE_PATH = "/View/HelpPage.fxml";
    
    private final ArrayList<String> inputArray = new ArrayList<String>();
    
    @FXML 
    private Text feedback;
    
    @FXML 
    private TextField userInput;
    
    String command = INITIALIZE;
    int index = 0;
    
    
    @FXML 
    private void handleSubmitButtonAction(KeyEvent event) throws IOException, JDOMException {
    	
    	if (event.getCode() == KeyCode.ENTER) {
    		readUserInput();
    		
    		/**************** temp parser *******************/
    		if(command.toLowerCase().equals("inbox") && !ListInterfaceController.tasks.isEmpty()) {
    			main.todoListController.displayPending();
    		} else if(command.toLowerCase().equals("complete") && !ListInterfaceController.completed.isEmpty()) {
    			main.todoListController.displayCompleted();
    		} else if(command.toLowerCase().equals("help")) {
    			openHelpPage();
    		} else if(command.toLowerCase().equals("calendar")) {
    			openCalendar();
    		} else if(command.toLowerCase().equals("quit calendar")) {
    			//closeCalendar();
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
/*
	private void closeCalendar() {
		main.classListController.todoClass.setOpacity(1);
		main.classListController.classContainer.getChildren().remove(CalendarGenerator.wb);
	}
*/
	private void openCalendar() {
		main.todoListController.closeList();
		main.classListController.closeList();
		CalendarGenerator.renderCalendar();
		main.showMainPane();
		main.welcomeMessage.getChildren().clear();
		main.welcomeMessage.getChildren().add(CalendarGenerator.wb);
	}

	private void openHelpPage() throws IOException {
		main.todoListController.closeList();
		main.classListController.closeList();
		main.showMainPane();
		main.welcomeMessage.getChildren().clear();
		main.welcomeMessage.getChildren().add(FXMLLoader.load(getClass().getResource(HELP_PAGE_PATH)));
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
    
    public void readUserInput() throws IOException, JDOMException {
        feedback.setText(uiToLogic());
        
        command = userInput.getText();
        userInput.clear();
        
        inputArray.add(command);
        index++;
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
        
    public void init(MainGUIController mainController) {
        main = mainController;
        feedback.setText(INIT_FEEDBACK);
    }
}
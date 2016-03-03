package Controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;

import Controllers.MainGUIController;
import Controllers.ParseBackground;

public class CommandLineController {
    
    private MainGUIController main;
    
    private static final String INITIALIZE = "";
    private static final String INIT_FEEDBACK = "How can I help you?";
   
    private final String HELP_PAGE_PATH = "/View/HelpPage.fxml";
    private final String HELP_PAGE_NAME = "Help";
    
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
	
	private void createHelpWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(HELP_PAGE_PATH));
        Parent helpPageRoot = (Parent) fxmlLoader.load();
        Stage helpPageStage = new Stage();
        helpPageStage.setTitle(HELP_PAGE_NAME);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), helpPageRoot);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        
        Scene helpPageScene = new Scene(helpPageRoot);
        helpPageStage.setScene(helpPageScene);
        helpPageStage.show();
        
        quitWindow(helpPageScene, helpPageStage);
    }

    private void quitWindow(Scene helpPageScene, Stage helpPageStage) {
        helpPageScene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
                helpPageStage.close();
            }
        });
    }
        
    public void init(MainGUIController mainController) {
        main = mainController;
        feedback.setText(INIT_FEEDBACK);
    }
}
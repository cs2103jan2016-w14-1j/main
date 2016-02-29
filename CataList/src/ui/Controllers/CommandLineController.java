package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import Controllers.MainGUIController;
import Controllers.ParseBackground;
import logic.LogicMain;

public class CommandLineController {
    
    private MainGUIController main;
    
    private static final String INITIALIZE = "";
    private static final String INIT_FEEDBACK = "Do you have anything on your mind?";
    private final String HELP_PAGE_PATH = "/View/HelpPage.fxml";
    private final String HELP_PAGE_NAME = "Help";
    
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
    	} else if (event.getCode() == KeyCode.DOWN) {
        	main.todoListController.todoList.scrollTo(index++);
          
    	} else if (event.getCode() == KeyCode.UP ) {
    		main.todoListController.todoList.scrollTo(index--);
    	}
    }
    
    private void readUserInput() {
    	feedback.setText("Input entered");
    	
    	// integration code
        // feedback.setText(LogicMain.processCommand(userInput.getText()));
        
        command = userInput.getText();
        userInput.clear();
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
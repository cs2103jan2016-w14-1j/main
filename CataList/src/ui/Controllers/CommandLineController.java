package Controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import org.jdom2.JDOMException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Controllers.CalendarGenerator;
import logic.LogicHandler;
import com.sun.javafx.scene.control.skin.DatePickerSkin;

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
    
    private String command = INITIALIZE;
    private int index = 0;
    private Node calendar;
    
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
    
	private void openCalendar() {
	//TODO: refactor here
		main.todoListController.closeList();
		main.classListController.closeList();
		VBox calendarContainer = new VBox(10);
		Label label = new Label("Schedule");
		label.setTextFill(Color.BLACK);
		Image image = new Image(getClass().getResourceAsStream("/Application/Stylesheets/Background/time-icon.png"));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(40);
		imageView.setFitWidth(40);
		imageView.setPreserveRatio(true);
		label.setGraphic(imageView);
		label.setFont(Font.font(20));
		calendarContainer.setId("calendarContainer");
		calendar = new DatePickerSkin(new DatePicker(LocalDate.now())).getPopupContent();
		calendarContainer.getChildren().addAll(label, calendar);
		main.showMainPane();
		main.welcomeMessage.getChildren().clear();
		main.welcomeMessage.getChildren().add(calendarContainer);
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
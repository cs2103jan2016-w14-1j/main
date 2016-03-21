package ui.Controllers;

import java.io.IOException;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import shared.LogHandler;

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
	private int screenSizeToggle = 1;
	private Logger log = LogHandler.retriveLog();

	public void init(MainGUIController mainController) {
		main = mainController;
		feedback.setText(INIT_FEEDBACK);
	}

	public void readUserInput() {
		feedback.setText(uiToLogic());

		command = userInput.getText();
		userInput.clear();

		inputArray.add(command);
		index++;
	}

	@FXML 
	private void handleSubmitButtonAction(KeyEvent event) throws IOException {

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
					//    				main.classListController.loopClassList();

				}
			}
		} else if (event.getCode() ==  KeyCode.UP) {
			getPreviousCommand();
		} else if (event.getCode() ==  KeyCode.DOWN) {
			getNextCommand();	
		} else if (event.getCode() == KeyCode.BACK_SLASH) {
			switch(screenSizeToggle) {
			case 1:
				((Stage) userInput.getScene().getWindow()).setFullScreen(true);
				screenSizeToggle = -1;
				break;
			case 0:
				((Stage) userInput.getScene().getWindow()).setFullScreen(false);
				((Stage) userInput.getScene().getWindow()).setWidth(1024);
				((Stage) userInput.getScene().getWindow()).setHeight(768);
				screenSizeToggle = 1;
				break;
			case -1:
				((Stage) userInput.getScene().getWindow()).setFullScreen(false);
				((Stage) userInput.getScene().getWindow()).setWidth(500);
				((Stage) userInput.getScene().getWindow()).setHeight(500);
				screenSizeToggle = 0;
				break;
			default:
				((Stage) userInput.getScene().getWindow()).setFullScreen(false);
				screenSizeToggle = 0;
				break;
			}
		}
	}


	private void getNextCommand() {
		if(index >= 0 && index < inputArray.size()-1) {
			log.info("command index " + index);
			userInput.setText(inputArray.get(++index));
			if(index == inputArray.size()-1) {
				index++;
			}
		} else if(index >= inputArray.size()-1) {
			userInput.clear();
			index = inputArray.size();
		}
	}

	private void getPreviousCommand() {
		if(index > 0 && index <= inputArray.size()) {
			log.info("command index " + index);
			userInput.setText(inputArray.get(--index));
		}
	}

	private String uiToLogic() {
		assert (this != null);
		return main.passInputToLogic(userInput.getText());
	}

	private static String removeFirstWord(String userInput) {
		return userInput.replace(getFirstWord(userInput), INITIALIZE).trim();
	}


	private static String getFirstWord(String userInput) {
		return userInput.trim().split("\\s+")[0];
	}
}
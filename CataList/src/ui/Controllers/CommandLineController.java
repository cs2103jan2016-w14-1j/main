package ui.Controllers;

import java.io.IOException;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import shared.LogHandler;

public class CommandLineController {

	private MainGUIController main;

	private static final String INITIALIZE = "";
	private static final String INIT_FEEDBACK = "How can I be of help?";
	private static final boolean TUTORIAL_ON = true;
	private static final boolean TUTORIAL_OFF = false;

	private static final int FULL_SCREEN = 1;
	private static final int DEFAULT_SCREEN = 0;
	private static final int SMALL_SCREEN = -1;

	private static final int START_INPUT_INDEX = 0;
	private static final int INBOX_TAB = 0;
	private static final int COMPLETE_TAB = 1;
	private boolean tutorialToggle = TUTORIAL_ON;

	@FXML 
	private Text feedback;
	@FXML 
	public TextField userInput;

	private String command = INITIALIZE;
	private int index = START_INPUT_INDEX;
	private int screenSizeToggle = FULL_SCREEN;
	private int tabToggle = COMPLETE_TAB;
	private ArrayList<String> inputArray = new ArrayList<String>();
	private Logger log = LogHandler.retriveLog();
	private ColorRenderer backgroundColor = new ColorRenderer();

	public void init(MainGUIController mainController) {
		main = mainController;
		feedback.setText(INIT_FEEDBACK);
	}

	private void readUserInput() {
		command = userInput.getText();
		loadFeedback();
		userInput.clear();
		inputArray.add(command);
		index++;
	}

	private void loadFeedback() {
		feedback.setText(uiToLogic(command));

		FadeTransition ft = new FadeTransition(Duration.millis(200), feedback);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}

	@FXML 
	private void handleSubmitButtonAction(KeyEvent event) throws IOException {

		if (event.getCode() == KeyCode.ENTER) {
			event.consume();
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
			} else if(command.toLowerCase().equals("tutorial")) {
				if(main.supportFeatureController.getMainPane().isManaged() == false) {
					main.supportFeatureController.renderTutorial();
				}
			} else {

				main.todoListController.loopTaskList();
				//   main.classListController.loopClassList();


			}
		} else if(event.getCode() == KeyCode.UP) {
			getPreviousCommand();
		} else if(event.getCode() == KeyCode.DOWN) {
			getNextCommand();	
		} else if(event.getCode() == KeyCode.RIGHT) {
			if(event.isAltDown()) {
				backgroundColor.toggleColorPlus(main.getBackgroundPane());
			} else if(event.isShiftDown() && main.isToDoListEmpty()) {
				updateTutorialToggle();
				main.supportFeatureController.showMainPane();
			} else {
				if(tutorialToggle == TUTORIAL_ON && main.isToDoListEmpty()) {
					main.todoListController.loopTaskList();
					main.supportFeatureController.renderTutorial();
					tutorialToggle = TUTORIAL_OFF;
				}
			}
		} else if(event.getCode() == KeyCode.LEFT) {
			if(event.isAltDown()) {
				backgroundColor.toggleColorMinus(main.getBackgroundPane());
			}
		} else if(event.getCode() == KeyCode.F12) {
			switch(screenSizeToggle) {
			case FULL_SCREEN:
				((Stage) userInput.getScene().getWindow()).setFullScreen(true);
				screenSizeToggle = -1;
				break;
			case DEFAULT_SCREEN:
				((Stage) userInput.getScene().getWindow()).setFullScreen(false);
				((Stage) userInput.getScene().getWindow()).setWidth(800);
				((Stage) userInput.getScene().getWindow()).setHeight(640);
				screenSizeToggle = 1;
				break;
			case SMALL_SCREEN:
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
		} else if (event.getCode() == KeyCode.F11) {
			main.getTabPane().getSelectionModel().select(tabToggle);
			if (tabToggle == INBOX_TAB) {
				tabToggle = COMPLETE_TAB;
			} else if (tabToggle == COMPLETE_TAB) {
				tabToggle = INBOX_TAB;
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

	public String uiToLogic(String input) {
		assert (this != null);
		return main.passInputToLogic(input);
	}

	public TextField getCommandLine() {
		return userInput;
	}

	public Text getFeedback() {
		return feedback;
	}

	public void updateTutorialToggle() {
		if(tutorialToggle) {
			tutorialToggle = TUTORIAL_OFF;
		} else {
			tutorialToggle = TUTORIAL_ON;
		}
	}

	public String getTutorialToggle() {
		if(tutorialToggle) {
			return "ON";
		} else {
			return "OFF";
		}
	}
}
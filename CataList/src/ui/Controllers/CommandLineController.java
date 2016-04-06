//@@author A01122204E
package ui.Controllers;

import java.io.File;
import java.io.IOException;

import javafx.stage.FileChooser;
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
	private static final boolean TUTORIAL_ON = true;
	private static final boolean TUTORIAL_OFF = false;
	
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_UNMARK = "unmark";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_REDO = "redo";
	private static final String COMMAND_MARK = "mark";
	private static final String COMMAND_HELP = "help";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_CLEAR = "clear";
	private static final String COMMAND_ADD = "add";
	
	private static final int START_INPUT_INDEX = 0;
	private static final int INBOX_TAB = 0;
	private static final int COMPLETE_TAB = 1;
	private boolean tutorialToggle = TUTORIAL_ON;

	@FXML private Text feedbackMain;
	@FXML private Text feedbackHelp;
	@FXML public TextField userInput;

	private String command = INITIALIZE;
	private int index = START_INPUT_INDEX;
	private int tabToggle = COMPLETE_TAB;

	private ArrayList<String> inputArray = new ArrayList<String>();
	private Logger log = LogHandler.retriveLog();
	private ColorRenderer backgroundColor = new ColorRenderer();

	public void init(MainGUIController mainController) {
		main = mainController;
		FeedbackGenerator.generateDefaultFeedback(feedbackMain);
		
		userInput.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.trim().isEmpty()) {
				FeedbackGenerator.clearFeedback(feedbackHelp);
			} else if(newValue.split(" ")[0].equalsIgnoreCase(COMMAND_ADD)) {
				FeedbackGenerator.generateAddFeedback(feedbackHelp);
			} else if (newValue.split(" ")[0].equalsIgnoreCase(COMMAND_DELETE)) {
				FeedbackGenerator.generateDeleteFeedback(feedbackHelp);
			} else if (newValue.split(" ")[0].equalsIgnoreCase(COMMAND_EDIT)) {
				FeedbackGenerator.generateEditFeedback(feedbackHelp);
			} else if (newValue.split(" ")[0].equalsIgnoreCase(COMMAND_MARK)) {
				FeedbackGenerator.generateMarkFeedback(feedbackHelp);
			} else if (newValue.split(" ")[0].equalsIgnoreCase(COMMAND_UNMARK)) {
				FeedbackGenerator.generateUnmarkFeedback(feedbackHelp);
			} else if (newValue.split(" ")[0].equalsIgnoreCase(COMMAND_SEARCH)) {
				FeedbackGenerator.generateSearchFeedback(feedbackHelp);
			} else if (newValue.split(" ")[0].equalsIgnoreCase(COMMAND_REDO)) {
				if(inputArray.size() >= 2 && inputArray.get(inputArray.size()-1).equalsIgnoreCase(COMMAND_UNDO)) {
					FeedbackGenerator.generateRedoFeedback(feedbackHelp, inputArray.get(inputArray.size()-2));
				} else {
					FeedbackGenerator.generateInvalidHelpFeedback(feedbackHelp);
				}
			} else if (newValue.split(" ")[0].equalsIgnoreCase(COMMAND_UNDO)) {
				if(inputArray.size() >= 1) {
					FeedbackGenerator.generateUndoFeedback(feedbackHelp, inputArray.get(inputArray.size()-1));
				} else {
					FeedbackGenerator.generateInvalidHelpFeedback(feedbackHelp);
				}
			} else if (newValue.split(" ")[0].equalsIgnoreCase(COMMAND_DISPLAY)) {
				FeedbackGenerator.generateHelpFeedback(feedbackHelp);
			} else {
				FeedbackGenerator.generateHelpFeedback(feedbackHelp);
				FeedbackGenerator.generateTipFeedback(feedbackMain);
			}
			
		});
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
				main.todoListController.displayComplete();
			} else if(command.toLowerCase().equals("help")) {
				main.supportFeatureController.loadHelpList();
			} else if(command.toLowerCase().equals("calendar")) {
				main.supportFeatureController.loadCalendar();
			} else if(command.toLowerCase().equals("tutorial")) {
				if(main.supportFeatureController.getMainPane().isManaged() == false) {
					main.supportFeatureController.renderTutorial();
				}
			} else if(command.toLowerCase().equals("save")) {
				FileChooser fileChooser = new FileChooser();
	            fileChooser.setTitle("Save Image");
	            File file = fileChooser.showSaveDialog(new Stage());
	            if (file != null) {
	            	command = file.getAbsolutePath();
	            	loadFeedback();
	            }
			} else {
				main.todoListController.loopTaskList();
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
		} else if (event.getCode() == KeyCode.F11) {
			main.getTabPane().getSelectionModel().select(tabToggle);
			if (tabToggle == INBOX_TAB) {
				tabToggle = COMPLETE_TAB;
			} else if (tabToggle == COMPLETE_TAB) {
				tabToggle = INBOX_TAB;
			}
		} else if(event.getCode() == KeyCode.SPACE) {
			AutoCompleteCommands.autoComplete(userInput, feedbackMain);
		}
	}
	
	public String uiToLogic(String input) {
		assert (this != null);
		return main.passInputToLogic(input);
	}

	private void readUserInput() {
		command = userInput.getText();
		loadFeedback();
		userInput.clear();
		inputArray.add(command);
		index++;
	}

	private void loadFeedback() {
		feedbackMain.setText(uiToLogic(command));

		FadeTransition ft = new FadeTransition(Duration.millis(200), feedbackMain);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}
	
	public void updateTutorialToggle() {
		if(tutorialToggle) {
			tutorialToggle = TUTORIAL_OFF;
		} else {
			tutorialToggle = TUTORIAL_ON;
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

	public TextField getCommandLine() {
		return userInput;
	}

	public Text getMainFeedback() {
		return feedbackMain;
	}
	
	public Text getHelpFeedback() {
		return feedbackHelp;
	}

	public String getTutorialToggle() {
		if(tutorialToggle) {
			return "ON";
		} else {
			return "OFF";
		}
	}
}
	//@@author A0112204E
package ui.Controllers;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AutoCompleteCommands {
	
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
	private static final String COMMAND_SAVE = "save";
	
	private static final String SPACE_REGEX = " ";
	
	private static final int START_INDEX = 0;
	private static final int END_INDEX = 1;
	private static final int END_INDEX_DISPLAY = 2;
	private static final int END_INDEX_SAVE = 2;
	private static final int END_INDEX_UNMARK = 3;
	
	private static ArrayList<String> autoCompleteList = new ArrayList<String>();
	
	private static void initAutoComplete() {
		autoCompleteList.add(COMMAND_ADD);
		autoCompleteList.add(COMMAND_CLEAR);
		autoCompleteList.add(COMMAND_DELETE);
		autoCompleteList.add(COMMAND_EDIT);
		autoCompleteList.add(COMMAND_HELP);
		autoCompleteList.add(COMMAND_MARK);
		autoCompleteList.add(COMMAND_UNDO);
		autoCompleteList.add(COMMAND_REDO);
		autoCompleteList.add(COMMAND_SEARCH);
	}
	
	public static void autoComplete(TextField userInput, Text feedback) {
		initAutoComplete();
		String autoCompleteCheck = processUserInput(userInput, feedback);
		checkCommandMatch(userInput, feedback, autoCompleteCheck);
	}

	private static String processUserInput(TextField userInput, Text feedback) {
		String autoCompleteCheck = userInput.getText();
		autoCompleteCheck = checkValidUserInput(userInput, feedback, autoCompleteCheck);
		return autoCompleteCheck;
	}

	private static void checkCommandMatch(TextField userInput, Text feedback, String autoCompleteCheck) {
		for(int i = 0; i < autoCompleteList.size(); i++) {
			if(autoCompleteCheck.equals(autoCompleteList.get(i).substring(START_INDEX, END_INDEX))) {
				int currentCaretPosition = userInput.getCaretPosition();
				String splitInput[] = userInput.getText().split(SPACE_REGEX);
				checkConflictingCommands(userInput, i, splitInput);
				
				StringBuilder sb = new StringBuilder();
				rebuildCorrectedUserInput(splitInput, sb);
				
				userInput.setText(sb.toString().trim());
				fixCaretFinalPosition(userInput, currentCaretPosition, splitInput);
				
				FeedbackGenerator.generateAutoCompleteFeedback(feedback, splitInput[START_INDEX]);
				break;
			}	
		}
	}

	private static void fixCaretFinalPosition(TextField userInput, int currentCaretPosition, String[] splitInput) {
		if(splitInput.length == 1) {
			userInput.end();
		} else {
			userInput.positionCaret(currentCaretPosition);
		}
	}

	private static String checkValidUserInput(TextField userInput, Text feedback, String autoCompleteCheck) {
		if(autoCompleteCheck.isEmpty()) {
			FeedbackGenerator.generateNullFeedback(feedback);
		} else {
			autoCompleteCheck = userInput.getText(START_INDEX, END_INDEX);
		}
		return autoCompleteCheck;
	}

	private static void rebuildCorrectedUserInput(String[] splitInput, StringBuilder sb) {
		for (String string : splitInput) {
			sb.append(string);
			if (sb.length() > 0) {
				sb.append(SPACE_REGEX);
			}
		}
	}

	private static void checkConflictingCommands(TextField userInput, int i, String[] splitInput) {
		if(isEstimatedCommandDisplay(userInput, splitInput)) {
			splitInput[START_INDEX] = COMMAND_DISPLAY;
		} else if(isEstimatedCommandSave(userInput, splitInput)) {
			splitInput[START_INDEX] = COMMAND_SAVE;
		} else if(isEstimatedCommandUnmark(userInput, splitInput)) {
			splitInput[START_INDEX] = COMMAND_UNMARK;
		} else {
			splitInput[START_INDEX] = autoCompleteList.get(i);
		}
	}

	private static boolean isEstimatedCommandUnmark(TextField userInput, String[] splitInput) {
		return splitInput[START_INDEX].length() > END_INDEX_UNMARK-1
				&& userInput.getText(START_INDEX, END_INDEX_UNMARK).
				equals(COMMAND_UNMARK.substring(START_INDEX, END_INDEX_UNMARK));
	}
	
	private static boolean isEstimatedCommandSave(TextField userInput, String[] splitInput) {
		return splitInput[START_INDEX].length() > END_INDEX_SAVE-1 
				&& userInput.getText(START_INDEX, END_INDEX_SAVE).
				equals(COMMAND_SAVE.substring(START_INDEX, END_INDEX_SAVE));
	}

	private static boolean isEstimatedCommandDisplay(TextField userInput, String[] splitInput) {
		return splitInput[START_INDEX].length() > END_INDEX_DISPLAY-1 
				&& userInput.getText(START_INDEX, END_INDEX_DISPLAY).
				equals(COMMAND_DISPLAY.substring(START_INDEX, END_INDEX_DISPLAY));
	}
}

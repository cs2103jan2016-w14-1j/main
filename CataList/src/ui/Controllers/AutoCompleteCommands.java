package ui.Controllers;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AutoCompleteCommands {
	
	private static final String SPACE_REGEX = " ";
	private static final int START_INDEX = 0;
	private static final int END_INDEX = 1;
	
	private static ArrayList<String> autoCompleteList = new ArrayList<String>();
	
	private static void initAutoComplete() {
		autoCompleteList.add("add");
		autoCompleteList.add("delete");
		autoCompleteList.add("edit");
		autoCompleteList.add("clear");
		autoCompleteList.add("mark");
		autoCompleteList.add("search");
		autoCompleteList.add("unmark");
		autoCompleteList.add("help");
	}
	
	public static void autoComplete(TextField userInput, Text feedback) {
		initAutoComplete();
		
		String autoCompleteCheck = userInput.getText();
		if(autoCompleteCheck.isEmpty()) {
			FeedbackGenerator.generateNullFeedback(feedback);
		} else {
			autoCompleteCheck = userInput.getText(START_INDEX, END_INDEX);
		}
		for(int i = 0; i < autoCompleteList.size(); i++) {
			if(autoCompleteCheck.equals(autoCompleteList.get(i).substring(START_INDEX, END_INDEX))) {
				String splitInput[] = userInput.getText().split(SPACE_REGEX);
				splitInput[START_INDEX] = autoCompleteList.get(i);
				StringBuilder sb = new StringBuilder();
				for (String string : splitInput) {
					if (sb.length() > 0) {
						sb.append(SPACE_REGEX);
					}
					sb.append(string);
				}
				userInput.setText(sb.toString());
				userInput.end();
				FeedbackGenerator.generateAutoCompleteFeedback(feedback, autoCompleteList.get(i));
			}	
		}
	}
}

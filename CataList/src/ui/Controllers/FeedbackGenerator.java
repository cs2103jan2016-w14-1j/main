package ui.Controllers;

import javafx.scene.text.Text;

public class FeedbackGenerator {
	
	private static final String FEEDBACK_COMMANDS = "add | delete | edit | mark | unmark | search | clear | help";
	private static final String FEEDBACK_ADD = "add [task] -d [date] -t [time]";
	private static final String FEEDBACK_DELETE = "delete [task number]";
	private static final String FEEDBACK_EDIT = "edit [task number] [new task] -d [new date] -t [new time]";
	private static final String FEEDBACK_MARK = "mark [task number]";
	private static final String FEEDBACK_UNMARK = "unmark [task number]";
	private static final String FEEDBACK_SEARCH = "search [keyword]";
	
	private static final String MAIN_FEEDBACK_DEFAULT = "How can I be of help?";
	private static final String MAIN_FEEDBACK_TIP = "You can hold SHIFT + SPACE to complete or correct your commands";
	
	private static final String FEEDBACK_AUTOCOMPLETE = "Is %1s what you meant?";
	
	private static final String CLEAR_REGEX = "";
	
	public static void generateDefaultFeedback(Text feedback) {
		feedback.setText(String.format(MAIN_FEEDBACK_DEFAULT));
	}
	
	public static void generateAutoCompleteFeedback(Text feedback, String command) {
		feedback.setText(String.format(FEEDBACK_AUTOCOMPLETE, command));
	}
	
	public static void generateAddFeedback(Text feedback) {
		feedback.setText(FEEDBACK_ADD);
	}
	
	public static void generateDeleteFeedback(Text feedback) {
		feedback.setText(FEEDBACK_DELETE);
	}
	
	public static void generateEditFeedback(Text feedback) {
		feedback.setText(FEEDBACK_EDIT);
	}
	
	public static void generateMarkFeedback(Text feedback) {
		feedback.setText(FEEDBACK_MARK);
	}
	
	public static void generateUnmarkFeedback(Text feedback) {
		feedback.setText(FEEDBACK_UNMARK);
	}
	
	public static void generateSearchFeedback(Text feedback) {
		feedback.setText(FEEDBACK_SEARCH);
	}
	
	public static void generateHelpFeedback(Text feedback) {
			feedback.setText(FEEDBACK_COMMANDS);
	}
	
	public static void generateTipFeedback(Text feedback) {
		feedback.setText(MAIN_FEEDBACK_TIP);
	}
	
	public static void clearFeedback(Text feedback) {
		feedback.setText(CLEAR_REGEX);
	}
}

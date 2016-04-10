//@@author A0112204E
package ui.Controllers;

import javafx.scene.text.Text;

public class FeedbackGenerator {

	private static final String FEEDBACK_COMMANDS = 
			"add | clear | delete | display| edit | help | mark | redo | saveas | search | unmark | undo";
	private static final String FEEDBACK_ADD = "add [task] [date] [time]";
	private static final String FEEDBACK_DELETE = "delete [task number]";
	private static final String FEEDBACK_EDIT = "edit [task number] [new task] [new date] [new time]";
	private static final String FEEDBACK_MARK = "mark [task number]";
	private static final String FEEDBACK_UNMARK = "unmark [task number]";
	private static final String FEEDBACK_SEARCH = "search [keyword]";
	private static final String FEEDBACK_REDO = "Your last input before undo was '%1s'";
	private static final String FEEDBACK_UNDO = "Your last input was '%1s'";
	private static final String FEEDBACK_INVALID = "The command you are about to input will not generate changes'";
	private static final String FEEDBACK_EVENT_CLASH = "There is a clash in your event tasks";
	
	private static final String MAIN_FEEDBACK_DEFAULT = "How can I be of help?";
	private static final String MAIN_FEEDBACK_INVALID = "Unrecognised command";
	private static final String MAIN_FEEDBACK_EMPTY = "Awaiting your orders";
	private static final String MAIN_FEEDBACK_TIP = "Press SPACE to complete or correct your commands";

	private static final String MAIN_FEEDBACK_AUTOCOMPLETE = "Is '%1s' what you meant?";
	

	private static final String NULL_REGEX = "";

	/**
	 * Generates are default feedback
	 * @param feedback This is the main feedback from commandListInterface
	 */
	public static void generateDefaultFeedback(Text feedback) {
		feedback.setText(String.format(MAIN_FEEDBACK_DEFAULT));
	}
	
	/**
	 * Generates feedback after auto complete
	 * @param feedback
	 * @param command
	 */
	public static void generateAutoCompleteFeedback(Text feedback, String command) {
		feedback.setText(String.format(MAIN_FEEDBACK_AUTOCOMPLETE, command));
	}

	/**
	 * Generates are add feedback
	 * @param feedback This is the secondary feedback from commandListInterface
	 */
	public static void generateAddFeedback(Text feedback) {
		feedback.setText(FEEDBACK_ADD);
	}
	
	/**
	 * Generates are delete feedback
	 * @param feedback This is the secondary feedback from commandListInterface
	 */
	public static void generateDeleteFeedback(Text feedback) {
		feedback.setText(FEEDBACK_DELETE);
	}
	
	/**
	 * Generates are edit feedback
	 * @param feedback This is the secondary feedback from commandListInterface
	 */
	public static void generateEditFeedback(Text feedback) {
		feedback.setText(FEEDBACK_EDIT);
	}
	
	/**
	 * Generates feedback for mark
	 * @param feedback This is the secondary feedback from commandListInterface
	 */
	public static void generateMarkFeedback(Text feedback) {
		feedback.setText(FEEDBACK_MARK);
	}

	/**
	 * Generates feedback for unmark
	 * @param feedback This is the secondary feedback from commandListInterface
	 */
	public static void generateUnmarkFeedback(Text feedback) {
		feedback.setText(FEEDBACK_UNMARK);
	}
	
	/**
	 * Generates feedback for search
	 * @param feedback This is the main feedback from commandListInterface
	 */
	public static void generateSearchFeedback(Text feedback) {
		feedback.setText(FEEDBACK_SEARCH);
	}
	
	/**
	 * Generates feedback for invalid
	 * @param feedback This is the secondary feedback from commandListInterface
	 */
	public static void generateInvalidHelpFeedback(Text feedback) {
		feedback.setText(FEEDBACK_INVALID);
	}
	
	/**
	 * Generates feedback for invalid 
	 * @param feedback This is the main feedback from commandListInterface
	 */
	public static void generateInvalidMainFeedback(Text feedback) {
		feedback.setText(MAIN_FEEDBACK_INVALID);
	}
	
	/**
	 * Generates feedback for empty input
	 * @param feedback This is the main feedback from commandListInterface
	 */
	public static void generateNullFeedback(Text feedback) {
		feedback.setText(MAIN_FEEDBACK_EMPTY);
	}
	
	/**
	 * Generates assisting feedback feedback
	 * @param feedback This is the main feedback from commandListInterface
	 */
	public static void generateHelpFeedback(Text feedback) {
		feedback.setText(FEEDBACK_COMMANDS);
	}
	
	/**
	 * Generates are tip for feedback
	 * @param feedback This is the main feedback from commandListInterface
	 */
	public static void generateTipFeedback(Text feedback) {
		feedback.setText(MAIN_FEEDBACK_TIP);
	}
	
	/**
	 * Generates feedback for redo
	 * @param feedback
	 * @param lastInput This is the input prior to undo
	 */
	public static void generateRedoFeedback(Text feedback, String lastInput) {
		feedback.setText(String.format(FEEDBACK_REDO, lastInput));
	}
	
	/**
	 * Generates feedback for undo
	 * @param feedback This is the secondary feedback from commandListInterface
	 * @param lastInput This is the most recent input
	 */
	public static void generateUndoFeedback(Text feedback, String lastInput) {
		feedback.setText(String.format(FEEDBACK_UNDO, lastInput));
	}
	
	/**
	 * Generates feedback for event clashes
	 * @param feedback This is the secondary feedback from commandListInterface
	 */
	public static void generateEventClashFeedback(Text feedback) {
		feedback.setText(FEEDBACK_EVENT_CLASH);
	}

	/**
	 * Clears the text from feedback
	 * @param feedback This is the main feedback from commandListInterface
	 */
	public static void clearFeedback(Text feedback) {
		feedback.setText(NULL_REGEX);
	}
}

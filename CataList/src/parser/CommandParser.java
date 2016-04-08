//@@author a0124946

package parser;

import logic.Commands;

public class CommandParser {
	
	private static final int COMMAND_INDEX = 0;
	private static final String SYMBOL_WHITESPACE = " ";
	
	public static String parseCommand(String userInput){
		String firstWord = extractFirstWord(userInput);
		String identifiedCommand = analyzeWord(firstWord);
		return identifiedCommand;
	}
	
	private static String extractFirstWord(String userInput){
		String[] resultingArray = userInput.split(SYMBOL_WHITESPACE);
		String firstWord = resultingArray[COMMAND_INDEX];
		return firstWord;
	}
	 
	private static boolean compareKeywords(String stringToTest, String[] arrayOfKeywords){
		boolean isKeyword = false;
		for(String supportedKeywords : arrayOfKeywords){
			if(stringToTest.equalsIgnoreCase(supportedKeywords)){
				isKeyword = true;
				break;
			}
		}
		return isKeyword;
	}
	
	private static String analyzeWord(String stringToTest){
		if(compareAddKeywords(stringToTest)){
			return Commands.ADD_COMMAND;
		} else if (compareDeleteKeywords(stringToTest)){
			return Commands.DELETE_COMMAND;
		} else if (compareClearKeywords(stringToTest)){
			return Commands.CLEAR_COMMAND;
		} else if (compareDisplayKeywords(stringToTest)){
			return Commands.DISPLAY_COMMAND;
		} else if (compareEditKeywords(stringToTest)){
			return Commands.EDIT_COMMAND;
		} else if (compareRedoKeywords(stringToTest)){
			return Commands.REDO_COMMAND;
		} else if (compareUndoKeywords(stringToTest)){
			return Commands.UNDO_COMMAND;
		} else if (compareSearchKeywords(stringToTest)){
			return Commands.SEARCH_COMMAND;
		} else if (compareMarkCompleteKeywords(stringToTest)){
			return Commands.MARK_COMMAND;
		} else if (compareMarkIncompleteKeywords(stringToTest)){
			return Commands.MARK_INCOMPLETE_COMMAND;
		} else if (compareExitKeywords(stringToTest)){
			return Commands.EXIT_COMMAND;
		} else if (compareHelpKeywords(stringToTest)){
			return Commands.HELP_COMMAND;
		} else if (compareCalendarKeywords(stringToTest)){
			return Commands.CALENDAR_COMMAND;
		} else if (compareTutorialKeywords(stringToTest)){
			return Commands.TUTORIAL_COMMAND;
		} else if (compareSaveKeywords(stringToTest)) {
			return Commands.SAVE_COMMAND;
		} else if (compareSaveToKeywords(stringToTest)) {
			return Commands.SAVETO_COMMAND;
		} else {
			return Commands.INVALID_COMMAND;
		}
	}
	
	private static boolean compareAddKeywords(String stringToTest){
		boolean isAddKeyword = false;
		isAddKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_ADD);
		return isAddKeyword;
	}
	
	private static boolean compareDeleteKeywords(String stringToTest){
		boolean isDeleteKeyword = false;
		isDeleteKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_DELETE);
		return isDeleteKeyword;
	}
	
	private static boolean compareClearKeywords(String stringToTest){
		boolean isClearKeyword = false;
		isClearKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_CLEAR);
		return isClearKeyword;
	}
	
	private static boolean compareDisplayKeywords(String stringToTest){
		boolean isDisplayKeyword = false;
		isDisplayKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_DISPLAY);
		return isDisplayKeyword;
	}
	
	private static boolean compareEditKeywords(String stringToTest){
		boolean isEditKeyword = false;
		isEditKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_EDIT);
		return isEditKeyword;
	}
	
	private static boolean compareRedoKeywords(String stringToTest){
		boolean isRedoKeyword = false;
		isRedoKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_REDO);
		return isRedoKeyword;
	}
	
	private static boolean compareUndoKeywords(String stringToTest){
		boolean isUndoKeyword = false;
		isUndoKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_UNDO);
		return isUndoKeyword;
	}
	
	private static boolean compareSearchKeywords(String stringToTest){
		boolean isSearchKeyword = false;
		isSearchKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_SEARCH);
		return isSearchKeyword;
	}
	
	private static boolean compareMarkCompleteKeywords(String stringToTest){
		boolean isMarkCompleteKeyword = false;
		isMarkCompleteKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_MARK_COMPLETE);
		return isMarkCompleteKeyword;
	}
	
	private static boolean compareMarkIncompleteKeywords(String stringToTest){
		boolean isMarkIncompleteKeyword = false;
		isMarkIncompleteKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_MARK_INCOMPLETE);
		return isMarkIncompleteKeyword;
	}
	
	private static boolean compareExitKeywords(String stringToTest){
		boolean isExitKeyword = false;
		isExitKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_EXIT);
		return isExitKeyword;
	}
	
	private static boolean compareHelpKeywords(String stringToTest){
		boolean isHelpKeyword = false;
		isHelpKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_HELP);
		return isHelpKeyword;
	}
	
	private static boolean compareCalendarKeywords(String stringToTest){
		boolean isCalKeyword = false;
		isCalKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_CALENDAR);
		return isCalKeyword;
	}
	
	private static boolean compareTutorialKeywords(String stringToTest){
		boolean isTutKeyword = false;
		isTutKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_TUTORIAL);
		return isTutKeyword;
	}
	
	private static boolean compareSaveKeywords(String stringToTest){
		boolean isTutKeyword = false;
		isTutKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_SAVE);
		return isTutKeyword;
	}
	
	private static boolean compareSaveToKeywords(String stringToTest){
		boolean isTutKeyword = false;
		isTutKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_SAVETO);
		return isTutKeyword;
	}
}

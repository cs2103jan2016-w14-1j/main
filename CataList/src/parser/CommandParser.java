package parser;

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
			return "add";
		} else if (compareDeleteKeywords(stringToTest)){
			return "delete";
		} else if (compareClearKeywords(stringToTest)){
			return "clear";
		} else if (compareDisplayKeywords(stringToTest)){
			return "display";
		} else if (compareEditKeywords(stringToTest)){
			return "edit";
		} else if (compareRedoKeywords(stringToTest)){
			return "redo";
		} else if (compareUndoKeywords(stringToTest)){
			return "undo";
		} else if (compareSearchKeywords(stringToTest)){
			return "search";
		} else if (compareMarkCompleteKeywords(stringToTest)){
			return "markcomplete";
		} else if (compareMarkIncompleteKeywords(stringToTest)){
			return "markincomplete";
		} else if (compareExitKeywords(stringToTest)){
			return "exit";
		} else if (compareHelpKeywords(stringToTest)){
			return "help";
		} else if (compareCalendarKeywords(stringToTest)){
			return "calendar";
		} else if (compareTutorialKeywords(stringToTest)){
			return "tutorial";
		} else {
			return "invalid";
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
		isExitKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_ADD);
		return isExitKeyword;
	}
	
	private static boolean compareHelpKeywords(String stringToTest){
		boolean isHelpKeyword = false;
		isHelpKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_ADD);
		return isHelpKeyword;
	}
	
	private static boolean compareCalendarKeywords(String stringToTest){
		boolean isCalKeyword = false;
		isCalKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_ADD);
		return isCalKeyword;
	}
	
	private static boolean compareTutorialKeywords(String stringToTest){
		boolean isTutKeyword = false;
		isTutKeyword = compareKeywords(stringToTest, KeywordConstraints.KW_TASK_ADD);
		return isTutKeyword;
	}
}

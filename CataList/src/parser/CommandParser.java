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
	
	
	
	
}

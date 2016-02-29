package parser;

import shared.COMMAND_TYPE;

public class Parser {
	
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_CLEAR= "clear";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_SORT = "dsiplay";
	
	public static COMMAND_TYPE getCommand(String userInput) {
		String commandType = getFirstWord(userInput).toLowerCase();
	
		switch (commandType) {
			case COMMAND_ADD:
				return COMMAND_TYPE.ADD_TASK;
			case COMMAND_DELETE:
				return COMMAND_TYPE.DELETE_TASK;
			case COMMAND_EDIT:
				return COMMAND_TYPE.EDIT_TASK;
			case COMMAND_UNDO:
				return COMMAND_TYPE.UNDO_ACTION;
			case COMMAND_CLEAR:
				return COMMAND_TYPE.CLEAR_CLASS;
			case COMMAND_DISPLAY:
				return COMMAND_TYPE.DISPLAY_CLASS;
			case COMMAND_SEARCH:
				return COMMAND_TYPE.SEARCH_KEYWORD;
			case COMMAND_SORT:
				return COMMAND_TYPE.SORT_TASK;
			default:
				return COMMAND_TYPE.INVALID;
		}
	}
	
	 private static String removeFirstWord(String userInput) {
		 return userInput.replace(getFirstWord(userInput), "").trim();
	 }

	 private static String getFirstWord(String userInput) {
		 return userInput.trim().split("\\s+")[0];
	 }
}

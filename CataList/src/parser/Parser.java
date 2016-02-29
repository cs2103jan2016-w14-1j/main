package parser;

public class Parser {
	
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_CLEAR= "clear";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_SORT = "dsiplay";
	
	private static final String WARNING_INVALID = "Are you drunk bro?";
	
	public static String getCommand(String userInput) {
		String commandType = getFirstWord(userInput);
	
		switch (commandType) {
			case COMMAND_ADD:
				return WARNING_INVALID;
			case COMMAND_DELETE:
				return WARNING_INVALID;
			case COMMAND_EDIT:
				return WARNING_INVALID;
			case COMMAND_UNDO:
				return WARNING_INVALID;
			case COMMAND_CLEAR:
				return WARNING_INVALID;
			case COMMAND_DISPLAY:
				return WARNING_INVALID;
			case COMMAND_SEARCH:
				return WARNING_INVALID;
			case COMMAND_SORT:
				return WARNING_INVALID;
			default:
				return WARNING_INVALID;
		}
	}
	
	 private static String removeFirstWord(String userInput) {
		 return userInput.replace(getFirstWord(userInput), "").trim();
	 }

	 private static String getFirstWord(String userInput) {
		 return userInput.trim().split("\\s+")[0];
	 }
}

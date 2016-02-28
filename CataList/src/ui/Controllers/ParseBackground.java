package Controllers;

public class ParseBackground {
	
	private static final String COMMAND_DEFAULT = "default";
	private static final String COMMAND_WINTER = "winter";
	private static final String COMMAND_POOL = "pool";
	
	private static final String DEFAULT_ID = "mainPaneDefault";
	private static final String WINTER_ID = "mainPaneWinter";
	private static final String POOL_ID = "mainPanePool";
	
	private static final String MESSAGE_INVALID = "Invalid background";
	
	public static String parseInput (String userInput) {
		switch (userInput.toLowerCase()) {
			case COMMAND_DEFAULT:
				return DEFAULT_ID;
			case COMMAND_WINTER:
				return WINTER_ID;
			case COMMAND_POOL:
				return POOL_ID;
			default:
				return MESSAGE_INVALID;
		}
	}
	
}

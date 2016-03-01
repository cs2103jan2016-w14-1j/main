package Controllers;

public class ParseBackground {
	
	private static final String COMMAND_DEFAULT = "default";
	private static final String COMMAND_SEA = "sea";
	private static final String COMMAND_FALLS = "waterfalls";
	
	private static final String DEFAULT_ID = "mainPaneDefault";
	private static final String SEA_ID = "mainPaneSea";
	private static final String FALLS_ID = "mainPaneFalls";
	
	private static final String MESSAGE_INVALID = "Invalid background";
	
	public static String parseInput (String userInput) {
		switch (userInput.toLowerCase()) {
			case COMMAND_DEFAULT:
				return DEFAULT_ID;
			case COMMAND_SEA:
				return SEA_ID;
			case COMMAND_FALLS:
				return FALLS_ID;
			default:
				return MESSAGE_INVALID;
		}
	}
	
}

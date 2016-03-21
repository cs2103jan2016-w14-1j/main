package ui.Controllers;

public class ParseBackground {
	
	private static final String COMMAND_DEFAULT = "default";
	private static final String COMMAND_SEA = "sea";
	private static final String COMMAND_SUNSET = "sunset";
	private static final String COMMAND_SUNRISE = "sunrise";
	private static final String COMMAND_MOUNTAIN = "mountain";
	private static final String COMMAND_WINTER = "winter";
	private static final String COMMAND_LAKE = "lake";
	private static final String COMMAND_NIGHT = "night";
	private static final String COMMAND_HILLS = "hills";
	
	private static final String DEFAULT_ID = "mainPaneDefault";
	private static final String SEA_ID = "mainPaneSea";
	private static final String SUNSET_ID = "mainPaneSunset";
	private static final String SUNRISE_ID = "mainPaneSunrise";
	private static final String MOUNTAIN_ID = "mainPaneMountain";
	private static final String WINTER_ID = "mainPaneWinter";
	private static final String LAKE_ID = "mainPaneLake";
	private static final String NIGHT_ID = "mainPaneNight";
	private static final String HILLS_ID = "mainPaneHills";
	
	private static final String MESSAGE_INVALID = "Invalid background";
	
	public static String parseInput (String userInput) {
		switch (userInput.toLowerCase()) {
			case COMMAND_DEFAULT:
				return DEFAULT_ID;
			case COMMAND_SEA:
				return SEA_ID;
			case COMMAND_SUNSET:
				return SUNSET_ID;
			case COMMAND_SUNRISE:
				return SUNRISE_ID;
			case COMMAND_MOUNTAIN:
				return MOUNTAIN_ID;
			case COMMAND_WINTER:
				return WINTER_ID;
			case COMMAND_LAKE:
				return LAKE_ID;
			case COMMAND_NIGHT:
				return NIGHT_ID;
			case COMMAND_HILLS:
				return HILLS_ID;
			default:
				return MESSAGE_INVALID;
		}
	}
	
}

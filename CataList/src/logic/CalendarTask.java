//@@author a0124946
package logic;

public class CalendarTask extends Task {
	private static final String MESSAGE_CALENDAR_DEFAULT = "Displaying Calendar";
	private static final String COMMAND_TYPE = "calendar";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public CalendarTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_CALENDAR_DEFAULT
				,MESSAGE_CALENDAR_DEFAULT);
	}
}
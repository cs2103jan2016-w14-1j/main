package logic;

public class DisplayTask extends Task {

	private static final String MESSAGE_DISPLAY_SUCCESS = "Displaying Event";
	private static final String MESSAGE_DISPLAY_FAILURE = "Unable to display event.";
	private static final String COMMAND_TYPE = "display";
	private static final String SYMBOL_SPACE = " ";
	
	//Constructor
	public DisplayTask (boolean isChange, String userInput, String cmd, String msg){
		super(isChange, userInput, COMMAND_TYPE, MESSAGE_DISPLAY_SUCCESS);
	}
	
	public DisplayTask(boolean isChange, String userInput, String cmd, String msg, String date){
		super(isChange, userInput, COMMAND_TYPE, MESSAGE_DISPLAY_SUCCESS);
		this.set_date(date);
	}
	public DisplayTask(boolean isChange, String userInput, String cmd, String msg, String time, String date){
		super(isChange,userInput, COMMAND_TYPE, MESSAGE_DISPLAY_SUCCESS, time, date);
	}
	
	
}

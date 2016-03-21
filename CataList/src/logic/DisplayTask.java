package logic;

public class DisplayTask extends Task {

	private static final String MESSAGE_DISPLAY_SUCCESS = "Displaying Event";
	private static final String MESSAGE_DISPLAY_FAILURE = "Unable to display.";
	private static final String MESSAGE_DISPLAY_EMPTY = "Nothing to display.";
	private static final String COMMAND_TYPE = "display";
	
	private static final boolean IS_MUTATOR = false;

	//Constructor
	public DisplayTask (String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_DISPLAY_SUCCESS);
	}
	
	public DisplayTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_DISPLAY_SUCCESS, date);
	}
	public DisplayTask(String userInput, String time, String date){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_DISPLAY_SUCCESS, time, date);
	}
	
	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_DISPLAY_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_DISPLAY_EMPTY);
	}
	
}

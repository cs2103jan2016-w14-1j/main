package logic;

public class EditTask extends Task{
	
	private static final String MESSAGE_EDIT_SUCCESS = "The event has been edited.";
	private static final String MESSAGE_EDIT_FAILURE = "Unable to edit event.";
	private static final String MESSAGE_EDIT_EMPTY = "No event to be editted.";
	private static final String COMMAND_TYPE = "edit";
	
	private static final boolean IS_MUTATOR = true;
	//Constructor
	public EditTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_EDIT_SUCCESS);
	}
	
	public EditTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_EDIT_SUCCESS, date);
	}

	public EditTask(String userInput, String time, String date){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_EDIT_SUCCESS, time, date);
	}
	
	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_EDIT_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_EDIT_EMPTY);
	}
}

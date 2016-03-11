package logic;

public class MarkIncomplete extends Task{

	private static final String MESSAGE_MARK_UNCOMPLETE_SUCCESS = "Event marked as incomplete.";
	private static final String MESSAGE_MARK_UNCOMPLETE_FAILURE = "Unable to mark event as incomplete.";
	private static final String MESSAGE_MARK_UNCOMPLETE_EMPTY = "Nothing to mark as incomplete.";
	private static final String COMMAND_TYPE = "markuncomplete";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public MarkIncomplete(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_MARK_UNCOMPLETE_SUCCESS);
	}
	
	public MarkIncomplete(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_MARK_UNCOMPLETE_SUCCESS, date);
	}
	public MarkIncomplete(String userInput, String date, String time){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_MARK_UNCOMPLETE_SUCCESS, time, date);
	}

	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_MARK_UNCOMPLETE_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_MARK_UNCOMPLETE_EMPTY);
	}


}

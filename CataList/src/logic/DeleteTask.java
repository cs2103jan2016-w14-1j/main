package logic;

public class DeleteTask extends Task{
	
	private static final String MESSAGE_DELETE_SUCCESS = "The event has been deleted.";
	private static final String MESSAGE_DELETE_FAILURE = "Unable to delete event.";
	private static final String COMMAND_TYPE = "delete";
	private static final String SYMBOL_SPACE = " ";
	
	//Constructor
	public DeleteTask(boolean isChange, String userInput, String cmd, String msg){
		super(isChange, userInput, COMMAND_TYPE, MESSAGE_DELETE_SUCCESS);
	}
	
	public DeleteTask(boolean isChange, String userInput, String cmd, String msg, String date){
		super(isChange, userInput, COMMAND_TYPE, MESSAGE_DELETE_SUCCESS);
		this.set_date(date);
	}
	public DeleteTask(boolean isChange, String userInput, String cmd, String msg, String time, String date){
		super(isChange,userInput, COMMAND_TYPE, MESSAGE_DELETE_SUCCESS, time, date);
	}

}

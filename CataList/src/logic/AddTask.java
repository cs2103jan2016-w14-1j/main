package logic;

import java.util.ArrayList;

public class AddTask extends Task {
	
	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	private static final String MESSAGE_ADD_FAILURE = "Unable to add message.";
	private static final String COMMAND_TYPE = "add";
	private static final String SYMBOL_SPACE = " ";
	
	//Constructor
	public AddTask(boolean isChange, String userInput, String cmd, String msg){
		super(isChange, userInput, cmd, msg);
	}
	
	public AddTask(boolean isChange, String userInput, String cmd, String msg, String date){
		super(isChange, userInput, cmd, msg);
		this.set_date(date);
	}
	public AddTask(boolean isChange, String userInput, String cmd, String msg, String time, String date){
		super(isChange,userInput, cmd, msg, time, date);
	}

}

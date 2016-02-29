// fucking
// clarify this shit on weds
package logic;

import java.util.ArrayList;

public class AddTask extends Task {
	
	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	private static final String MESSAGE_ADD_FAILURE = "Unable to add event.";
	private static final String COMMAND_TYPE = "add";
	private static final String SYMBOL_SPACE = " ";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public AddTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_ADD_SUCCESS);
	}
	
	public AddTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_ADD_SUCCESS);
		this.set_date(date);
	}
	public AddTask(String userInput, String date, String time){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_ADD_SUCCESS, time, date);
	}

}

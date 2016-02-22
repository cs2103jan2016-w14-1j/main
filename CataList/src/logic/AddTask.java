package logic;

import java.util.ArrayList;
import storage.Output;

public class AddTask implements Command {
	
	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	private static final String MESSAGE_ADD_FAILURE = "Unable to add message.";
	private static final String COMMAND_TYPE = "add";
	private static final String SYMBOL_SPACE = " ";
	
	private String theEvent;
	private String date;
	private String time;
	
	//Constructor
	public AddTask(){
		
	}
	
	public Output execute(){
		return new Output(true, theEvent, COMMAND_TYPE, MESSAGE_ADD_SUCCESS);
	}

	
	//GETTERS
	public String getEvent(){
		return theEvent;
	}
	//SETTERS
	public void setEvent(String inputEvent){
		this.theEvent = inputEvent;
	}
	
	public void setDate(String inputDate){
		this.date = inputDate;
	}
}

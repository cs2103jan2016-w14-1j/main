package logic;

import java.util.ArrayList;
import storage.Output;

public class addTask {
	
	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	private static final String MESSAGE_ADD_FAILURE = "Unable to add message.";
	private static final String COMMAND_TYPE = "add";
	private String theEvent;
	private ArrayList<String> dates;
	private ArrayList<String> times;
	//time?? how?
	
	//Constructor
	public addTask(){
		
	}
	
	public Output perform(){
		//So this shit
		//is supposed to return this chunk of bs
		//as it's the add command?
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
}

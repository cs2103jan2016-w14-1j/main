package logic;

import storage.Output;

public interface Command {

	/**
	 * The execute() command is the default action
	 * function call for every class that implements the Command interface.
	 * @return
	 */
	Task execute();
	
	// Getters
	String getEvent();
	
	// Setters
	void setEvent(String inputEvent);
}

package logic;

import java.util.ArrayList;

import parser.ParserMain;
import storage.Storage;

public class LogicMain {
	private final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Nothing to delete at this index!";
	private final String COMPLETE_TASK_SUCCESS = "Marked as complete.";
	private final String INCOMPLETE_TASK_SUCCESS = "Marked as incomplete.";
	private ArrayList<Task> operatingTasks;
	
	ParserMain inputParser;
	Storage storageSystem;
	
	public LogicMain(){
		inputParser = new ParserMain();
		storageSystem = new Storage();
		operatingTasks = new ArrayList<Task>();
	}
	
	public String processCommand(String userInput){
		String[] formattedInput = inputParser.processInput(userInput);
		Task newCreatedTask = LogicHandler.processCommand(formattedInput);
		String feedbackToUI = operateOnTask(newCreatedTask);
		return feedbackToUI;
		
	}
	
	//method for UI to get that shit.
	public ArrayList<Task> getOperatingTasksForUI(){
		return operatingTasks;
	}
	
	private String operateOnTask(Task requestedTask){
		String commandType = requestedTask.get_cmd();
		switch(commandType){
			case "add":
				return doAdd(requestedTask);
			case "delete":
				return doDelete(requestedTask);
			case "clear" :
				return doClear(requestedTask);
			case "display" :
				return doDisplay(requestedTask);
			case "edit" :
				return doEdit(requestedTask);
			case "redo" : 
				return doRedo(requestedTask);
			case "undo" :
				return doUndo(requestedTask);
			case "search" :
				return doSearch(requestedTask);
			case "markcomplete" :
				return doMarkComplete(requestedTask);
			case "markincomplete" :
				return doMarkIncomplete(requestedTask);
			case "invalid" :
				return doInvalid(requestedTask);
			default: 
				return "UNKNOWN OPERATION";
		}
	}
	
	private String doAdd(Task taskToOp){
		String feedback = storageSystem.addToStorage(taskToOp);
		return feedback;
	}
	
	private String doDelete(Task taskToOp){
		String feedback = storageSystem.deleteFromStorage(taskToOp);
		return feedback;
	}
	
	private String doClear(Task taskToOp){
		String feedback = storageSystem.clearFromStorage(taskToOp);
		return feedback;
	}
	
	private String doDisplay(Task taskToOp){
		String feedback = storageSystem.displayFromStorage(taskToOp);
		return feedback;
	}
	
	private String doEdit(Task taskToOp){
		String feedback = storageSystem.editFromStorage(taskToOp);
		return feedback;
	}
	
	private String doUndo(Task taskToOp){
		String feedback = storageSystem.undoFromStorage(taskToOp);
		return feedback;
	}
	
	private String doRedo(Task taskToOp){
		String feedback = storageSystem.redoFromStorage(taskToOp);
		return feedback;
	}
	
	private String doSearch(Task taskToOp){
		//TODO:
		//get ARRAYLIST<task> from storage
		//do search.
		//update operatingTasks
	}
	
	private String doMarkComplete(Task taskToOp){
		// takes the current operating list.
		int operateOnIndex = taskToOp.get_index();
		try{
			Task operateOn = operatingTasks.get(operateOnIndex);
			operateOn.set_Complete();
			return COMPLETE_TASK_SUCCESS;
		} catch(IndexOutOfBoundsException e) {
			return INDEX_OUT_OF_BOUNDS_MESSAGE;
		}
	}
	
	private String doMarkIncomplete(Task taskToOp){
		int operateOnIndex = taskToOp.get_index();
		try{
			Task operateOn = operatingTasks.get(operateOnIndex);
			operateOn.set_Incomplete();
			return INCOMPLETE_TASK_SUCCESS;
		} catch (IndexOutOfBoundsException e){
			return INDEX_OUT_OF_BOUNDS_MESSAGE;
		}
	}
	
	private String doInvalid(Task taskToOp){
		String feedback = taskToOp.get_messageToUser();
		return feedback;
	}
	
}

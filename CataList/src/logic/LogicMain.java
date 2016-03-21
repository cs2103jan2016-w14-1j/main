package logic;

import java.util.ArrayList;

import parser.ParserMain;
import storage.Storage;

public class LogicMain {
	private final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Index out of bounds. Please try again.";
	private final String COMPLETE_TASK_SUCCESS = "Marked as complete.";
	private final String INCOMPLETE_TASK_SUCCESS = "Marked as incomplete.";
	
	private ArrayList<Task> completeTasks;
	private ArrayList<Task> incompleteTasks;
	private ArrayList<Task> masterListTasks;
	
	private ArrayList<Task> operatingTasks;
	
	private ArrayList<ArrayList<Task>> state;
	
	private static final int INCOMPLETE_LIST_INDEX = 0;
	private static final int COMPLETE_LIST_INDEX = 1;
	private static final int MASTER_LIST_INDEX = 2;
	
	ParserMain inputParser;
	Storage storageSystem;
	
	public LogicMain(){
		inputParser = new ParserMain();
		storageSystem = new Storage();
		
		masterListTasks = new ArrayList<Task>();
		completeTasks = new ArrayList<Task>();
		incompleteTasks = new ArrayList<Task>();
		
		masterListTasks = storageSystem.loadTask();
		operatingTasks = masterListTasks;
		state.add(masterListTasks);
	}
	
	public String processCommand(String userInput){
		String[] formattedInput = inputParser.processInput(userInput);
		Task newCreatedTask = LogicHandler.processCommand(formattedInput);
		String feedbackToUI = operateOnTask(newCreatedTask);
		
		regenerateSubListsFromMasterList();
		//TODO: MAINTAIN A STATE? AARON PLS.
		//state.add(masterListTasks);
		return feedbackToUI;
	}
	
	//method for UI to get that shit.
	public ArrayList<Task> getOperatingTasksForUI(){
		return operatingTasks;
		/*
		if (operatingOn == 1){
			return completeTasks;
		} else if (operatingOn == 2){
			return incompleteTasks;
		} else {
			return masterListTasks;
		}
		*/
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
	
	private void regenerateSubListsFromMasterList(){
		incompleteTasks.clear();
		completeTasks.clear();
		for(Task eachTask : masterListTasks){
			if(eachTask.get_completionState()){
				completeTasks.add(eachTask);
			} else {
				incompleteTasks.add(eachTask);
			}
		}
	}
	/*
	private void saveBackToOriginalList(){
		if(operatingOn == MASTER_LIST_INDEX){
			masterListTasks = operatingTasks;
		} else if (operatingOn == COMPLETE_LIST_INDEX){
			completeTasks = operatingTasks;
		} else if (operatingOn == INCOMPLETE_LIST_INDEX){
			incompleteTasks = operatingTasks;
		}
	}
	*/
	
	private String doAdd(Task taskToOp){
		masterListTasks.add(taskToOp);
		String feedback = taskToOp.get_messageToUser();
		return feedback;
	}
	
	private String doDelete(Task taskToOp){
		int operateIndex = taskToOp.get_index();
		
		try{
			operatingTasks.remove(operateIndex);
		} catch (IndexOutOfBoundsException e){
			taskToOp.setMessageErrorEmpty();
		}
		String feedback = taskToOp.get_messageToUser();
		return feedback;
	}
	
	private String doClear(Task taskToOp){
		String feedback = taskToOp.get_messageToUser();
		
		incompleteTasks.clear();
		completeTasks.clear();
		masterListTasks.clear();
		
		operatingTasks = masterListTasks;
		return feedback;
	}
	
	private String doDisplay(Task taskToOp){
		int listToDisplay = taskToOp.get_index();
		
		if(listToDisplay == COMPLETE_LIST_INDEX){
			operatingTasks = completeTasks;
		} else if (listToDisplay == INCOMPLETE_LIST_INDEX){
			operatingTasks = incompleteTasks;
		} else if(listToDisplay == MASTER_LIST_INDEX){
			operatingTasks = masterListTasks;
		} else {
			taskToOp.setMessageErrorDefault();
			return taskToOp.get_messageToUser();
		}
		
		String feedback = taskToOp.get_messageToUser();
		return feedback;
	}
	
	private String doEdit(Task taskToOp){
		int operateIndex = taskToOp.get_index();
		Task toEdit = operatingTasks.get(operateIndex);
		
		try{
			//find and change inside masterList 
			for(int i = 0 ; i < masterListTasks.size() ; i++){
				if(masterListTasks.get(i).equals(toEdit)){
					masterListTasks.set(i, taskToOp);
				}
			}
		
			operatingTasks.set(operateIndex, taskToOp);
		
		} catch (IndexOutOfBoundsException e){
			taskToOp.setMessageErrorEmpty();
		}
		String feedback = taskToOp.get_messageToUser();
		return feedback;
	}
	
	/*
	private String doUndo(Task taskToOp){
		previousMasterList = state
	}
	
	private String doRedo(Task taskToOp){
		String feedback = storageSystem.redoFromStorage(taskToOp);
		return feedback;
	}
	*/
	
	private String doSearch(Task taskToOp){
		String toFind = taskToOp.get_task();
		ArrayList<Task> foundList = new ArrayList<Task>();
		
		for(Task eachTask : masterListTasks){
			if(eachTask.get_task().contains(toFind)){
				foundList.add(eachTask);
			}
		}
		operatingTasks = foundList;
		return taskToOp.get_messageToUser();
	}
	
	private String doMarkComplete(Task taskToOp){
		int operateIndex = taskToOp.get_index();
		try{
			Task operateOn = operatingTasks.get(operateIndex);
			
			for(int i = 0 ; i < masterListTasks.size() ; i++){
				if(masterListTasks.get(i).equals(operateOn)){
					masterListTasks.get(i).set_Complete();
				}
			}
			operatingTasks.get(operateIndex).set_Complete();
			
			return COMPLETE_TASK_SUCCESS;
		} catch(IndexOutOfBoundsException e) {
			return INDEX_OUT_OF_BOUNDS_MESSAGE;
		}
	}
	
	private String doMarkIncomplete(Task taskToOp){
		int operateIndex = taskToOp.get_index();
		try{
			Task operateOn = operatingTasks.get(operateIndex);
			
			for(int i = 0 ; i < masterListTasks.size() ; i++){
				if(masterListTasks.get(i).equals(operateOn)){
					masterListTasks.get(i).set_Incomplete();
				}
			}
			
			operatingTasks.get(operateIndex).set_Incomplete();
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

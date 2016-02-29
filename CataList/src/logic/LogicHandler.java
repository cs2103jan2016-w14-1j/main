package logic;
import java.util.Stack;
import parser.Parser;


public class LogicHandler {
	
	//TODO:
	//1> gets called by UI
	//2> receives string? from UI
	//3> calls parser
	//4> receive result from parser
	//5> create Task objects from results
	//6> pass to Storage
	
	static Stack<String> userInputStack;
	Parser commandParser;
	
	/********Constructor********/
	public LogicHandler(){
		this.initialSetup();
	}
	
	private void initialSetup(){
		userInputStack = new Stack<String>();
		commandParser = new Parser();
	}
	
	public void handleInput(String userInput){
	//TODO: parser
		String[] formattedString = commandParser(userInput);
		createTask(formattedString);
	}
	/**
	 * unused
	private boolean isValidInput(String[] checkString){
		if(checkString[0] == "invalid"){
			return false;
		} else {
			return true;
		}
	}
	**/
	
	private Task createTask(String[] checkString){
		String commandType = checkString[0];
		switch(commandType){
			case "add":
				return new AddTask(checkString);
			case "delete":
				return DeleteTask(checkString);
			
		}
	}
	
}

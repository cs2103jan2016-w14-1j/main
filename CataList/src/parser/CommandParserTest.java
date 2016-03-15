package parser;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

public class CommandParserTest {
	 private static final Logger logger =
		        Logger.getLogger(CommandParserTest.class.getName());
	 
	@Test
	public void AddCommandTest(){
		logger.entering(getClass().getName(), "AddTest");
		
		String userInput1 = "add \"Fire James 2GD";
		String userInput2 = "/a Fire James 2GD";
		String userInput3 = "-a Fire James 2GD";
		String userInput4 = "++ Fire James 2GD";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		String result4 = CommandParser.parseCommand(userInput4);
		
		String expectedResult = "add";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);
		assertEquals(expectedResult, result4);
		logger.exiting(getClass().getName(), "add test");
	}
	
	@Test
	public void DeleteCommandTest(){
		String userInput1 = "delete Fire James 2GD";
		String userInput2 = "rm Fire James 2GD";
		String userInput3 = "del Fire James 2GD";
		String userInput4 = "-del Fire James 2GD";
		String userInput5 = "/del Fire James 2GD";
		String userInput6 = "-- Fire James 2GD";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		String result4 = CommandParser.parseCommand(userInput4);
		String result5 = CommandParser.parseCommand(userInput5);
		String result6 = CommandParser.parseCommand(userInput6);
		
		String expectedResult = "delete";
		System.out.println(result1);
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);
		assertEquals(expectedResult, result4);
		assertEquals(expectedResult, result5);
		assertEquals(expectedResult, result6);
	}
	
	@Test
	public void ClearCommandTest(){
		String userInput1 = "clear";
		String userInput2 = "nuke";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		
		String expectedResult = "clear";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
	}
	
	@Test
	public void DisplayCommandTest(){
		String userInput1 = "ls";
		String userInput2 = "display";
		String userInput3 = "show";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		
		String expectedResult = "display";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);		
	}
	
	@Test
	public void SearchCommandTest(){
		String userInput1 = "search";
		String userInput2 = "find";
		String userInput3 = "/find";
		String userInput4 = "/f";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		String result4 = CommandParser.parseCommand(userInput4);
		
		String expectedResult = "search";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);		
		assertEquals(expectedResult, result4);		
	}
	
	@Test
	public void EditCommandTest(){
		String userInput1 = "edit";
		String userInput2 = "change";
		String userInput3 = "update";
		String userInput4 = "-e";
		String userInput5 = "/e";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		String result4 = CommandParser.parseCommand(userInput4);
		String result5 = CommandParser.parseCommand(userInput5);
		
		String expectedResult = "edit";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);		
		assertEquals(expectedResult, result4);		
		assertEquals(expectedResult, result5);		
	}	

	@Test
	public void MarkCompleteCommandTest(){
		String userInput1 = "done";
		String userInput2 = "mark";
		String userInput3 = "complete";
		String userInput4 = "-c";
		String userInput5 = "/c";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		String result4 = CommandParser.parseCommand(userInput4);
		String result5 = CommandParser.parseCommand(userInput5);
		
		String expectedResult = "markcomplete";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);		
		assertEquals(expectedResult, result4);		
		assertEquals(expectedResult, result5);		
	}
	
	@Test
	public void MarkIncompleteCommandTest(){
		String userInput1 = "undone";
		String userInput2 = "unmark";
		String userInput3 = "notdone";
		String userInput4 = "incomplete";
		String userInput5 = "/ic";
		String userInput6 = "-ic";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		String result4 = CommandParser.parseCommand(userInput4);
		String result5 = CommandParser.parseCommand(userInput5);
		String result6 = CommandParser.parseCommand(userInput6);
		
		String expectedResult = "markincomplete";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);		
		assertEquals(expectedResult, result4);		
		assertEquals(expectedResult, result5);		
		assertEquals(expectedResult, result6);		
	}
	
	@Test
	public void UndoCommandTest(){
		String userInput1 = "undo";
		String userInput2 = "/u";
		String userInput3 = "-u";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		
		String expectedResult = "undo";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);		
	}

	@Test
	public void RedoCommandTest(){
		String userInput1 = "redo";
		String userInput2 = "/r";
		String userInput3 = "-r";
		
		String result1 = CommandParser.parseCommand(userInput1);
		String result2 = CommandParser.parseCommand(userInput2);
		String result3 = CommandParser.parseCommand(userInput3);
		
		String expectedResult = "redo";
		
		assertEquals(expectedResult, result1);
		assertEquals(expectedResult, result2);
		assertEquals(expectedResult, result3);		
	}
	

}

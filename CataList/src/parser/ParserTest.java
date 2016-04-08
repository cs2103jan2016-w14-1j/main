//@@author a0124946

package parser;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.COMMAND_TYPE;
import parser.Parser;

public class ParserTest {

	COMMAND_TYPE expectedResults;
	String userInput;
	
	@Test
	public void test() {
		userInput = "add feed dota from 10am to 10pm";
		expectedResults = COMMAND_TYPE.ADD_TASK;
		assertEquals(Parser.getCommand(userInput), expectedResults);
	}

}

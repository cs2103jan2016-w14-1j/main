//@@author a0124946

package parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EventParserTest {

	@Test
	public void parseEventNoDateNoTime(){
		String userInput = "add Steal ramzes666's keyboard";
		String testResult = EventParser.parseEvent(userInput);
		String expectedResult = "Steal ramzes666's keyboard";
		
		assertEquals(testResult, expectedResult);
	}
	
	@Test
	public void parseEventWithDateNoTime(){
		String userInput = "add Steal ramzes666's keyboard 23-march";
		String testResult = EventParser.parseEvent(userInput);
		String expectedResult = "Steal ramzes666's keyboard";
		
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
	}
	
	@Test
	public void parseEventWithDateWithTime(){
		String userInput = "add Steal ramzes666's keyboard 22-march 2pm";
		String testResult = EventParser.parseEvent(userInput);
		String expectedResult = "Steal ramzes666's keyboard";
		
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
	}
	
	@Test
	public void parseEventWithDateWithTimeReOrganized(){
		String userInput = "add Steal 22-march ramzes666's keyboard 2pm";
		String testResult = EventParser.parseEvent(userInput);
		String expectedResult = "Steal ramzes666's keyboard";
		
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
	}
	
	@Test
	public void parseEventWithDateWith2Time(){
		String userInput = "add Steal 22-march ramzes666's keyboard 2pm";
		String testResult = EventParser.parseEvent(userInput);
		String expectedResult = "Steal ramzes666's keyboard";
		
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
	}
	
	@Test
	public void parseEventWithNothing(){
		String userInput = "add";
		String testResult = EventParser.parseEvent(userInput);
		String expectedResult = "";
		
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
	}
}

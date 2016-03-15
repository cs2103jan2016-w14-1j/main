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
		String userInput = "add Steal ramzes666's keyboard -d 23-march";
		String testResult = EventParser.parseEvent(userInput);
		String expectedResult = "Steal ramzes666's keyboard";
		
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
	}
	
	@Test
	public void parseEventWithDateWithTime(){
		String userInput = "add Steal ramzes666's keyboard -d 22-march -t 322pm";
		String testResult = EventParser.parseEvent(userInput);
		String expectedResult = "Steal ramzes666's keyboard";
		
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
	}
}

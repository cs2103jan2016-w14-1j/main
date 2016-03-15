package parser;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IndexParserTest {

	@Test
	public void findIndex(){
		String userInput = "display 1";
		
		int testIndex = IndexParser.parseIndex(userInput);
		int expectedIndex = 1;
		
		assertEquals(expectedIndex, testIndex);
	}
	
	@Test
	public void findIndexLotsOfNumbers(){
		String userInput = "BEGIN_PGP_SIGNATURE 1 2 3 4 5 6 ";
		int testIndex = IndexParser.parseIndex(userInput);
		int expectedIndex = 1;
		
		assertEquals(expectedIndex, testIndex);
	}
}

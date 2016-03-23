package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import org.junit.Test;

public class DateTimeParserTest {
	
	@Test
	public void parseDateTimeTestWithYearFormatSlash(){
		String userInput = "add ShangHai Major Failure -d 22/3/16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithYearFormatDash(){
		String userInput = "add ShangHai Major Failure -d 22-3-16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithYearFormatDot(){
		String userInput = "add ShangHai Major Failure -d 22.3.16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithYearFormatNothing(){
		String userInput = "add ShangHai Major Failure -d 220316";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithYearFormatEnglishDot(){
		String userInput = "add ShangHai Major Failure -d 22.March.16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearFormatEnglishDash(){
		String userInput = "add ShangHai Major Failure -d 22-March-16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithoutYearFormatSlash(){
		String userInput = "add ShangHai Major Failure -d 22/3";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithoutYearFormatDash(){
		String userInput = "add ShangHai Major Failure -d 22-3";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithoutYearFormatDot(){
		String userInput = "add ShangHai Major Failure -d 22.3";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithoutYearFormatNothing(){
		String userInput = "add ShangHai Major Failure -d 2203";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithoutYearFormatEnglishDot(){
		String userInput = "add ShangHai Major Failure -d 22.march";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithoutYearFormatEnglishNothing(){
		String userInput = "add ShangHai Major Failure -d 22March";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithoutYearFormatEnglishDash(){
		String userInput = "add ShangHai Major Failure -d 22-March";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	//WITH TIME
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatColon(){
		String userInput = "add ShangHai Major Failure -d 22-March -t 09:30";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatDot(){
		String userInput = "add ShangHai Major Failure -d 22-March -t 09.30";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatAPMNothingOnlyHour(){
		String userInput = "add ShangHai Major Failure -d 22-March -t 9am";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0900");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatAPMColon(){
		String userInput = "add ShangHai Major Failure -d 22-March -t 09:30am";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatAPMDot(){
		String userInput = "add ShangHai Major Failure -d 22-March -t 09.30am";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatAPMNothing(){
		String userInput = "add ShangHai Major Failure -d 22-March -t 0930am";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	//EQUIVALENCE PARTITIONING UPDATE.
	// TEST : SWAPPED DATE TIME, REORGANIZED TIME/DATE, UNACCEPTABLE TIME, UNACCEPTABLE DATE.
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatDateNotPresent(){
		String userInput = "add ShangHai Major Failure -d -t 9pm";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertFalse(expectedResults.equals(testResults));
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatTimeNotPresent(){
		String userInput = "add ShangHai Major Failure -d 1.Jan -t";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("01/01/16");
		expectedResults.add("0930");
		
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertFalse(expectedResults.equals(testResults));
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatDateTimeBothNotPresent(){
		String userInput = "add ShangHai Major Failure -d -t";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertFalse(expectedResults.equals(testResults));
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatSwapped(){
		String userInput = "add ShangHai Major Failure -t 0930 -d 22-march";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatReorganized(){
		String userInput = "add -t 0930 ShangHai -d 22-march Major Failure";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatUnacceptable(){
		String userInput = "add ShangHai Major Failure -d 22-March -t 9o'clock";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertFalse(expectedResults.equals(testResults));
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatDateUnacceptable(){
		String userInput = "add ShangHai Major Failure -d 334-yule -t 0930";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		expectedResults.add("0930");
		
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertFalse(expectedResults.equals(testResults));
	}	
	
}

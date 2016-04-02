package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import org.junit.Test;

public class DateTimeParserTest {
	
	@Test
	public void parseDateTimeTest(){
		String userInput = "add hihi 3pm";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("1500");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearFormatSlash(){
		String userInput = "add ShangHai Major Failure 22/3/16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	

	@Test
	public void parseDateTimeTestWithYearFormatDash(){
		String userInput = "add ShangHai Major Failure  22-3-16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithYearFormatDot(){
		String userInput = "add ShangHai Major Failure  22.3.16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithYearFormatNothing(){
		String userInput = "add ShangHai Major Failure  220316";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithYearFormatEnglishDot(){
		String userInput = "add ShangHai Major Failure  22.March.16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithYearFormatEnglishDash(){
		String userInput = "add ShangHai Major Failure  22-March-16";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithoutYearFormatSlash(){
		String userInput = "add ShangHai Major Failure  22/3";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithoutYearFormatDash(){
		String userInput = "add ShangHai Major Failure  22-3";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithoutYearFormatDot(){
		String userInput = "add ShangHai Major Failure  22.3";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	@Test
	public void parseDateTimeTestWithoutYearFormatNothing(){
		String userInput = "add ShangHai Major Failure  2203";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithoutYearFormatEnglishDot(){
		String userInput = "add ShangHai Major Failure  22.march";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithoutYearFormatEnglishNothing(){
		String userInput = "add ShangHai Major Failure  22March";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}

	@Test
	public void parseDateTimeTestWithoutYearFormatEnglishDash(){
		String userInput = "add ShangHai Major Failure  22-March";
		ArrayList<String> testResults = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("22/03/16");
		System.out.println(expectedResults.toString());
		System.out.println(testResults.toString());
		assertEquals(expectedResults, testResults);
	}
	
	//WITH TIME
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatColon(){
		String userInput = "add ShangHai Major Failure  22-March  09:30";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());
		assertEquals(expectedResultsDate, testResultsDate);
		assertEquals(expectedResultsTime, testResultsTime);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatDot(){
		String userInput = "add ShangHai Major Failure  22-March  09.30";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());
		assertEquals(expectedResultsDate, testResultsDate);
		assertEquals(expectedResultsTime, testResultsTime);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatAPMNothingOnlyHour(){
		String userInput = "add ShangHai Major Failure  22-March  9am";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0900");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());
		assertEquals(expectedResultsDate, testResultsDate);
		assertEquals(expectedResultsTime, testResultsTime);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatAPMColon(){
		String userInput = "add ShangHai Major Failure  22-March  09:30am";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());
		assertEquals(expectedResultsDate, testResultsDate);
		assertEquals(expectedResultsTime, testResultsTime);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatAPMDot(){
		String userInput = "add ShangHai Major Failure  22-March  09.30am";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());
		assertEquals(expectedResultsDate, testResultsDate);
		assertEquals(expectedResultsTime, testResultsTime);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatAPMNothing(){
		String userInput = "add ShangHai Major Failure  22-March  0930am";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());
		assertEquals(expectedResultsDate, testResultsDate);
		assertEquals(expectedResultsTime, testResultsTime);
	}
	//EQUIVALENCE PARTITIONING UPDATE.
	// TEST : SWAPPED DATE TIME, REORGANIZED TIME/DATE, UNACCEPTABLE TIME, UNACCEPTABLE DATE.
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatDateNotPresent(){
		String userInput = "add ShangHai Major Failure   9pm";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());
	
		assertFalse(expectedResultsDate.equals(testResultsDate));
		assertFalse(expectedResultsTime.equals(testResultsTime));
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatTimeNotPresent(){
		String userInput = "add ShangHai Major Failure  1.Jan ";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());
	
		assertFalse(expectedResultsDate.equals(testResultsDate));
		assertFalse(expectedResultsTime.equals(testResultsTime));
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatDateTimeBothNotPresent(){
		String userInput = "add ShangHai Major Failure  ";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());

		assertFalse(expectedResultsDate.equals(testResultsDate));
		assertFalse(expectedResultsTime.equals(testResultsTime));
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatSwapped(){
		String userInput = "add ShangHai Major Failure  0930  22-march";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());

		assertEquals(expectedResultsDate, testResultsDate);
		assertEquals(expectedResultsTime, testResultsTime);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatReorganized(){
		String userInput = "add  0930 ShangHai  22-march Major Failure";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());

		assertEquals(expectedResultsDate, testResultsDate);
		assertEquals(expectedResultsTime, testResultsTime);
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatUnacceptable(){
		String userInput = "add ShangHai Major Failure  22-March  9o'clock";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());

		assertFalse(expectedResultsTime.equals(testResultsTime));
	}
	
	@Test
	public void parseDateTimeTestWithYearWithTimeFormatDateUnacceptable(){
		String userInput = "add ShangHai Major Failure  334-yule  0930";
		ArrayList<String> testResultsDate = DateTimeParser.parseDateTime(userInput).get(0);
		ArrayList<String> expectedResultsDate = new ArrayList<String>();
		ArrayList<String> testResultsTime = DateTimeParser.parseDateTime(userInput).get(1);
		ArrayList<String> expectedResultsTime = new ArrayList<String>();
		expectedResultsDate.add("22/03/16");
		expectedResultsTime.add("0930");
		System.out.println(expectedResultsDate.toString());
		System.out.println(testResultsDate.toString());
		System.out.println(expectedResultsTime.toString());
		System.out.println(testResultsTime.toString());

		assertFalse(expectedResultsDate.equals(testResultsDate));	
	}	
	
}

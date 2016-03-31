package parser;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

public class DateTimeParser {
	private static final String SYMBOL_EMPTY = "";
	private static final int EMPTY_SIZE = 0;
	private static final int SIZE_ONE = 0;
	
	protected static final String INVALID_DATE_MESSAGE = "Invalid date input";
	protected static final String INVALID_TIME_MESSAGE = "Invalid time input";
	
	public static ArrayList<ArrayList<String>> parseDateTime(String userInput){
		String[] splitInput = userInput.split(" ");
		ArrayList<String> dateArgs = parseStartEndDates(splitInput);
		ArrayList<String> timeArgs = parseStartEndTimes(splitInput);
		
		if(dateArgs.size() == EMPTY_SIZE && timeArgs.size() != EMPTY_SIZE){
			if(timeArgs.size() == SIZE_ONE){
				dateArgs.add(getToday());
			} else {
				dateArgs.add(getToday());
				dateArgs.add(getToday());
			}
		}
		
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		results.add(dateArgs);
		results.add(timeArgs);
		return results;
	}
	
	/*
	private static int searchForDateFlagsIndex(String[] splittedInput){
		int indexOfDate = 0;
		for(int i = 0; i <splittedInput.length; i++){
			for(String eachKeyword : KeywordConstraints.KW_DATE_FLAG){
				if(splittedInput[i].contains(eachKeyword)){
					indexOfDate = i;
					break;
				}
			}
		}
		return indexOfDate + 1;
	}
	
	private static int searchForTimeFlagsIndex(String[] splittedInput){
		int indexOfTime = 0;
		for(int i = 0 ; i<splittedInput.length; i++){
			for(String eachKeyword : KeywordConstraints.KW_TIME_FLAG){
				if(splittedInput[i].contains(eachKeyword)){
					indexOfTime = i;
					break;
				}
			}
		}
		return indexOfTime + 1;
	}
	*/
	private static ArrayList<String> parseStartEndDates(String[] splitInput){
		String startDate;
		int length = splitInput.length;
		int startIndex = 0;
		ArrayList<String> results = new ArrayList<String>();
		
		for(int i = 0 ; i < length ; i++){
			startDate = parseForDate(splitInput[i]);
			if(!startDate.equalsIgnoreCase(SYMBOL_EMPTY)){
				results.add(startDate);
				break;
			}
		}
		
		if(startIndex != 0){
			String endDate;
			for(int j = startIndex ; j < length ; j++){
				endDate = parseForDate(splitInput[j]);
				if(!endDate.equalsIgnoreCase(SYMBOL_EMPTY)){
					results.add(endDate);
					break;
				}
			}
		}
		
		return results;
	}
	
	private static ArrayList<String> parseStartEndTimes(String[] splitInput){
		String startTime;
		int length = splitInput.length;
		int startIndex = 0;
		ArrayList<String> results = new ArrayList<String>();
		
		for(int i = 0 ; i < length ; i++){
			startTime = parseForTime(splitInput[i]);
			if(!startTime.equalsIgnoreCase(SYMBOL_EMPTY)){
				results.add(startTime);
				break;
			}
		}
		
		if(startIndex != 0){
			String endTime;
			for(int j = startIndex ; j < length ; j++){
				endTime = parseForDate(splitInput[j]);
				if(!endTime.equalsIgnoreCase(SYMBOL_EMPTY)){
					results.add(endTime);
					break;
				}
			}
		}
		
		return results;
	}
	
	private static String getToday(){
		DateTime now = new DateTime();
		LocalDate today = now.toLocalDate();
		String dateParsed = today.toString(KeywordConstraints.KW_FORMAT_DATE_STORAGE);
		return dateParsed;
	}
	
	private static String parseForDate(String targetString){
		LocalDate curDate = new LocalDate();
		String dateParsed = SYMBOL_EMPTY;
		
		if(!parseForDateWithYear(curDate, targetString).isEmpty()){
			dateParsed = parseForDateWithYear(curDate, targetString);
		} else if (!parseForDateNoYear(curDate, targetString).isEmpty()){
			dateParsed = parseForDateNoYear(curDate, targetString);
		}
		
		return dateParsed;

	}
	
	private static String parseForDateNoYear(LocalDate currentDate, String targetString) {
		LocalDate targetDate = null;
		String dateParsed = SYMBOL_EMPTY;
		
		for(String eachKeyword : KeywordConstraints.KW_FORMAT_DATE_WITHOUT_YEAR){
			try{
				targetDate = DateTimeFormat.forPattern(eachKeyword).parseLocalDate(targetString)
										.withYear(currentDate.getYear());
				dateParsed = targetDate.toString(KeywordConstraints.KW_FORMAT_DATE_STORAGE);
			} catch (IllegalArgumentException e) {
				//nothing 
			}
		}
		return dateParsed;
	}
	
	private static String parseForDateWithYear(LocalDate currentDate, String targetString) {
		LocalDate targetDate = null;
		String dateParsed = SYMBOL_EMPTY;
		
		for(String eachKeyword : KeywordConstraints.KW_FORMAT_DATE_WITH_YEAR){
			try{
				targetDate = DateTimeFormat.forPattern(eachKeyword).parseLocalDate(targetString);
				dateParsed = targetDate.toString(KeywordConstraints.KW_FORMAT_DATE_STORAGE);
			} catch (IllegalArgumentException e){
				//nothing.
			}
		}
		
		return dateParsed;
	}
	
	private static String parseForTime(String targetString){
		LocalTime curTime = null;
		String timeParsed = SYMBOL_EMPTY;
		for(String eachKeyword : KeywordConstraints.KW_FORMAT_TIME){
			try{
			curTime = DateTimeFormat.forPattern(eachKeyword).parseLocalTime(targetString);
			timeParsed = curTime.toString(KeywordConstraints.KW_FORMAT_TIME_STORAGE);
			} catch (IllegalArgumentException e){
				//nothing
			}
		}
		return timeParsed;
	}
	
	private static boolean hasDateFlag(String userInput){
		boolean result = false;
		for(String eachKeyword : KeywordConstraints.KW_DATE_FLAG){
			if(userInput.contains(eachKeyword)){
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	private static boolean hasTimeFlag(String userInput){
		boolean result = false;
		for(String eachKeyword : KeywordConstraints.KW_TIME_FLAG){
			if(userInput.contains(eachKeyword)){
				result = true;
				break;
			}
		}
		
		return result;
	}
}

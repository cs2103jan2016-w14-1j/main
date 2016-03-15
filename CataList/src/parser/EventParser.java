package parser;

public class EventParser {
	private static final String SYMBOL_WHITESPACE = " ";
	private static final String SYMBOL_EMPTY = "";
<<<<<<< HEAD
=======
	
>>>>>>> 4bc2ba00decfba7f0731ebf36b9f10c652e35bf0
	private static final int COMMAND_WORD_INDEX = 0;
	private static final int ARRAY_MINIMUM_LENGTH = 2; 
	
	public static String parseEvent(String userInput){
		String inputEvent = removeCommandWord(userInput);
		String resultingEvent = removeDateTime(inputEvent);
		return resultingEvent.trim();
	}
	
	private static String removeCommandWord(String userInput){
		String[] inputArray = userInput.split(SYMBOL_WHITESPACE);
		String remainingText = SYMBOL_EMPTY;
		if(inputArray.length >= ARRAY_MINIMUM_LENGTH) {
			for(int i = 0; i < inputArray.length; i++) {
				if (i != COMMAND_WORD_INDEX){
					remainingText += inputArray[i];
				} 
				
				if(i != inputArray.length) {
					remainingText += SYMBOL_WHITESPACE;
				}
			}
		}
		return remainingText;
	}
	
	private static String removeDateTime(String userInput){
		String extractedEvent = SYMBOL_EMPTY;
		String[] inputArray = userInput.split(SYMBOL_WHITESPACE);
		
		for(int i = 0 ; i <inputArray.length ; i++){
			if(isNotDate(inputArray[i]) && isNotTime(inputArray[i])){
				extractedEvent += inputArray[i];
				extractedEvent += SYMBOL_WHITESPACE;
			} else {
				break;
			}
		}
		return extractedEvent;
	}
	
	private static boolean isNotDate(String testString){
		boolean noDate = true;
		for(String eachFlag : KeywordConstraints.KW_DATE_FLAG){
			if(testString.equalsIgnoreCase(eachFlag)){
				noDate = false;
				return noDate;
			}
		}
		return noDate;
	}
	
	private static boolean isNotTime(String testString){
		boolean noTime = true;
		for(String eachFlag : KeywordConstraints.KW_TIME_FLAG){
			if(testString.equalsIgnoreCase(eachFlag)){
				noTime = false;
				return noTime;
			}
		}
		return noTime;
	}
	
}
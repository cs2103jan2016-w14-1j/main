package parser;

public class IndexParser {

	private static final String INVALID_INDEX_EXCEPTION = "Invalid index";
	private static final int FIRST_WORD_INDEX = 1;
	
	public static int parseIndex(String userInputEvent){
		String IndexWord = findIndexWord(userInputEvent);
		int parsedInt = tryParse(IndexWord);
		System.out.println(parsedInt);
		return parsedInt;
	}
	
	private static String findIndexWord(String userInputEvent){
		String[] userInputArray = userInputEvent.split(" ");
		try{
			return userInputArray[FIRST_WORD_INDEX];
		} catch (IndexOutOfBoundsException e){
			return INVALID_INDEX_EXCEPTION;
		}
	}
	
	private static int tryParse(String parseWord){
		try{
			return Integer.parseInt(parseWord);
		} catch (NumberFormatException e){
			return -1;
		}
	}

}

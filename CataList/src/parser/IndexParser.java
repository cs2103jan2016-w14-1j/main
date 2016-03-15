package parser;

public class IndexParser {
	private static final int FIRST_WORD_INDEX = 1;
	
	public static int parseIndex(String userInputEvent){
		String IndexWord = findIndexWord(userInputEvent);
		int parsedInt = tryParse(IndexWord);
		return parsedInt;
	}
	
	private static String findIndexWord(String userInputEvent){
		String[] userInputArray = userInputEvent.split(" ");
		return userInputArray[FIRST_WORD_INDEX];
	}
	
	private static int tryParse(String parseWord){
		try{
			return Integer.parseInt(parseWord);
		} catch (NumberFormatException e){
			return -1;
		}
	}

}

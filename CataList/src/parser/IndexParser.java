package parser;

public class IndexParser {
<<<<<<< HEAD
	private static final int FIRST_WORD_INDEX = 0;
=======
	private static final int FIRST_WORD_INDEX = 1;
>>>>>>> 4bc2ba00decfba7f0731ebf36b9f10c652e35bf0
	
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

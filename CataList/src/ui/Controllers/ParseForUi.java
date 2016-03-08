package Controllers;

public class ParseForUi {
	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	private static final String MESSAGE_CLEAR_SUCCESS = "Your dashboard has been cleared.";
	
	public static boolean parseLogicMessage(String msg) {
		switch(msg) {
			case MESSAGE_ADD_SUCCESS:
				return true;
			case MESSAGE_CLEAR_SUCCESS:
				return true;
			default:
				return false;
		}
	}
}

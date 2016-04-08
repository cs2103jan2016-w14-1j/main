//@@author a0124946

package parser;

public class InvalidInputException extends Exception{
	//i honestly have no idea why serialver is needed lolol
	private static final long serialVersionUID = 1L;
	private String message = "Invalid input. Please try again.";

	public InvalidInputException() {

	}

	public InvalidInputException(String messageToUser) {
		this.message = messageToUser;
	}

	public void setMessage(String newMessage){
		this.message = newMessage;
	}

	public String getMessage() {
		return message;
	}
	
}

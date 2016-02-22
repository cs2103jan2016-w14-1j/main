package logic;

public class Task {
	private static final String SYMBOL_NULL = "";
	
    String eventDetails;
	String time;
	String date;
	
	public Task(){
		
	}
	
	public Task(String event){
		this.time = SYMBOL_NULL;
		this.date = SYMBOL_NULL;
		this.eventDetails = event;
	}
	
	public Task(String event, String givenDate){
		this(event);
		setDate(givenDate);
	}
	
	public Task(String event, String givenDate, String givenTime){
		this(event, givenDate);
		setTime(givenTime);
	}
	
	/****Getter****/
	public String getEvent(){
		return eventDetails;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getDate(){
		return date;
	}
	
	/****Setter****/
	public void setEvent(String event){
		this.eventDetails = event;
	}
	
	public void setTime(String inputTime){
		this.time = inputTime;
	}
	
	public void setDate(String inputDate){
		this.date = inputDate;
	}
}

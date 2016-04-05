package logic;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import parser.KeywordConstraints;

public class Task implements Cloneable, Comparable<Task> {
	private static final String SYMBOL_NULL = "";
	
	private boolean _changeDataFlag;
	private String _task;
	private String _cmd;
	private String _messageToUserSuccess;
	private String _messageToUserFail;
	private String _startDate;
	private String _startTime;
	private String _endDate;
	private String _endTime;
	
	private int operandIndex;
	private boolean isComplete;
	
	private static final int STARTINDEX = 0;
	private static final int ENDINDEX = 1;
	private static final int HAVEDATEANDTIME = 2;
	
	private static final int DATEINDEX = 0;
	private static final int TIMEINDEX = 1;
	private static final int HAVESTARTONLY = 1;
	private static final int HAVESTARTANDEND = 2;

	private static final int MAX_VALUE = Integer.MAX_VALUE;
	private static final int MIN_VALUE = Integer.MIN_VALUE;
	
	public Task (boolean isChanged, String userInput, String cmd
				, String successMsg,String failMsg){
		set_changeDataFlag(isChanged);
		set_task(userInput);
		set_cmd(cmd);
		set_messageToUserSuccess(successMsg);
		set_messageToUserFail(failMsg);
		set_startTime(SYMBOL_NULL);
		set_startDate(SYMBOL_NULL);
		set_endDate(SYMBOL_NULL);
		set_endTime(SYMBOL_NULL);
	}
	
	public Task(boolean isChanged, String userInput, String cmd
				, String successMsg, String failMsg
				, ArrayList<ArrayList<String>> dateTimeArgs){
		this(isChanged, userInput, cmd, successMsg, failMsg);
		
		setDateTimeForTask(dateTimeArgs);
	}
	
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Task){
			Task other = (Task)obj;
			return(isEqualCmd(other) 
					&& isEqualMsgFail(other)
					&& isEqualMsgSuccess(other)
					&& isEqualTask(other) 
					&& isSuccesful(other)
					&& isSameStartDate(other)
					&& isSameEndDate(other)
					&& isSameStartTime(other)
					&& isSameEndTime(other)
					&& isSameCompletionState(other));
		}
		return false;
	}
	
	public Task cloneOf(){
		Task newTask;
		try{
			newTask = (Task) this.clone();
		} catch(CloneNotSupportedException e) {
			newTask = new Task(this._changeDataFlag
									,this._task , this._cmd
									,this._messageToUserSuccess
									,this._messageToUserFail);
			newTask.set_startDate(this._startDate);
			newTask.set_endDate(this._endDate);
			newTask.set_startTime(this._startTime);
			newTask.set_endTime(this._endTime);
			
		}
		return newTask;
	}
	
	public void editWith(Task Other){
		if(Other.get_task() != SYMBOL_NULL){
			this.set_task(Other.get_task());
		}

		if(Other.get_startTime() != SYMBOL_NULL){
			this.set_startTime(Other.get_startTime());
		}
		
		if(Other.get_startDate() != SYMBOL_NULL){
			this.set_startDate(Other.get_startDate());
		}
		
		if(Other.get_endTime() != SYMBOL_NULL){
			this.set_endTime(Other.get_endTime());
		}
		
		if(Other.get_endDate() != SYMBOL_NULL){
			this.set_endDate(Other.get_endDate());
		}
		if(Other.get_completionState()){
			this.set_Complete();
		} else {
			this.set_Incomplete();
		}
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int compareTo(Task Other) {
		int result = 0;
		String thisStart = this.get_startDate();
		String otherStart = Other.get_startDate();
		
		if(thisStart != SYMBOL_NULL && otherStart == SYMBOL_NULL){
			result = -1;
		} else if(thisStart == SYMBOL_NULL && otherStart != SYMBOL_NULL){
			result = 1;
		} else if(thisStart != SYMBOL_NULL && otherStart != SYMBOL_NULL ){
			result = isBeforeOrAfter(Other);
		} else {
			result = this.get_task().compareTo(Other.get_task());
		}

		System.out.println(this.get_task() + " comparing against " + Other.get_task());
		System.out.println("Result = " + result);
		return result;
	}
	
	public boolean isEqualCmd(Task other){
		return (other.get_cmd() == this._cmd);
	}
	
	public boolean isEqualMsgSuccess(Task other){
		return (other.get_messageToUserSuccess() == this._messageToUserSuccess);
	}

	public boolean isEqualMsgFail(Task other){
		return (other.get_messageToUserFail() == this._messageToUserFail);
	}
	
	public boolean isEqualTask(Task other){
		return (other.get_task() == this._task);
	}
	
	public boolean isSuccesful(Task other){
		return (other.is_changed() == this._changeDataFlag);
	}
	
	public boolean isSameStartDate(Task other){
		String otherDate = other.get_startDate();
		String thisDate = this.get_startDate();
		return isSameString(otherDate, thisDate);
	}
	
	public boolean isSameEndDate(Task other){
		String otherDate = other.get_endDate();
		String thisDate = this.get_endDate();
		return isSameString(otherDate, thisDate);
	}
	
	public boolean isSameStartTime(Task other){
		String otherTime = other.get_startTime();
		String thisTime = this.get_startTime();
		return isSameString(otherTime, thisTime);
	}
	
	public boolean isSameEndTime(Task other){
		String otherTime = other.get_endTime();
		String thisTime = this.get_endTime();
		return isSameString(otherTime, thisTime);
	}	
	private boolean isSameString(String otherString, String thisString){
		if(otherString != SYMBOL_NULL && thisString != SYMBOL_NULL){
			return otherString.equals(thisString);
		} else if (otherString == SYMBOL_NULL && thisString == SYMBOL_NULL){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isSameCompletionState(Task other){
		boolean otherState = other.get_completionState();
		boolean thisState = this.get_completionState();
		return thisState == otherState;
	}
	
	private int isBeforeOrAfter(Task Other){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");
		DateTime thisDate = formatter.parseDateTime(this.get_startDate());
		DateTime otherDate = formatter.parseDateTime(Other.get_startDate());
		int result = 0;
		result = thisDate.compareTo(otherDate);
		
		if(result == 0){
			if(this.get_startTime() != SYMBOL_NULL && Other.get_startTime() != SYMBOL_NULL){
				DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("h.mma");
				DateTime thisTime = timeFormatter.parseDateTime(this.get_startTime());
				DateTime otherTime = timeFormatter.parseDateTime(Other.get_startTime());
				result = thisTime.compareTo(otherTime);
			}
		}
		return result;
	}
	
	private boolean isSameOperand(Task other){
		int otherIndex = other.get_index();
		int thisIndex = this.get_index();
		return thisIndex == otherIndex;
	}
		
	/************GETTERS***********/
	public boolean is_changed() {
		return _changeDataFlag;
	}

	public String get_cmd() {
		return _cmd;
	}

	public String get_task() {
		return _task;
	}
	
	public String get_messageToUserSuccess() {
		return _messageToUserSuccess;
	}
	
	public String get_messageToUserFail() {
		return _messageToUserFail;
	}
	
	public String get_startDate() {
		return _startDate;
	}

	public String get_startTime() {
		return _startTime;
	}
	
	public String get_endTime(){
		return _endTime;
	}
	
	public String get_endDate(){
		return _endDate;
	}
	
	public int get_index(){
		return operandIndex;
	}
	
	public boolean get_completionState(){
		return isComplete;
	}

	/************SETTERS***********/
	public void set_task(String _task) {
		this._task = _task;
	}

	public void set_changeDataFlag(boolean _changes) {
		this._changeDataFlag = _changes;
	}
	
	public void set_cmd(String _cmd) {
		this._cmd = _cmd;
	}
	
	public void set_messageToUserSuccess(String _messageToUserSuccess) {
		this._messageToUserSuccess = _messageToUserSuccess;
	}

	public void set_messageToUserFail(String _messageToUserFail) {
		this._messageToUserFail = _messageToUserFail;
	}
	
	public void set_startDate(String date1) {
		this._startDate = date1;
	}

	public void set_startTime(String time1) {
		this._startTime = time1;
	}

	public void set_endDate(String date2) {
		this._endDate = date2;
	}

	public void set_endTime(String time2) {
		this._endTime = time2;
	}
	
	public void set_index(int newIndex){
		this.operandIndex = newIndex;
	}
	
	public void set_Complete(){
		this.isComplete = true;
	}
	
	public void set_Incomplete(){
		this.isComplete = false;
	}
	
	public void setMessageErrorCustom(String customErrorMsg){
		this._messageToUserFail = customErrorMsg;
	}
	
	public void setDateTimeForTask(ArrayList<ArrayList<String>> dateTimeArgs){
		ArrayList<String> dateArgs = dateTimeArgs.get(DATEINDEX);
		ArrayList<String> timeArgs = dateTimeArgs.get(TIMEINDEX);

		if(dateArgs.size() == HAVESTARTONLY){
			this.set_startDate(dateArgs.get(STARTINDEX));
		} else if (dateArgs.size() == HAVESTARTANDEND){
			this.set_startDate(dateArgs.get(STARTINDEX));
			this.set_endDate(dateArgs.get(ENDINDEX));	
		}
		
		if(timeArgs.size() == HAVESTARTONLY){
			this.set_startTime(timeArgs.get(STARTINDEX));
		} else if (timeArgs.size() == HAVESTARTANDEND){
			this.set_startTime(timeArgs.get(STARTINDEX));
			this.set_endTime(timeArgs.get(ENDINDEX));	
		}
	}

}

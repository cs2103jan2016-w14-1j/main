package logic;

public class Task {
	private static final String SYMBOL_NULL = "";
	
	private boolean _changeDataFlag;
	private String _task;
	private String _cmd;
	private String _messageToUser;
	private String _date;
	private String _time;
	
	private int operandIndex;
	private boolean isComplete;
	
	public Task(boolean isChanged, String userInput, String cmd, String msg){
		set_changeDataFlag(isChanged);
		set_task(userInput);
		set_cmd(cmd);
		set_messageToUser(msg);
		set_time(SYMBOL_NULL);
		set_date(SYMBOL_NULL);
	}
	
	public Task(boolean isChanged, String userInput, String cmd, String msg, String date){
		this(isChanged, userInput, cmd, msg);
		set_date(date);
	}
	
	public Task(boolean isChanged, String userInput, String cmd, String msg, String time, String date){
		this(isChanged, userInput, cmd, msg);
		set_date(date);
		set_time(time);
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Task){
			Task other = (Task)obj;
			return(isEqualCmd(other) 
					&& isEqualMsg(other) 
					&& isEqualTask(other) 
					&& isSuccesful(other)
					&& isSameDate(other)
					&& isSameTime(other)
					&& isSameCompletionState(other));
		}
		return false;
	}
	

	public boolean isEqualCmd(Task other){
		return (other.get_cmd() == this._cmd);
	}
	
	public boolean isEqualMsg(Task other){
		return (other.get_messageToUser() == this._messageToUser);
	}
	
	public boolean isEqualTask(Task other){
		return (other.get_task() == this._task);
	}
	
	public boolean isSuccesful(Task other){
		return (other.is_changed() == this._changeDataFlag);
	}
	
	public boolean isSameDate(Task other){
		String otherDate = other.get_date();
		String thisDate = this.get_date();
		return isSameString(otherDate, thisDate);
	}
	
	public boolean isSameTime(Task other){
		String otherTime = other.get_time();
		String thisTime = this.get_time();
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
		return thisState==otherState;
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
	
	public String get_messageToUser() {
		return _messageToUser;
	}

	public String get_date() {
		return _date;
	}

	public String get_time() {
		return _time;
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
	
	public void set_messageToUser(String _messageToUser) {
		this._messageToUser = _messageToUser;
	}

	public void set_date(String _date) {
		this._date = _date;
	}

	public void set_time(String _time) {
		this._time = _time;
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

	public void setMessageErrorDefault(){
		//Method to be implemented in all concrete classes
	}
	
	public void setMessageErrorEmpty(){
		//ditto. Man I need to write better comments.
	}
	
	public void setMessageErrorCustom(String customErrorMsg){
		set_messageToUser(customErrorMsg);
	}
	
	
}

package storage;

public class Output {
	//Returns something from executed commands.
	private boolean _success;
	private String _task;
	private String _cmd;
	private String _messageToUser;
	
	public Output(boolean status, String userInput, String cmd, String msg){
		set_success(status);
		set_task(userInput);
		set_cmd(cmd);
		set_messageToUser(msg);
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Output){
			Output other = (Output)obj;
			return(isEqualCmd(other) 
					&& isEqualMsg(other) 
					&& isEqualTask(other) 
					&& isSuccesful(other));
		}
		return false;
	}
	
	public boolean isEqualCmd(Output other){
		return (other.get_cmd() == this._cmd);
	}
	
	public boolean isEqualMsg(Output other){
		return (other.get_messageToUser() == this._messageToUser);
	}
	
	public boolean isEqualTask(Output other){
		return (other.get_task() == this._task);
	}
	
	public boolean isSuccesful(Output other){
		return (other.is_success() == this._success);
	}
	/************GETTERS***********/
	public boolean is_success() {
		return _success;
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

	/************SETTERS***********/
	public void set_task(String _task) {
		this._task = _task;
	}

	public void set_success(boolean _success) {
		this._success = _success;
	}
	
	public void set_cmd(String _cmd) {
		this._cmd = _cmd;
	}
	
	public void set_messageToUser(String _messageToUser) {
		this._messageToUser = _messageToUser;
	}

}

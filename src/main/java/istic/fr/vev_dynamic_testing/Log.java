package istic.fr.vev_dynamic_testing;

import com.google.gson.Gson;

public class Log {

	public enum IO {
		BEGIN,
		END,
		DECLARING
	}

	public enum TYPE {
		BLOCK,
		METHOD,
		CONSTRUCTOR
	}

	private IO io;
	private TYPE type;
	private String message;
	
	public Log(IO io, TYPE type,String message) {
		this.io = io;
		this.type = type;
		this.message = message;
	}

	public IO getIo() {
		return io;
	}

	public TYPE getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String toCallableType() {
		return "\""+io+"\",\""+type+"\",\""+message+"\"";
	}

	public String toStatement() {
		return "Logs.getInstance().addLogs("+toCallableType()+");";
	}

	public String toString() {
		return new Gson().toJson(this);
	}

	public boolean isBeginBlock() {
		return (getIo() == IO.BEGIN) && (getType() == TYPE.BLOCK);
	}

	public boolean isBeginMethod() {
		return (getIo() == IO.BEGIN) && (getType() == TYPE.METHOD);
	}

	public boolean isDeclaringBlock() {
		return (getIo() == IO.DECLARING) && (getType() == TYPE.BLOCK);
	}

}

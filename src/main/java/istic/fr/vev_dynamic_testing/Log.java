package istic.fr.vev_dynamic_testing;

import com.google.gson.Gson;
import org.apache.maven.shared.invoker.SystemOutHandler;

import java.util.Arrays;
import java.util.function.Consumer;

public class Log {

	public enum IO {
		BEGIN,
		END,
		DECLARING,
		CALLING
	}

	public enum TYPE {
		BLOCK,
		METHOD,
		CONSTRUCTOR
	}

	private IO io;
	private TYPE type;
	private String message;
	private String parameters = "";
	
	public Log(IO io, TYPE type,String message) {
		this.io = io;
		this.type = type;
		this.message = message;
	}

	public Log(String io, String type, String message) {
		this.io = IO.valueOf(io);
		this.type = TYPE.valueOf(type);
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

	public Log parameters(Object[] parameters) {
		System.out.println(parameters);
		return this;
	}

	private String paren(Object s) {
		return "\"" + s + "\"";
	}

	public String toCallableType() {
		return String.join(", ", paren(io), paren(type), paren(message));
		//return String.join(", ", paren(io), paren(type), paren(message)) + ", $args";
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

	public boolean isCallingMethod() {
		return (getIo() == IO.CALLING) && (getType() == TYPE.METHOD);
	}

}

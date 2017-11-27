package istic.fr.vev_dynamic_testing;

public class Log {
	
	private String type;
	private String message;
	
	Log(String type, String message) {
		this.type = type;
		this.message = message;
	}

	public String toStatement() {
		return "Logs.getInstance().addLogs(\""+type+"\", \""+message+"\");";
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String m) {
		message = m;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public String toString() {
		
		String ret = "";
		
		ret = "Type="+type+" : message="+message;
		
		return ret;
	}

}

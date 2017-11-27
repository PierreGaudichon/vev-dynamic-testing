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
	public String toString() {
		
		String ret = "";
		
		ret = "Type="+type+" : message="+message;
		
		return ret;
	}

}

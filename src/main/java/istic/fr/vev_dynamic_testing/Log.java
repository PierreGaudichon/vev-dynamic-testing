package istic.fr.vev_dynamic_testing;

public class Log {
	
	private String type;
	private String message;
	
	Log(String type, String message) {
		this.setType(type);
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		
		String ret = "";
		
		ret = "Type="+type+" : message="+message;
		
		return ret;
	}

}

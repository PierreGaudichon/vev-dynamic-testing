package istic.fr.vev_dynamic_testing;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Logs {
	
	// liste des logs
	List<Log> lesLogs;
	
	/** Constructeur privé */
	private Logs() {
		lesLogs = new ArrayList<Log>();
	}
 
	/** Instance unique non préinitialisée */
	private static Logs INSTANCE = null;
 
	/** Point d'accès pour l'instance unique du singleton */
	public static Logs getInstance()
	{			
		if (INSTANCE == null) {
			INSTANCE = new Logs();
		}
		return INSTANCE;
	}
	
	public void removeLogs() {
		lesLogs = new ArrayList<Log>();
	}
	
	public void addLogs(String io, String type, String message) {
		lesLogs.add(new Log(Log.IO.valueOf(io), Log.TYPE.valueOf(type), message));
	}
	
	public List<Log> getLogs() {
		return lesLogs;
	}
	
	public String toString() {
		
		String ret = "";
		
		Iterator<Log> it = lesLogs.iterator();
		
		while(it.hasNext()) {
			Log l = it.next();
			ret = ret + l.toString() + "\n";
		}
		
		return ret;
	}
}
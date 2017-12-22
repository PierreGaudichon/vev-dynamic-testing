package istic.fr.vev_dynamic_testing;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Logs {

	public static String toString(List<Log> logs) {
		List<String> ls = logs.stream().map(Log::toString).collect(Collectors.toList());
		return "[\n" +String.join(",\n", ls) + "\n]";
	}

	public static String getClassifiedName() {
		return "istic.fr.vev_dynamic_testing.Logs";
	}
	
	// liste des logs
	private List<Log> lesLogs;
	
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
	
	public Logs removeLogs() {
		lesLogs = new ArrayList<Log>();
		return this;
	}

	public void addLogs(Log log) {
		lesLogs.add(log);
	}
	
	public void addLogs(String io, String type, String message) {
		addLogs(new Log(io, type, message));
	}

	/*public void addLogs(String io, String type, String message, Object[] args) {
		addLogs(new Log(io, type, message).parameters(args));
	}*/
	
	public List<Log> getLogs() {
		return lesLogs;
	}
	
	public String toString() {
		return Logs.toString(getLogs());
	}

}
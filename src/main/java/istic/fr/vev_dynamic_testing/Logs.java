package istic.fr.vev_dynamic_testing;

public class Logs {
	
	// le résultat du String
	private String resultat;
	
	/** Constructeur privé */
	private Logs() {
		this.resultat = "";
	}
 
	/** Instance unique non préinitialisée */
	private static Logs INSTANCE = null;
 
	/** Point d'accès pour l'instance unique du singleton */
	public static Logs getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new Logs();	
		}
		return INSTANCE;
	}
	
	public void addLogs(String newLog) {
		resultat = resultat + newLog + "\n";
	}
	
	public String getResultat() {
		return resultat;
	}
}
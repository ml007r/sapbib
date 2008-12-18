package BIB_SAP_CONNECT;
/**
 * JCoDemoConPool.java
 */

import com.sap.mw.jco.*;

/**
 * Demonstrationsklasse zur Nutzung eines RFC-fähigen ABAP-Funktionsbausteines
 * mit JCo.
 * 
 * Den korrespondierenden Funktionsbaustein "Z_dev044_CALC_VAT" finden Sie im
 * SAP-System G23 unter dem Paket Z_dev044_RFC_DEMO.
 * 
 * @version 0.4
 */
public class JCoDemoConPool {

	/**
	 * Speichert eine eindeutige Kennzeichnung für den Connection-Pool zu dem
	 * jeweiligen SAP-System
	 */
	private String conPoolId;

	/**
	 * Speichert eine Referenz auf die Repository-Schnittstelle des angebundenen
	 * SAP-Systems
	 */
	private IRepository repository;

	/**
	 * Der Std.-Konstruktor; Er Nutzt die Connection-Pool-Kennzeichnung
	 * "StdConPoolId" zur Initialisierung des Objekts.
	 */
	public JCoDemoConPool() {
		this("StdConPoolId");
	}

	/**
	 * Der typische Konstruktor.
	 * 
	 * @param conPoolId
	 *            eine eindeutige Kennzeichnung für den Connection-Pool
	 */
	public JCoDemoConPool(String conPoolId) {
		this.conPoolId = conPoolId;
	}

	/**
	 * Es wird ein Connection-Pool für die Verbindung zum SAP-System
	 * eingerichtet und anschließend ein Referenz zu dem "Repository" des
	 * SAP-Systems angefordert.
	 * 
	 * @throws JCO.Exception
	 */
	protected void erstelleVerbindungsPool() throws JCO.Exception {

		// Erstellen des Connection-Pools
		JCO.addClientPool(this.conPoolId, // Kennzeichner für den Pool
				5, // Max. Anzahl der Verbindungen
				"900", // SAP Mandant
				"dev156", // Benutzername
				"34561697", // Kennwort
				"DE", // Sprache
				"hcc3v00.informatik.tu-muenchen.de", // Hostname
				"00" // System-Nummer
		);

		/*
		 * Erstellen einer Repository-Referenz zu dem SAP-System am
		 * Connection-Pool "this.conPoolId"
		 */
		repository = JCO.createRepository("DemoRepository", this.conPoolId);
		
		JCO.Pool pool = JCO.getClientPoolManager().getPool(conPoolId);
		if (pool == null) 
		{
			System.out.println("Kein Pool vorhanden");
		}
		else
		{
			System.out.println("PoolCurrentSize: " +pool.getCurrentPoolSize());
			System.out.println("PoolMaxSize: " +pool.getMaxPoolSize());
			System.out.println("PoolMaxConnections: " +pool.getMaxConnections());
			System.out.println("PoolName: " +pool.getName());
			System.out.println("PoolTimeOutCheckPeriod: " +pool.getTimeoutCheckPeriod());
			System.out.println("PoolCurrentSize: " +pool.getCurrentPoolSize());
		}
		
	}

	/**
	 * Es werden die durch den Connection-Pool belegten Ressourcen freigeben und
	 * der Pool "this.conPoolId" mit allen Verbindungen geschlossen.
	 */
	protected void schliesseVerbindungsPool() {

		JCO.removeClientPool(this.conPoolId);
	}

	/**
	 * Aufruf der Demofunktion "Z_dev044_Aufgabe_6_1"
	 * 
	 * @param VORLESUNGSNAME
	 *            ein Wert für den Input-Parameter "VORLESUNGSNAME"
	 */
	public String calcVat(int i) throws Exception {

		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("Z_DEV156_KUNDE_SCHREIBEN");

		if (ftemplate == null)
			throw new Exception("Funktionstemplate nicht gefunden");

		/*
		 * Eine entsprechende "JCo-Funktion" aufgrund des Templates erzeugen
		 */
		JCO.Function function = ftemplate.getFunction();

		/*
		 * Die Funktionsparameter (IMPORT) der festlegen
		 */
		JCO.ParameterList input = function.getImportParameterList();

		input.setValue(123456, "KUNDENNAME");
		input.setValue(i, "ORT");
		input.setValue(i, "PLZ");
		input.setValue(i, "STRASSE");
		input.setValue(i, "HAUSNUMMER");
		input.setValue(i, "TELEFONNR");

		/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);

			// Den EXPORT Wert "RETURN" als Double zurückgeben

			String ausgabe = "";

			ausgabe += function.getExportParameterList().getString("HINZU");
//			ausgabe += " " + function.getExportParameterList().getString("TITEL");
//			ausgabe += " " + function.getExportParameterList().getString("AUTOR");
//			ausgabe += " " + function.getExportParameterList().getString("BESCHREIBUNG");
//			ausgabe += " " +function.getExportParameterList().getString("BEILAGE");
//			ausgabe += " " + function.getExportParameterList().getString("VERLAG");

			return ausgabe;

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
	}

	/**
	 * Der Einstiegspunkt zu dieser JCo Demoanwendung.
	 * 
	 * @param argv
	 *            nicht verwendet
	 */
	public static void main(String[] argv) {

		// eine Instanz der Demo-Klasse erstellen
		JCoDemoConPool jcd = new JCoDemoConPool();

		/*
		 * eine Verbindung zum SAP-System per Connection-Pool einrichten und
		 * eine Referenz zum Repository des SAP-Systems anfordern
		 */
		jcd.erstelleVerbindungsPool();

		try {

			System.out.println("VL DBI findet statt: " + jcd.calcVat(1));
			
			
		} catch (Exception ex) {

			System.out.println("Leider wurde eine Exception geworfen: \n" + ex);
		} finally {

			/*
			 * den Connection-Pool schliessen und die Verbindung zum SAP-System
			 * beenden
			 */
			
			JCO.Pool pool = JCO.getClientPoolManager().getPool("StdConPoolId");
			if (pool == null) 
			{
				System.out.println("Kein Pool vorhanden");
			}
			else
			{
				System.out.println("PoolCurrentSize: " +pool.getCurrentPoolSize());
				System.out.println("PoolMaxSize: " +pool.getMaxPoolSize());
				System.out.println("PoolMaxConnections: " +pool.getMaxConnections());
				System.out.println("PoolName: " +pool.getName());
				System.out.println("PoolTimeOutCheckPeriod: " +pool.getTimeoutCheckPeriod());
				System.out.println("PoolCurrentSize: " +pool.getCurrentPoolSize());
			}
		
			
			
			jcd.schliesseVerbindungsPool();
		}
	}
}

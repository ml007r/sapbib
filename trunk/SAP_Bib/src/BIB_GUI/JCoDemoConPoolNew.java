package BIB_GUI;
/**
 * JCoDemoConPool.java
 */

import java.util.ArrayList;
import java.util.Date;

import com.sap.mw.jco.*;
import com.sap.mw.jco.JCO.Record;
import com.sap.mw.jco.JCO.Table;

import BIB_Modell.Verleih;
import BIB_Modell.Buch;
import BIB_Modell.Leser;

/**
 * Demonstrationsklasse zur Nutzung eines RFC-fähigen ABAP-Funktionsbausteines
 * mit JCo.
 * 
 * Den korrespondierenden Funktionsbaustein "Z_dev044_CALC_VAT" finden Sie im
 * SAP-System G23 unter dem Paket Z_dev044_RFC_DEMO.
 * 
 * @version 0.4
 */
public class JCoDemoConPoolNew {


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
	public JCoDemoConPoolNew() {
		this("StdConPoolId");
	}

	/**
	 * Der typische Konstruktor.
	 * 
	 * @param conPoolId
	 *            eine eindeutige Kennzeichnung für den Connection-Pool
	 */
	public JCoDemoConPoolNew(String conPoolId) {
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
	public String calcVat() throws Exception {

		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_BUCH_ALLE_LESEN");

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

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);

			// Den EXPORT Wert "RETURN" als Double zurückgeben

			String ausgabe = "";

			Table x = function.getTableParameterList().getTable("BUCH");
			x.firstRow();
			
			x.getField("TITEL");
			
			//Record rec = x.;
			
			//ausgabe += x.getValue(1);
			ausgabe += " " + x.getField("TITEL").getString();
			
			x.nextRow();
			ausgabe += " " + x.getField("TITEL").getString();
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
	 * Aufruf der Demofunktion "Z_dev044_Aufgabe_6_1"
	 * 
	 * @param VORLESUNGSNAME
	 *            ein Wert für den Input-Parameter "VORLESUNGSNAME"
	 */
	public ArrayList<Buch> oeffneBuch() throws Exception {

		ArrayList<Buch> buch = new ArrayList();
		
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_BUCH_ALLE_LESEN");

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
		JCO.ParameterList export = function.getExportParameterList();

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);

			// Den EXPORT Wert "RETURN" als Double zurückgeben

			Table x = function.getTableParameterList().getTable("BUCH");
			System.out.println("letztes Buch: " + export.getField("LETZTES").getInt());
			Buch.setAnzahlBuecher(export.getField("LETZTES").getInt());
			System.out.println(x);
			x.firstRow();
			if(x.getField("VERLEIHSTATUS").getChar() != 'X'){
				buch.add(new Buch(x.getField("ID").getInt(), x.getField("ISBN").getString(),x.getField("TITEL").getString(),x.getField("AUTOR").getString(),x.getField("BESCHREIBUNG").getString(),x.getField("VERLAG").getString(), true));
			}
			else{ 
				buch.add(new Buch(x.getField("ID").getInt(), x.getField("ISBN").getString(),x.getField("TITEL").getString(),x.getField("AUTOR").getString(),x.getField("BESCHREIBUNG").getString(),x.getField("VERLAG").getString(),false));
				System.out.println("verliehenes Buch");
			}
			while(x.nextRow()){
			if(x.getField("VERLEIHSTATUS").getChar() != 'X'){
				buch.add(new Buch(x.getField("ID").getInt(), x.getField("ISBN").getString(),x.getField("TITEL").getString(),x.getField("AUTOR").getString(),x.getField("BESCHREIBUNG").getString(),x.getField("VERLAG").getString(),true));
			}
			else{
				buch.add(new Buch(x.getField("ID").getInt(), x.getField("ISBN").getString(),x.getField("TITEL").getString(),x.getField("AUTOR").getString(),x.getField("BESCHREIBUNG").getString(),x.getField("VERLAG").getString(), false));
				System.out.println("verliehenes Buch");
			}
			Buch.setAnzahlBuecher(export.getField("LETZTES").getInt()+1);
			System.out.println("letztes Buch: " + export.getField("LETZTES").getInt());
			}
//			

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		return buch;
		
	}
	/**
	 * Aufruf der Demofunktion "Z_dev044_Aufgabe_6_1"
	 * 
	 * @param VORLESUNGSNAME
	 *            ein Wert für den Input-Parameter "VORLESUNGSNAME"
	 */
	public ArrayList<Leser> oeffneLeser() throws Exception {

		ArrayList<Leser> leser = new ArrayList();
		
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_LESER_ALLE_LESEN");

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
		JCO.ParameterList export = function.getExportParameterList();

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);

			// Den EXPORT Wert "RETURN" als Double zurückgeben
			
			Table x = function.getTableParameterList().getTable("LESER");
			System.out.println(x);
			x.firstRow();
			leser.add(new Leser(x.getField("ID").getInt(),x.getField("VORNAME").getString(),x.getField("NAME").getString(),x.getField("STRASSE").getString(),x.getField("PLZ").getString(),x.getField("ORT").getString()));
			while(x.nextRow()){
				leser.add(new Leser(x.getField("ID").getInt(),x.getField("VORNAME").getString(),x.getField("NAME").getString(),x.getField("STRASSE").getString(),x.getField("PLZ").getString(),x.getField("ORT").getString()));
			}
			Leser.setAnzahlLeser(export.getField("LETZTER").getInt()+1);
			System.out.println("Letzter Leser:" + export.getField("LETZTER").getInt());

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		return leser;
		
	}
	/**
	 * Aufruf der Demofunktion "Z_dev044_Aufgabe_6_1"
	 * 
	 * @param VORLESUNGSNAME
	 *            ein Wert für den Input-Parameter "VORLESUNGSNAME"
	 */
	public ArrayList<Verleih> oeffneVerleih() throws Exception {

		ArrayList<Verleih> verleih = new ArrayList();
		
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_VERLEIH_ALLE_LESEN");

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
		JCO.ParameterList export = function.getExportParameterList();

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);

			// Den EXPORT Wert "RETURN" als Double zurückgeben
			
			Table x = function.getTableParameterList().getTable("VERLEIH");
			System.out.println(x);
			Verleih.setAnzahlVerleihen(export.getField("LETZTER").getInt()+1);
			x.firstRow();
			verleih.add(new Verleih(x.getField("ID").getInt(), x.getField("AUSLEIH_DATUM").getString(), x.getField("RUECKGABE_DATUM").getString(),
					x.getField("LESER_ID").getInt(),x.getField("BUCH_ID").getInt()));
			while(x.nextRow()){
				verleih.add(new Verleih(x.getField("ID").getInt(), x.getField("AUSLEIH_DATUM").getString(), x.getField("RUECKGABE_DATUM").getString(),
						x.getField("LESER_ID").getInt(),x.getField("BUCH_ID").getInt()));
			}
			

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		return verleih;
		
	}
	/**
	 * Aufruf der Demofunktion "Z_dev044_Aufgabe_6_1"
	 * 
	 * @param VORLESUNGSNAME
	 *            ein Wert für den Input-Parameter "VORLESUNGSNAME"
	 */
	public void schreibeLeser(Leser les) throws Exception {

		
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_LESER_SCHREIBEN");

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

		input.setValue(les.getId(), "ID");
		input.setValue(les.getVorname(), "VORNAME");
		input.setValue(les.getNachname(), "NAME");
		input.setValue(les.getStrasse(), "STRASSE");
		input.setValue(les.getPlz(), "PLZ");
		input.setValue(les.getOrt(), "ORT");

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_LESER_SCHREIBEN");

			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		
	}
	
	/**
	 * Aufruf der Demofunktion "Z_dev044_Aufgabe_6_1"
	 * 
	 * @param VORLESUNGSNAME
	 *            ein Wert für den Input-Parameter "VORLESUNGSNAME"
	 */
	public void schreibeBuch(Buch buch) throws Exception {

		
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_BUCH_SCHREIBEN");

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

		input.setValue(buch.getId(), "ID");
		input.setValue(buch.getAutor(), "AUTOR");
		input.setValue(buch.getBeschreibung(), "BESCHREIBUNG");
		input.setValue(buch.getIsbn(), "ISBN");
		input.setValue(buch.getTitel(), "TITEL");
		input.setValue(buch.getVerlag(), "VERLAG");

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_Buch_SCHREIBEN");

			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		
	}
	

	
	public void loescheLeser(int i) {
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_LESER_LOESCHEN");

		if (ftemplate == null)
			try {
				throw new Exception("Funktionstemplate nicht gefunden");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		/*
		 * Eine entsprechende "JCo-Funktion" aufgrund des Templates erzeugen
		 */
		JCO.Function function = ftemplate.getFunction();

		/*
		 * Die Funktionsparameter (IMPORT) der festlegen
		 */
		JCO.ParameterList input = function.getImportParameterList();

		input.setValue(i, "ID");
		

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_LESER_SCHREIBEN");

			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
	}
	/**
	 * Aufruf der Demofunktion "Z_dev044_Aufgabe_6_1"
	 * 
	 * @param VORLESUNGSNAME
	 *            ein Wert für den Input-Parameter "VORLESUNGSNAME"
	 */
	public void aendernLeser(Leser les) throws Exception {

		
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_LESER_AENDERN");

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
		input.setValue(les.getId(), "ID");
		input.setValue(les.getVorname(), "VORNAME");
		input.setValue(les.getNachname(), "NAME");
		input.setValue(les.getStrasse(), "STRASSE");
		input.setValue(les.getPlz(), "PLZ");
		input.setValue(les.getOrt(), "ORT");

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_LESER_SCHREIBEN");

			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		
	}

	public int letzterLeser() {
		int i;
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_LESER_letzter");

		if (ftemplate == null)
			try {
				throw new Exception("Funktionstemplate nicht gefunden");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		/*
		 * Eine entsprechende "JCo-Funktion" aufgrund des Templates erzeugen
		 */
		JCO.Function function = ftemplate.getFunction();

		/*
		 * Die Funktionsparameter (IMPORT) der festlegen
		 */
		JCO.ParameterList export = function.getExportParameterList();
		
				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_LESER_letzter");
			i = export.getField("letzter").getInt();
			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		return i;
	}
	
	public int letztesBuch() {
		int i;
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_BUCH_LETZTES");

		if (ftemplate == null)
			try {
				throw new Exception("Funktionstemplate nicht gefunden");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		/*
		 * Eine entsprechende "JCo-Funktion" aufgrund des Templates erzeugen
		 */
		JCO.Function function = ftemplate.getFunction();

		/*
		 * Die Funktionsparameter (IMPORT) der festlegen
		 */
		JCO.ParameterList export = function.getExportParameterList();
		
				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_Buch_letztes");
			i = export.getField("letztes").getInt();
			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		return i;
	}


	public void aendernBuch(Buch buch) {
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_BUCH_AENDERN");

		/*
		 * Eine entsprechende "JCo-Funktion" aufgrund des Templates erzeugen
		 */
		JCO.Function function = ftemplate.getFunction();

		/*
		 * Die Funktionsparameter (IMPORT) der festlegen
		 */
		JCO.ParameterList input = function.getImportParameterList();
		input.setValue(buch.getId(), "ID");
		input.setValue(buch.getAutor(), "AUTOR");
		input.setValue(buch.getBeschreibung(), "BESCHREIBUNG");
		input.setValue(buch.getIsbn(), "ISBN");
		input.setValue(buch.getTitel(), "TITEL");
		input.setValue(buch.getVerlag(), "VERLAG");

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_BUCH_AENDERN");

			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
	}

	public void loescheBuch(int id) {
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_BUCH_LOESCHEN");

		if (ftemplate == null)
			try {
				throw new Exception("Funktionstemplate nicht gefunden");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		/*
		 * Eine entsprechende "JCo-Funktion" aufgrund des Templates erzeugen
		 */
		JCO.Function function = ftemplate.getFunction();

		/*
		 * Die Funktionsparameter (IMPORT) der festlegen
		 */
		JCO.ParameterList input = function.getImportParameterList();

		input.setValue(id, "ID");
		

				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_BUCH_SCHREIBEN");

			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
	}

	public void schreibeVerleih(Verleih hilfsVerleih) {
		JCO.Client client = null;

		/*
		 * Die Schnittstellen-Beschreibung der gewünschten RFC-Funktion als
		 * Template beim Repository anfordern
		 */
		IFunctionTemplate ftemplate = repository
				.getFunctionTemplate("ZZZ_VERLEIH_SCHREIBEN");

		if (ftemplate == null)
			try {
				throw new Exception("Funktionstemplate nicht gefunden");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		/*
		 * Eine entsprechende "JCo-Funktion" aufgrund des Templates erzeugen
		 */
		JCO.Function function = ftemplate.getFunction();

		/*
		 * Die Funktionsparameter (IMPORT) der festlegen
		 */
		JCO.ParameterList input = function.getImportParameterList();

		input.setValue(hilfsVerleih.getId(), "ID");
		input.setValue(hilfsVerleih.getDasBuch(), "BUCH_ID");
		input.setValue(hilfsVerleih.getDerLeser(), "LESER_ID");
		input.setValue(hilfsVerleih.getAusleihdatum(), "AUSLEIH_DATUM");
		input.setValue(hilfsVerleih.getRueckgabedatum(), "RUECKGABE_DATUM");
				/*
		 * Eine Serververbindung aus dem Connection-Pool als JCO.Client abrufen
		 */
		client = JCO.getClient(this.conPoolId);

		try {

			// Die Funktion synchron beim Remote-System ausführen
			client.execute(function);
			System.out.println("ZZZ_VERLEIH_SCHREIBEN");

			
					

		} finally {

			// Die Serververbindung an den Pool zurückgeben
			JCO.releaseClient(client);
		}
		
	}
}

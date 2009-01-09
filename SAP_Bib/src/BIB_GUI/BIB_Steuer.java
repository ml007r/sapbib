package BIB_GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sap.mw.jco.*;

import BIB_Modell.Ausleihe;
import BIB_Modell.Buch;
import BIB_Modell.Leser;


public class BIB_Steuer {
	
	private  ArrayList<Leser> alleLeser = new ArrayList<Leser>();
	private  ArrayList<Buch> alleBuecher = new ArrayList<Buch>();
	private  ArrayList<Ausleihe> alleAusleihen = new ArrayList<Ausleihe>();
	private JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
	
	
	public BIB_Steuer(){
		
	}

	public ArrayList<Leser> getAlleLeser() {
		return alleLeser;
	}

	public void setAlleLeser(ArrayList<Leser> alleLeser) {
		this.alleLeser = alleLeser;
	}

	public ArrayList<Buch> getAlleBuecher() {
		return alleBuecher;
	}

	public void setAlleBuecher(ArrayList<Buch> alleBuecher) {
		this.alleBuecher = alleBuecher;
	}

	public ArrayList<Ausleihe> getAlleAusleihen() {
		return alleAusleihen;
	}

	public void setAlleAusleihen(ArrayList<Ausleihe> alleAusleihen) {
		this.alleAusleihen = alleAusleihen;
	}
	
	// Methoden zur Pflege der Leser
	public void addLeser(String vorname, String nachname, String strasse,
														String plz, String ort){
		Leser hilfsLeser = new Leser(vorname, nachname, strasse,plz, ort);
		this.alleLeser.add(hilfsLeser);
		
			// eine Instanz der Demo-Klasse erstellen
			JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
			
			
			// eine Verbindung zum SAP-System per Connection-Pool einrichten und
			// eine Referenz zum Repository des SAP-Systems anfordern
			 
			jcd.erstelleVerbindungsPool();
			
			try {
				jcd.schreibeLeser(hilfsLeser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
		
				System.out.println("Schreiben des Lesers fehlgeschlagen!!!! " + e.getMessage());
			}
			finally{
				jcd.schliesseVerbindungsPool();
			}
		
	}
	public void removeLeser(int i){
		this.alleLeser.remove(i);
		// eine Instanz der Demo-Klasse erstellen
		JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
		System.out.println(i+ "removeLeser : i");
		
		// eine Verbindung zum SAP-System per Connection-Pool einrichten und
		// eine Referenz zum Repository des SAP-Systems anfordern
		 
		jcd.erstelleVerbindungsPool();
		
		try {
			jcd.loescheLeser(i+1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
	
			System.out.println("Schreiben des Lesers fehlgeschlagen!!!! " + e.getMessage());
		}
		finally{
			jcd.schliesseVerbindungsPool();
		}
		
	}
	public void removeAllLeser(Leser leser){
		this.alleLeser = null;
	}
	public Leser getLeser(int i){
		return this.alleLeser.get(i);
	}
	
	public void setLeser(int i,String vorname, String nachname, String strasse,
			String plz, String ort){
		// eine Instanz der Demo-Klasse erstellen
		JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
		System.out.println(i+ "setLeser : i");
		
		// eine Verbindung zum SAP-System per Connection-Pool einrichten und
		// eine Referenz zum Repository des SAP-Systems anfordern
		 
		jcd.erstelleVerbindungsPool();
		
		System.out.println(i + "setLeser: i");
		this.alleLeser.get(i).setNachname(nachname);
		this.alleLeser.get(i).setVorname(vorname);
		this.alleLeser.get(i).setStrasse(strasse);
		this.alleLeser.get(i).setPlz(plz);
		this.alleLeser.get(i).setOrt(ort);
		
		try {
			jcd.schreibeLeser(this.alleLeser.get(i));
		} catch (Exception e) {
			// TODO Auto-generated catch block
	
			System.out.println("Schreiben des Lesers fehlgeschlagen!!!! " + e.getMessage());
		}
		finally{
			jcd.schliesseVerbindungsPool();
		}
		
	}
	// Methoden zur Pflege der Buecher
	public void addBuch(String isbn, String titel, String autor,
			String beschreibung, String verlag){
		this.alleBuecher.add(new Buch(isbn, titel, autor,
			 beschreibung, verlag));
	}
	public void removeBuch(Buch buch){
		this.alleLeser.remove(buch);
	}
	public void removeAllBuecher(){
		this.alleBuecher = null;
	}
	
	// Methoden zur Pflege der Ausleihen
	public void addAusleihe(Ausleihe ausleihe){
		this.alleAusleihen.add(ausleihe);
	}
	public void removeAusleihe(Ausleihe ausleihe){
		this.alleAusleihen.remove(ausleihe);
	}
	public void removeAllAusleihe(){
		this.alleAusleihen = null;
	}
	public void oeffneDaten(){
		// eine Instanz der Demo-Klasse erstellen
		JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();

		/*
		 * eine Verbindung zum SAP-System per Connection-Pool einrichten und
		 * eine Referenz zum Repository des SAP-Systems anfordern
		 */
		jcd.erstelleVerbindungsPool();

		try {

			this.alleBuecher = jcd.oeffneBuch();
			this.alleLeser = jcd.oeffneLeser();
			Leser.setAnzahlLeser(this.alleLeser.size());
			
			
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

			
			
			jcd.schliesseVerbindungsPool();
		}
	}

}

package BIB_GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		this.alleLeser.add(new Leser(vorname, nachname, strasse,
				plz, ort));
	}
	public void removeLeser(Leser leser){
		this.alleLeser.remove(leser);
	}
	public void removeAllLeser(Leser leser){
		this.alleLeser = null;
	}
	public Leser getLeser(int i){
		return this.alleLeser.get(i);
	}
	public void setLeser(Leser les){
		this.alleLeser.remove(les.getId());
		this.alleLeser.add(les);
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

package BIB_GUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.sap.mw.jco.*;

import BIB_Modell.Verleih;
import BIB_Modell.Buch;
import BIB_Modell.Leser;


public class BIB_Steuer implements Runnable{
	
	private  ArrayList<Leser> alleLeser = new ArrayList<Leser>();
	private  ArrayList<Buch> alleBuecher = new ArrayList<Buch>();
	private  ArrayList<Verleih> alleVerleihen = new ArrayList<Verleih>();
	private JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
	Leser hilfsLeser;
	
	private static BIB_Steuer instance = null;
	  
	public static BIB_Steuer getInstance() {
	      if(instance == null) {
	         instance = new BIB_Steuer();
	      }
	      return instance;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


	
	protected BIB_Steuer(){	}

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
	
	
	public synchronized void removeLeser(Leser les){
		System.out.println(alleLeser);
		alleLeser.remove(les);
	
		// eine Instanz der Demo-Klasse erstellen
		JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
		System.out.println(les.getId()+ "removeLeser : i");
		
		// eine Verbindung zum SAP-System per Connection-Pool einrichten und
		// eine Referenz zum Repository des SAP-Systems anfordern
		 
		jcd.erstelleVerbindungsPool();
		
		try {
			jcd.loescheLeser(les.getId());
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
	public Leser getLeserByID(int i){
		Iterator<Leser> itr = alleLeser.iterator();
		 while (itr.hasNext()) {
			 Leser les = itr.next();
			 if(les.getId()== i){
				 return les; 
			 }
		    }
		 return null;
	}
	
	public void setLeser(int i,String vorname, String nachname, String strasse,
			String plz, String ort){
		Leser les = this.getLeserByID(i);
		// eine Instanz der Demo-Klasse erstellen
		JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
		System.out.println(i+ "setLeser : i");
		
		// eine Verbindung zum SAP-System per Connection-Pool einrichten und
		// eine Referenz zum Repository des SAP-Systems anfordern
		 
		jcd.erstelleVerbindungsPool();
		
		System.out.println(i + "setLeser: i");
		les.setNachname(nachname);
		les.setVorname(vorname);
		les.setStrasse(strasse);
		les.setPlz(plz);
		les.setOrt(ort);
		
		try {
			jcd.aendernLeser(les);
		} catch (Exception e) {
			// TODO Auto-generated catch block
	
			System.out.println("Schreiben des Lesers fehlgeschlagen!!!! " + e.getMessage());
		}
		finally{
			jcd.schliesseVerbindungsPool();
		}
		
	}
	// Methoden zur Pflege der Buecher
	public void addBuch(int id, String isbn, String titel, String autor,
			String beschreibung, String verlag){
		System.out.println("neue Buch-ID: " + id);
		Buch hilfsBuch = new Buch(id,isbn, titel, autor,beschreibung, verlag);
		this.alleBuecher.add(hilfsBuch);
		
			// eine Instanz der Demo-Klasse erstellen
			JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
			
			
			// eine Verbindung zum SAP-System per Connection-Pool einrichten und
			// eine Referenz zum Repository des SAP-Systems anfordern
			 
			jcd.erstelleVerbindungsPool();
			
			try {
				jcd.schreibeBuch(hilfsBuch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
		
				System.out.println("Schreiben des Buches fehlgeschlagen!!!! " + e.getMessage());
			}
			finally{
				jcd.schliesseVerbindungsPool();
			}
	}
	public void removeBuch(Buch buch){
		alleBuecher.remove(buch);
	
		// eine Instanz der Demo-Klasse erstellen
		JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
		System.out.println(buch.getId()+ "removeBuch : i");
		
		// eine Verbindung zum SAP-System per Connection-Pool einrichten und
		// eine Referenz zum Repository des SAP-Systems anfordern
		 
		jcd.erstelleVerbindungsPool();
		
		try {
			jcd.loescheBuch(buch.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
	
			System.out.println("Schreiben des Lesers fehlgeschlagen!!!! " + e.getMessage());
		}
		finally{
			jcd.schliesseVerbindungsPool();
		}
	}
	public void removeAllBuecher(){
		this.alleBuecher = null;
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
			this.alleVerleihen = jcd.oeffneVerleih();
			//System.out.println("Letzter Leser: " + jcd.letzterLeser());
			//Leser.setAnzahlLeser(jcd.letzterLeser());
			//System.out.println("Letztes Buch: " + jcd.letztesBuch());
			//Buch.setAnzahlBuecher(jcd.letztesBuch());
			
			
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
	public ArrayList<Verleih> getAlleVerleihen() {
		return alleVerleihen;
	}

	public void setAlleVerleihen(ArrayList<Verleih> alleVerleihen) {
		this.alleVerleihen = alleVerleihen;
	}

	// Methoden zur Pflege der Leser
	public void addLeser(int i, String vorname, String nachname, String strasse,
														String plz, String ort) {
		hilfsLeser = new Leser(i,vorname, nachname, strasse,plz, ort);
		this.alleLeser.add(hilfsLeser);
		System.out.println("HilfsLeser.getID() " + hilfsLeser.getId());
		
			// eine Instanz der Demo-Klasse erstellen
			JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
			
			
			// eine Verbindung zum SAP-System per Connection-Pool einrichten und
			// eine Referenz zum Repository des SAP-Systems anfordern
			 
			jcd.erstelleVerbindungsPool();
			
			try{
				jcd.schreibeLeser(hilfsLeser);
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			finally{
				jcd.schliesseVerbindungsPool();
			}
		
	}

	public Buch getBuchByID(int i) {
		Iterator<Buch> itr = alleBuecher.iterator();
		 while (itr.hasNext()) {
			 Buch buch = itr.next();
			 if(buch.getId()== i){
				 return buch; 
			 }
		    }
		 return null;
	}

	public void setBuch(int id, String isbn, String titel, String autor,
			String beschreibung, String verlag) {
		Buch buch = this.getBuchByID(id);
		// eine Instanz der Demo-Klasse erstellen
		JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
		
		// eine Verbindung zum SAP-System per Connection-Pool einrichten und
		// eine Referenz zum Repository des SAP-Systems anfordern
		 
		jcd.erstelleVerbindungsPool();
		buch.setAutor(autor);
		buch.setBeschreibung(beschreibung);
		buch.setIsbn(isbn);
		buch.setTitel(titel);
		buch.setVerlag(verlag);
		
		try {
			jcd.aendernBuch(buch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
	
			System.out.println("Schreiben des Lesers fehlgeschlagen!!!! " + e.getMessage());
		}
		finally{
			jcd.schliesseVerbindungsPool();
		}
		
	}

	public void addVerleih(int id, String ausleihdatum, String rueckgabedatum,
			int derLeser, int dasBuch) {
		
		Verleih hilfsVerleih = new Verleih(id, ausleihdatum, rueckgabedatum, derLeser, dasBuch);
	System.out.println(ausleihdatum+ "" + rueckgabedatum);
		this.alleVerleihen.add(hilfsVerleih);
		
			// eine Instanz der Demo-Klasse erstellen
			JCoDemoConPoolNew jcd = new JCoDemoConPoolNew();
			
			
			// eine Verbindung zum SAP-System per Connection-Pool einrichten und
			// eine Referenz zum Repository des SAP-Systems anfordern
			 
			jcd.erstelleVerbindungsPool();
			
			try {
				jcd.schreibeVerleih(hilfsVerleih);
			} catch (Exception e) {
				// TODO Auto-generated catch block
		
				System.out.println("Schreiben des Buches fehlgeschlagen!!!! " + e.getMessage());
			}
			finally{
				jcd.schliesseVerbindungsPool();
			}
	}
	public void aendernBuchStatus(int buch){
		Buch b = this.getBuchByID(buch);
		b.setLeihe(false);
		
	}
}

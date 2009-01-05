package BIB_GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import BIB_Modell.Ausleihe;
import BIB_Modell.Buch;
import BIB_Modell.Leser;

public class BIB_Steuer {
	
	private static Map<String, Leser> alleLeser = new HashMap< String , Leser>();
	private static Map<String, Buch> alleBuecher = new HashMap< String , Buch>();
	private static Map<String, Ausleihe> alleAusleihen = new HashMap< String , Ausleihe>();
	
	// Leser-Methoden
	public Leser getLeser(String nachname){
		Leser l = alleLeser.get(nachname);
		return l;
	}
	
	public Map<String,Leser> getAlleLeser(){
		return alleLeser;
	}
	
	public void addLeser(int id, String vorname, String nachname, String strasse,
			String plz, String ort){
		Leser l = new Leser(id,vorname,nachname,strasse,plz,ort);
		alleLeser.put(nachname, l);
		
	}
	
	// woran erkennt man das?
	public void editLeser(int id, String vorname, String nachname, String strasse,
			String plz, String ort, String alt){
		getLeser(nachname);
		
	}
	
	public void removeLeser(String nachname){
		alleLeser.remove(nachname);		
	}
	
	
	// Buch-Methoden
	public Buch getBuch(){
		return null;
	}
	
	public ArrayList<Buch> getAlleBuecher(){
		return null;
	}
	
	public void addBuch(){
		
	}
	
	public void editBuch(){
		
	}
	
	public void removeBuch(){
		
	}
	
	// Ausleihe-Funktionen
	public Ausleihe getAusleihe(){
		return null;
	}
	
	public ArrayList<Ausleihe> getAlleAusleihen(){
		return null;
	}
	
	public void addAusleihe(){
		
	}
	
	public void removeAusleihe(){
		
	}
	
	// Verbindungen mit SAP im Controller


}

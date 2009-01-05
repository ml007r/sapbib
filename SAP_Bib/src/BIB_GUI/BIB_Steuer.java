package BIB_GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import BIB_Modell.Ausleihe;
import BIB_Modell.Buch;
import BIB_Modell.Leser;

public class BIB_Steuer {
	
	private  ArrayList<Leser> alleLeser = new ArrayList<Leser>();
	private  ArrayList<Buch> alleBuecher = new ArrayList<Buch>();
	private  ArrayList<Ausleihe> alleAusleihen = new ArrayList<Ausleihe>();
	
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
		Leser hilfsLeser = new Leser(vorname, nachname, strasse,
				plz, ort);
		this.alleLeser.add(hilfsLeser);
	}
	public void removeLeser(Leser leser){
		this.alleLeser.remove(leser);
	}
	public void removeAllLeser(Leser leser){
		this.alleLeser = null;
	}
	// Methoden zur Pflege der Buecher
	public void addBuch(Buch buch){
		this.alleBuecher.add(buch);
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

}

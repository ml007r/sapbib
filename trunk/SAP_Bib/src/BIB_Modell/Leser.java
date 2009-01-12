package BIB_Modell;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Leser {

	private static int anzahlLeser;
	private int id;
	private String vorname;
	private String nachname;
	private String Strasse;
	private String plz;
	private String ort;

	/**
	 * @param id
	 * @param vorname
	 * @param nachname
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param geburtsdatum
	 * @param geburtsort
	 */
	public Leser( int id, String vorname, String nachname, String strasse,
			String plz, String ort) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		Strasse = strasse;
		this.plz = plz;
		this.ort = ort;	
		anzahlLeser++;
		System.out.println("Klasse Leser: neues Kundenobjekt!");
	}
	
	public Leser(String vorname, String nachname, String strasse,
			String plz, String ort) {
		super();
		anzahlLeser++;
		this.id = anzahlLeser;
		this.vorname = vorname;
		this.nachname = nachname;
		Strasse = strasse;
		this.plz = plz;
		this.ort = ort;	
		anzahlLeser++;
		System.out.println("Klasse Leser: neues Kundenobjekt!");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static int getAnzahlLeser() {
		return anzahlLeser;
	}

	public static void setAnzahlLeser(int anzahlLeser) {
		Leser.anzahlLeser = anzahlLeser;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getStrasse() {
		return Strasse;
	}
	public void setStrasse(String strasse) {
		Strasse = strasse;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public String toString(){
		return "ID: " + getId() + " Name: " + getNachname() + " Vorname: " + getVorname()+ "\n";
	}
}

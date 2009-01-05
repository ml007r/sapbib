package BIB_Modell;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Leser {

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
	public Leser(int id, String vorname, String nachname, String strasse,
			String plz, String ort) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		Strasse = strasse;
		this.plz = plz;
		this.ort = ort;	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}

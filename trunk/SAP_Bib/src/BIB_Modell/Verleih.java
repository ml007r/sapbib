package BIB_Modell;

import java.util.Date;

public class Verleih {

	private static int anzahlVerleihen;
	private int id;
	private String ausleihdatum;
	private String rueckgabedatum;
	private int derLeser;
	private int dasBuch;
	private boolean aktiv;
	
	
	public Verleih(int id, String ausleihdatum, String rueckgabedatum,
			int derLeser, int dasBuch) {
		super();
		this.id = id;
		this.ausleihdatum = ausleihdatum;
		this.rueckgabedatum = rueckgabedatum;
		this.derLeser = derLeser;
		this.dasBuch = dasBuch;
		anzahlVerleihen++;
		this.aktiv = true;
	}
	public Verleih(int id, String ausleihdatum, String rueckgabedatum,
			int derLeser, int dasBuch, boolean aktiv) {
		super();
		this.id = id;
		this.ausleihdatum = ausleihdatum;
		this.rueckgabedatum = rueckgabedatum;
		this.derLeser = derLeser;
		this.dasBuch = dasBuch;
		anzahlVerleihen++;
		this.aktiv = aktiv;
	}
	
	public static int getAnzahlVerleihen() {
		return anzahlVerleihen;
	}

	public static void setAnzahlVerleihen(int anzahlVerleihen) {
		Verleih.anzahlVerleihen = anzahlVerleihen;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAktiv() {
		return aktiv;
	}
	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
	public String getAusleihdatum() {
		return ausleihdatum;
	}
	public void setAusleihdatum(String ausleihdatum) {
		this.ausleihdatum = ausleihdatum;
	}
	public String getRueckgabedatum() {
		return rueckgabedatum;
	}
	public void setRueckgabedatum(String rueckgabedatum) {
		this.rueckgabedatum = rueckgabedatum;
	}
	public int getDerLeser() {
		return derLeser;
	}
	public void setDerLeser(int derLeser) {
		this.derLeser = derLeser;
	}
	public int getDasBuch() {
		return dasBuch;
	}
	public void setDasBuch(int dasBuch) {
		this.dasBuch = dasBuch;
	}	
}

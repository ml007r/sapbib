package BIB_Modell;

import java.util.Date;

public class Verleih {

	private static int anzahlVerleihen;
	private int id;
	private Date ausleihdatum;
	private Date rueckgabedatum;
	private int derLeser;
	private int dasBuch;
	//private Date frist;
	
	
	public Verleih(int id, Date ausleihdatum, Date rueckgabedatum,
			int derLeser, int dasBuch) {
		super();
		this.id = id;
		this.ausleihdatum = ausleihdatum;
		this.rueckgabedatum = rueckgabedatum;
		this.derLeser = derLeser;
		this.dasBuch = dasBuch;
//		this.frist = frist;
		anzahlVerleihen++;
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
	public Date getAusleihdatum() {
		return ausleihdatum;
	}
	public void setAusleihdatum(Date ausleihdatum) {
		this.ausleihdatum = ausleihdatum;
	}
	public Date getRueckgabedatum() {
		return rueckgabedatum;
	}
	public void setRueckgabedatum(Date rueckgabedatum) {
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
	
	/*public Date getFrist() {
		return frist;
	}
	public void setFrist(Date frist) {
		this.frist = frist;
	}*/
	
	
}

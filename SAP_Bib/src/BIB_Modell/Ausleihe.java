package BIB_Modell;

import java.util.Date;

public class Ausleihe {

	private int id;
	private Date ausleihdatum;
	private Date rueckgabedatum;
	private Leser derLeser;
	private Buch dasBuch;
	private Date frist;
	
	
	public Ausleihe(int id, Date ausleihdatum, Date rueckgabedatum,
			Leser derLeser, Buch dasBuch, Date frist) {
		super();
		this.id = id;
		this.ausleihdatum = ausleihdatum;
		this.rueckgabedatum = rueckgabedatum;
		this.derLeser = derLeser;
		this.dasBuch = dasBuch;
		this.frist = frist;
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
	public Leser getDerLeser() {
		return derLeser;
	}
	public void setDerLeser(Leser derLeser) {
		this.derLeser = derLeser;
	}
	public Buch getDasBuch() {
		return dasBuch;
	}
	public void setDasBuch(Buch dasBuch) {
		this.dasBuch = dasBuch;
	}
	public Date getFrist() {
		return frist;
	}
	public void setFrist(Date frist) {
		this.frist = frist;
	}
	
	
}

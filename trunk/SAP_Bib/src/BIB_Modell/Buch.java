package BIB_Modell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Buch {
	
	private static int anzahlBuecher = 0;
	private int id;
	private String isbn;
	private String titel;
	private String autor;
	private String beschreibung;
	private String verlag;
	private boolean leihe;
	
	public Buch(String isbn, String titel, String autor,
			String beschreibung, String verlag) {
		super();
		this.id = anzahlBuecher;
		this.isbn = isbn;
		this.titel = titel;
		this.autor = autor;
		this.beschreibung = beschreibung;
		this.verlag = verlag;
		this.leihe = true;
		anzahlBuecher++;
	}
	
	public Buch(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getVerlag() {
		return verlag;
	}
	public void setVerlag(String verlag) {
		this.verlag = verlag;
	}
	public boolean isLeihe() {
		return leihe;
	}
	public void setLeihe(boolean leihe) {
		this.leihe = leihe;
	}
	

}

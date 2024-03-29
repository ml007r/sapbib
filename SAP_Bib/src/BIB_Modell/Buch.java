package BIB_Modell;

public class Buch {
	
	private static int anzahlBuecher = 0;
	private int id;
	private String isbn;
	private String titel;
	private String autor;
	private String beschreibung;
	private String verlag;
	private boolean verleihStatus;

	public Buch(String isbn, String titel, String autor,
			String beschreibung, String verlag, boolean verleih) {
		super();
		this.id = anzahlBuecher;
		this.isbn = isbn;
		this.titel = titel;
		this.autor = autor;
		this.beschreibung = beschreibung;
		this.verlag = verlag;
		this.verleihStatus = verleih;
		anzahlBuecher++;
	}
	public Buch(int id, String isbn, String titel, String autor,
			String beschreibung, String verlag, boolean verleih) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titel = titel;
		this.autor = autor;
		this.beschreibung = beschreibung;
		this.verlag = verlag;
		this.verleihStatus = verleih;
		anzahlBuecher++;
	}
	public Buch(String isbn, String titel, String autor,
			String beschreibung, String verlag) {
		super();
		this.id = anzahlBuecher;
		this.isbn = isbn;
		this.titel = titel;
		this.autor = autor;
		this.beschreibung = beschreibung;
		this.verlag = verlag;
		this.verleihStatus = true;
		anzahlBuecher++;
	}
	public Buch(int id, String isbn, String titel, String autor,
			String beschreibung, String verlag) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titel = titel;
		this.autor = autor;
		this.beschreibung = beschreibung;
		this.verlag = verlag;
		this.verleihStatus = true;
		anzahlBuecher++;
	}

	public static int getAnzahlBuecher() {
		return anzahlBuecher;
	}
	public static void setAnzahlBuecher(int anzahlBuecher) {
		Buch.anzahlBuecher = anzahlBuecher;
		System.out.println("Aufruf: setAnzahlBuecher" + anzahlBuecher);
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
	public boolean getVerleihStatus() {
		return verleihStatus;
	}
	public void setLeihe(boolean leihe) {
		this.verleihStatus = leihe;
	}
	
	public String toString(){
		return "ID: " + id + " Autor: " + autor + " ISBN: " + isbn;
	}

}

package BIB_GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import BIB_Modell.Ausleihe;
import BIB_Modell.Buch;
import BIB_Modell.Leser;

public class BIB_Steuer {
	
	private static ArrayList<Leser> alleLeser = new ArrayList<Leser>();
	private static ArrayList<Buch> alleBuecher = new ArrayList<Buch>();
	private static ArrayList<Ausleihe> alleAusleihen = new ArrayList<Ausleihe>();
	
	public BIB_Steuer(){
		
	}
	
	public static ArrayList<Leser> getAlleLeser() {
		return alleLeser;
	}
	public static void setAlleLeser(ArrayList<Leser> alleLeser) {
		BIB_Steuer.alleLeser = alleLeser;
	}
	public static ArrayList<Buch> getAlleBuecher() {
		return alleBuecher;
	}
	public static void setAlleBuecher(ArrayList<Buch> alleBuecher) {
		BIB_Steuer.alleBuecher = alleBuecher;
	}
	public static ArrayList<Ausleihe> getAlleAusleihen() {
		return alleAusleihen;
	}
	public static void setAlleAusleihen(ArrayList<Ausleihe> alleAusleihen) {
		BIB_Steuer.alleAusleihen = alleAusleihen;
	}
	
	

}

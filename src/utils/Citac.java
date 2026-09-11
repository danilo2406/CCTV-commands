package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;

import greske.GreskaUImenuFajla;
import greske.IOExceptionWrapper;

public class Citac {
	
	private static Citac instance = null;
	private ArrayList<String> komande = new ArrayList<>();

	private Citac() {}

	public static Citac getInstance() {
		
		if (instance == null) {
			instance = new Citac();
		}
        return instance;
		
	}

	public void citajIzFajla(String s) {
		try {
			citajIzFajla1(s);
		} catch (GreskaUImenuFajla e) {
			e.iskoci();
		} catch (IOException e) {
			if (e instanceof AccessDeniedException) {
				new IOExceptionWrapper("pristup").iskoci();
			}
			else if (e instanceof NoSuchFileException) {
				new IOExceptionWrapper("ne postoji").iskoci();
			}
			else {
				new IOExceptionWrapper("").iskoci();
			}
		}
	}
	
	private void citajIzFajla1(String s) throws GreskaUImenuFajla, IOException {
		if (s != null) {
			proveraFajla(s);
			try (BufferedReader br = Files.newBufferedReader(Paths.get(s))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (!line.isBlank()) {
				        komande.add(line);
				    }
				}
			}
		}
	}

	private void proveraFajla(String s) throws GreskaUImenuFajla {
		if (s.equals("")) {
			throw new GreskaUImenuFajla("prazno");
		}
		else if (s.endsWith(".txt") && s.length() < 5) {
			throw new GreskaUImenuFajla("prazno");
		} 
		else if (!s.endsWith(".txt")) {
		    throw new GreskaUImenuFajla("ekstenzija");
		}
	}
	
	public ArrayList<String> getKomande() {
		return komande;
	}
	
	public void obrisiKomande() {
		komande.clear();
	}
	
	public int getUkupnoKomandi() {
		return komande.size();
	}

}

package utils;

import java.util.Objects;

public class Odgovor {
		
	private boolean uspesan = false;
	private String ime;
	private String tekst;
	private int kod;
	
	public Odgovor(String ime, String tekst) {
		this.uspesan = true;
		this.ime = ime;
		this.tekst = tekst;
	}
	
	public Odgovor(String ime, int kod) {
		//this.uspesan = false;
		this.ime = ime;
		this.kod = kod;
	}

	public boolean isUspesan() {
		return uspesan;
	}

	public String getIme() {
		return ime;
	}

	public String getTekst() {
		return tekst;
	}

	public int getKod() {
		return kod;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Odgovor other = (Odgovor) obj;
		return Objects.equals(ime, other.ime) && kod == other.kod && Objects.equals(tekst, other.tekst)
				&& uspesan == other.uspesan;
	}
	
	
	
}

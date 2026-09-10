package greske;

public class GreskaUSlanjuKomande extends GreskaAdapter {
	
	static String odrediPoruku(String s) {
		if (s.equals("prazno")) {
			return "Nema učitanih komandi";
		}
		else {
			return "Greška pri slanju komande kameri";
		}
	}
	
	public GreskaUSlanjuKomande(String s) {
		super(odrediPoruku(s));
	}
	
}

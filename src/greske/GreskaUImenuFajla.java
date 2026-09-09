package greske;

public class GreskaUImenuFajla extends GreskaAdapter {
	
	static String odrediPoruku(String s) {
		if (s.equals("prazno")) {
			return "Ime fajla ne moze biti prazan tekst";
		}
		else if (s.equals("ekstenzija")) {
			return "Fajl mora biti tipa .txt";
		}
		else {
			return "Ime fajla ne moze biti prazan tekst\nFajl mora biti tipa .txt";
		}
	}
	
	public GreskaUImenuFajla(String s) {
		super(odrediPoruku(s));
	}
	
}

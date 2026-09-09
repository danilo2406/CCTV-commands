package greske;

public class IOExceptionWrapper extends GreskaAdapter {
	
	static String odrediPoruku(String s) {
		
		if (s.equals("pristup")) {
			return "Odbijen pristup fajlu";
		}
		else if (s.equals("ne postoji")) {
			return "Fajl ne postoji";
		}
		else {
			return "Greska u citanju fajla";
		}
	}
	
	public IOExceptionWrapper(String s) {
		super(odrediPoruku(s));
	}
	
	
	
}

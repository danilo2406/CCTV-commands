package utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

public class Posiljalac {
	
	private static Posiljalac instance = null;
	private HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build();
	private StringBuilder sb = new StringBuilder();
	private ArrayList<Odgovor> odgovori = new ArrayList<>();
	private int brojUspesnih;
	private int brojNeuspesnih;
	
	private Posiljalac() {}

	public static Posiljalac getInstance() {
		
		if (instance == null) {
			instance = new Posiljalac();
		}
        return instance;
		
	}
	
	public void posalji(String s) {

		sb.delete(0, sb.length());
		String url = sb.append("http://").append(s.trim().replaceAll("(?i)^https?[:/\\\\]+", "")).toString();
		
		try {
			
			HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().timeout(Duration.ofSeconds(2)).build();
			
			HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			
			if (response.statusCode() == 200) {
				Odgovor uo = new Odgovor(url, response.body());
				if (!odgovori.contains(uo)) {
					odgovori.add(uo);
					brojUspesnih++;
				}
            }
			else {
				Odgovor no = new Odgovor(url, response.statusCode());
				if (!odgovori.contains(no)) {
					odgovori.add(no);
	            	brojNeuspesnih++;
				}
            }
			
		}
		catch(Exception e) {
			Odgovor eo = new Odgovor(url, 111);
			if (!odgovori.contains(eo)) {
				odgovori.add(eo);
				brojNeuspesnih++;
			}
		}
	}
	
	public void obrisiPodatke() {
		odgovori.clear();
		brojUspesnih = 0;
		brojNeuspesnih = 0;
	}
	
	public int getBrojUspesnih() {
		return brojUspesnih;
	}

	public int getBrojNeuspesnih() {
		return brojNeuspesnih;
	}

	public int getUkupnoKamera() {
		return odgovori.size();
	}

	public ArrayList<Odgovor> getOdgovori() {
		return odgovori;
	}
	
}

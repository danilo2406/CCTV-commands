package main;

import gui.GlavniProzor;
import utils.Citac;
import utils.Posiljalac;

public class Main {
	
	public static void main(String[] args) {
		
		Citac c = Citac.getInstance();
		Posiljalac p = Posiljalac.getInstance();
		new GlavniProzor(c, p);
		
	}
	
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import greske.GreskaUSlanjuKomande;
import utils.Odgovor;
import utils.Posiljalac;

public class IzvestajOdgovora extends Frame {
	
	private Posiljalac posiljalac;
	
	public IzvestajOdgovora(Frame owner, Posiljalac p) throws GreskaUSlanjuKomande {
		
		this.posiljalac = p;
		if (p.getOdgovori().isEmpty()) {
			throw new GreskaUSlanjuKomande("prazno");
		}
		
		setTitle("Odgovori kamera");
		setLocation(owner.getX() + 50, owner.getY() + 50);
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
		
	}
	
	private void populateWindow() {
		
		Panel sadrzaj = new Panel(new GridLayout(1, 2, 20, 0));
		
		Panel uspesniKolona = new Panel(new BorderLayout(0, 10));
	    Panel neuspesniKolona = new Panel(new BorderLayout(0, 10));
	    
	    Label uspesniLabel = new Label("Uspešni " + posiljalac.getBrojUspesnih() + "/" + posiljalac.getUkupnoKamera(), Label.CENTER);
	    uspesniLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	    uspesniLabel.setBackground(Color.GREEN);
	    Label neuspesniLabel = new Label("Neuspešni " + posiljalac.getBrojNeuspesnih() + "/" + posiljalac.getUkupnoKamera(), Label.CENTER);
	    neuspesniLabel.setFont(new Font("Dialog", Font.BOLD, 25));
	    neuspesniLabel.setBackground(Color.RED);
	    
	    TextArea uspesniTA = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
	    TextArea neuspesniTA = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		
	    uspesniTA.setFont(new Font("Monospaced", Font.PLAIN, 18));
	    neuspesniTA.setFont(new Font("Monospaced", Font.PLAIN, 18));
		uspesniTA.setEditable(false);
	    neuspesniTA.setEditable(false);
	    
	    StringBuilder uspesniTekst = new StringBuilder();
	    StringBuilder neuspesniTekst = new StringBuilder();
	    
	    int maxSirina = 50;
	    
	    Font taFont = uspesniTA.getFont();
		
		for (Odgovor o : posiljalac.getOdgovori()) {
			String url = "URL: " + o.getIme();
			if (o.isUspesan()) {
	            uspesniTekst.append(url).append("\n");
	            uspesniTekst.append("ODGOVOR:\n").append(o.getTekst()).append("\n");
	            uspesniTekst.append("-".repeat(20)).append("\n\n");
	        } else {
	            neuspesniTekst.append(url).append("\n");
	            neuspesniTekst.append("KOD GREŠKE: ").append(o.getKod()).append("\n\n");
	            neuspesniTekst.append("-".repeat(20)).append("\n\n");
	        }
			int sirina = izracunajSirinuTeksta(url, taFont);
	        if (sirina > maxSirina) maxSirina = sirina;
		}
		
		uspesniTA.setText(uspesniTekst.toString());
	    neuspesniTA.setText(neuspesniTekst.toString());
		
		uspesniKolona.add(uspesniLabel, BorderLayout.NORTH);
		uspesniKolona.add(uspesniTA, BorderLayout.CENTER);
	    
	    neuspesniKolona.add(neuspesniLabel, BorderLayout.NORTH);
	    neuspesniKolona.add(neuspesniTA, BorderLayout.CENTER);
		
	    sadrzaj.add(uspesniKolona);
	    sadrzaj.add(neuspesniKolona);
	    
	    sadrzaj.setPreferredSize(new Dimension(((maxSirina + 60) * 2), 400));
	    
		add(sadrzaj, BorderLayout.CENTER);
		
		pack();
		validate();
		repaint();
		
	}

	private int izracunajSirinuTeksta(String s, Font f) {
		FontMetrics fm = getFontMetrics(f);
	    return fm.stringWidth(s);
	}
	
}

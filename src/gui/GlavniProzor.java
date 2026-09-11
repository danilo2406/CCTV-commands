package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import greske.GreskaUSlanjuKomande;
import utils.Citac;
import utils.Posiljalac;

public class GlavniProzor extends Frame {
	
	private Citac citac;
	private Posiljalac posiljalac;
	
	private Label fajl;
	private Label brojKomandi;
	private TextArea fajlTA;
	private LoadDialog ld;
	
	public GlavniProzor(Citac c, Posiljalac p) {
		
		this.citac = c;
		this.posiljalac = p;
		
		setTitle("Komande za kamere");
		setBounds(600, 250, 500, 500);
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				for (Window w : getWindows()) {
					w.dispose();
				}
			}
		});
		
		setVisible(true);
		
	}

	private void populateWindow() {
		
		
		setLayout(new BorderLayout(30, 30));
		
		Panel toolBar = new Panel(new GridLayout(1, 2));
		
		Button load = new Button("Učitaj fajl");
	    load.setPreferredSize(new Dimension(300, 50));
	    load.setFont(new Font("Dialog", Font.BOLD, 20));
	    Button send = new Button("Pošalji komande");
	    send.setPreferredSize(new Dimension(300, 50));
	    send.setFont(new Font("Dialog", Font.BOLD, 20));
	    
	    toolBar.add(load);
	    toolBar.add(send);
	    
	    add(toolBar, BorderLayout.NORTH);
	    
	    Panel centar = new Panel(new BorderLayout(20, 20));
	    
	    Panel info = new Panel(new GridLayout(2, 1));
	    
	    fajl = new Label("Učitan fajl: --- Nije učitan nijedan fajl ---", Label.CENTER);
	    fajl.setFont(new Font("Dialog", Font.PLAIN, 18));
	    
	    brojKomandi = new Label("Ukupno komandi: " + citac.getUkupnoKomandi(), Label.CENTER);
	    brojKomandi.setFont(new Font("Dialog", Font.PLAIN, 18));
	    
	    info.add(fajl);
	    info.add(brojKomandi);
	    
	    centar.add(info, BorderLayout.NORTH);
	    
	    fajlTA = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
	    fajlTA.setFont(new Font("Monospaced", Font.PLAIN, 15));
		fajlTA.setEditable(false);
		
		centar.add(fajlTA, BorderLayout.CENTER);
		
		add(centar, BorderLayout.CENTER);
		
		Button obrisi = new Button("Obriši podatke");
		obrisi.setFont(new Font("Dialog", Font.BOLD, 20));
		add(obrisi, BorderLayout.SOUTH);
		
		load.addActionListener((ae) -> {
			
			ld = new LoadDialog(this, posiljalac, citac);
			citac.citajIzFajla(ld.getPutDoFajla());
			osveziPrikaz();
			
		});
		
		send.addActionListener((ae) -> {
			
			for (String komanda : citac.getKomande()) {
				posiljalac.posalji(komanda);
			}
			try {
				new IzvestajOdgovora(this, posiljalac);
			}
			catch (GreskaUSlanjuKomande e) {
				e.iskoci();
			}
			
		});
		
		obrisi.addActionListener((ae) -> {
			
			citac.obrisiKomande();
			posiljalac.obrisiPodatke();
			osveziPrikaz();
			
		});
		
	    pack();
		validate();
		repaint();
		
	}
	
	private void osveziPrikaz() {
		
		if (citac.getKomande().isEmpty()) {
			fajl.setText("Učitan fajl: --- Nije učitan nijedan fajl ---");
	        brojKomandi.setText("Ukupno komandi: 0");
	        fajlTA.setText("");
		}
		else {
			StringBuilder sb = new StringBuilder();
			
			for (String komanda : citac.getKomande()) {
				sb.append(komanda).append("\n");
			}
			
			fajl.setText("Učitan fajl: " + ld.getImeFajla());
			brojKomandi.setText("Ukupno komandi: " + citac.getUkupnoKomandi());
			fajlTA.setText(sb.toString());
		}
		
		revalidate();
		
	}
	
}

package gui;

import java.awt.*;
import java.awt.event.*;

import greske.GreskaUSlanjuKomande;
import utils.*;

public class GlavniProzor extends Frame {
	
	private Citac citac;
	private Posiljalac posiljalac;
	
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
		
		Panel sadrzaj = new Panel(new GridBagLayout());
		
		dodajDugmad(sadrzaj);

		add(sadrzaj, BorderLayout.CENTER);
		
		validate();
		repaint();
		
	}

	private void dodajDugmad(Panel p) {
		
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.insets = new Insets(10, 10, 10, 10);

	    Button load = new Button("Učitaj");
	    load.setPreferredSize(new Dimension(300, 110));
	    load.setFont(new Font("Dialog", Font.BOLD, 25));

	    Button send = new Button("Pošalji");
	    send.setPreferredSize(new Dimension(300, 110));
	    send.setFont(new Font("Dialog", Font.BOLD, 25));

	    gbc.gridy = 0;
	    p.add(load, gbc);

	    gbc.gridy = 1;
	    p.add(send, gbc);
		
		load.addActionListener((ae) -> {
			LoadDialog ld = new LoadDialog(this, posiljalac, citac);
			citac.citajIzFajla(ld.getImeFajla());
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
		
	}
	
}

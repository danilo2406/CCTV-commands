package gui;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import utils.*;
import greske.GreskaUEnkodiranju;

public class GlavniProzor extends Frame {
	
	private Citac citac;
	
	public GlavniProzor(Citac c) {
		
		this.citac = c;
		
		setTitle("Komande za kamere");
		setBounds(600, 250, 500, 500);
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
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

	    Button load = new Button("Load");
	    load.setPreferredSize(new Dimension(300, 110));
	    load.setFont(new Font("Dialog", Font.BOLD, 25));

	    Button send = new Button("Send");
	    send.setPreferredSize(new Dimension(300, 110));
	    send.setFont(new Font("Dialog", Font.BOLD, 25));

	    gbc.gridy = 0;
	    p.add(load, gbc);

	    gbc.gridy = 1;
	    p.add(send, gbc);
		
		load.addActionListener((ae) -> {
			LoadDialog ld = new LoadDialog(this);
			citac.citajIzFajla(ld.getImeFajla());
		});
		
		send.addActionListener((ae) -> {
			citac.resetKomanda();
			String query = null;
			while (citac.getKomanda() != null) {
				String s = citac.getKomanda();
				citac.sledecaKomanda();
				try {
					query = URLEncoder.encode(s, "UTF-8");
					//String url = "https://www.google.com/search?q=" + query;
					String url = "https://" + query;
					Desktop.getDesktop().browse(new URI(url));
					/* TODO: Ovde treba staviti neku funkciju
		        	za slanje HTTPS ili tako nesto (Vapix) */
				} catch (UnsupportedEncodingException e) {
					new GreskaUEnkodiranju().iskoci();
				} catch (IOException | URISyntaxException e) {
					System.err.print("Neka greska sa pretrazivanjem");
					e.printStackTrace();
				}
			}
		});
		
	}
	
}

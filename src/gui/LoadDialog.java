package gui;

import java.awt.*;
import java.awt.event.*;

import utils.Citac;
import utils.Posiljalac;

public class LoadDialog extends Dialog {
	
	private String imeFajlaVar;
	private TextField imeFajla = new TextField();
	private Posiljalac posiljalac;
	private Citac citac;
	private Checkbox brisanjePodataka;
	
	public LoadDialog(Frame owner, Posiljalac p, Citac c) {
		
		super(owner);
		
		this.posiljalac = p;
		this.citac = c;
		
		setTitle("Učitaj iz fajla");
		setLocation(owner.getX() + owner.getWidth() / 2, owner.getY() + owner.getHeight() / 2);
		
		populateWindow();
		
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
		
	}

	private void populateWindow() {
		
		Panel sadrzaj = new Panel(new GridLayout(0, 1));
		
		Panel p = new Panel();
		
		Label l = new Label("Unesite ime fajla iz koga želite da učitate podatke");
		l.setFont(new Font("Dialog", Font.PLAIN, 25));
		p.add(l);
		sadrzaj.add(p);
		
		Panel field = new Panel();
		imeFajla.setPreferredSize(new Dimension(300, 40));
		imeFajla.setFont(new Font("Dialog", Font.PLAIN, 20));
		field.add(imeFajla);
		sadrzaj.add(field);
		
		Panel brisanjePodatakaPanel = new Panel();
		brisanjePodataka = new Checkbox("Obriši postojeće podatke");
		brisanjePodataka.setFont(new Font("Dialog", Font.PLAIN, 20));
		brisanjePodatakaPanel.add(brisanjePodataka);
		sadrzaj.add(brisanjePodatakaPanel);
		
		Panel buttonPanel = new Panel();
		
		Button load = new Button("Učitaj");
		load.setPreferredSize(new Dimension(120, 50));
	    load.setFont(new Font("Dialog", Font.PLAIN, 22));
	    
		Button cancel = new Button("Otkaži");
		cancel.setPreferredSize(new Dimension(120, 50));
	    cancel.setFont(new Font("Dialog", Font.PLAIN, 22));
	    
		buttonPanel.add(load);
		buttonPanel.add(cancel);
		add(buttonPanel, BorderLayout.SOUTH);
		
		load.addActionListener((ae) -> {
			ucitaj();
		});
		
		cancel.addActionListener((ae) -> {
			dispose();
		});
		
		imeFajla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ucitaj();
				}
			}
		});
		
		add(sadrzaj, BorderLayout.CENTER);
		
		pack();
		
	}
	
	private void ucitaj() {
		imeFajlaVar = imeFajla.getText();
		if (brisanjePodataka.getState()) {
			posiljalac.obrisiPodatke();
			citac.obrisiKomande();
		}
		dispose();
	}
	
	public String getImeFajla() {
		return imeFajlaVar;
	}
	
}

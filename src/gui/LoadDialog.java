package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFileChooser;

import utils.Citac;
import utils.Posiljalac;

public class LoadDialog extends Dialog {
	
	private String imeFajla;
	private Posiljalac posiljalac;
	private Citac citac;
	private Checkbox brisanjePodataka;
	private String putDoFajla;
	
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
		
		Panel p = new Panel(new GridBagLayout());
		Label l = new Label("Odaberite fajl iz koga želite da učitate podatke");
		l.setFont(new Font("Dialog", Font.PLAIN, 25));
		p.add(l);
		sadrzaj.add(p);
		
		Panel odabirP = new Panel();
		Button odabir = new Button("Odaberi");
		odabir.setPreferredSize(new Dimension(120, 50));
	    odabir.setFont(new Font("Dialog", Font.PLAIN, 22));
	    odabirP.add(odabir);
	    sadrzaj.add(odabirP);
	    
	    Panel odabraniFajlPanel = new Panel(); 
	    Label odabraniFajl = new Label("---Nije odabran nijedan fajl---", Label.CENTER);
	    odabraniFajl.setFont(new Font("Dialog", Font.BOLD, 18));
	    odabraniFajlPanel.add(odabraniFajl);
		sadrzaj.add(odabraniFajlPanel);
	    
	    odabir.addActionListener((ae) -> {
	    	JFileChooser fc = new JFileChooser();
	    	int returnVal = fc.showOpenDialog(this);
	    	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    		imeFajla = fc.getSelectedFile().getName();
	    		putDoFajla = fc.getSelectedFile().getAbsolutePath();
	    		odabraniFajl.setText(imeFajla);
	    		pack();
	    		revalidate();
	    	}
	    });
		
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
			if (brisanjePodataka.getState()) {
				posiljalac.obrisiPodatke();
				citac.obrisiKomande();
			}
			dispose();
		});
		
		cancel.addActionListener((ae) -> {
			dispose();
		});

		add(sadrzaj, BorderLayout.CENTER);
		
		pack();
		
	}
	
	public String getImeFajla() {
		return imeFajla;
	}
	
	public String getPutDoFajla() {
		return putDoFajla;
	}
	
}

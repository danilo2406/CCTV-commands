package gui;

import java.awt.*;
import java.awt.event.*;

public class LoadDialog extends Dialog {
	
	private String imeFajlaVar;
	private TextField imeFajla = new TextField();
	
	public LoadDialog(Frame owner) {
		
		super(owner);
		
		setTitle("Ucitaj iz fajla");
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
		
		Label l = new Label("Unesite ime fajla iz koga zelite da ucitate podatke");
		l.setFont(new Font("Dialog", Font.PLAIN, 25));
		p.add(l);
		sadrzaj.add(p);
		
		Panel field = new Panel();
		imeFajla.setPreferredSize(new Dimension(300, 40));
		imeFajla.setFont(new Font("Dialog", Font.PLAIN, 20));
		field.add(imeFajla);
		sadrzaj.add(field);
		
		Panel buttonPanel = new Panel();
		
		Button load = new Button("Load");
		load.setPreferredSize(new Dimension(80, 35));
	    load.setFont(new Font("Dialog", Font.PLAIN, 15));
	    
		Button cancel = new Button("Cancel");
		cancel.setPreferredSize(new Dimension(80, 35));
	    cancel.setFont(new Font("Dialog", Font.PLAIN, 15));
	    
		buttonPanel.add(load);
		buttonPanel.add(cancel);
		add(buttonPanel, BorderLayout.SOUTH);
		
		load.addActionListener((ae) -> {
			imeFajlaVar = imeFajla.getText();
			dispose();
		});
		
		cancel.addActionListener((ae) -> {
			dispose();
		});
		
		add(sadrzaj, BorderLayout.CENTER);
		
		pack();
		
	}
	
	public String getImeFajla() {
		return imeFajlaVar;
	}
	
}

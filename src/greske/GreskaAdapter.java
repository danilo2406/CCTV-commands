package greske;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GreskaAdapter extends Exception {
	
	public GreskaAdapter(String s) {
		super(s);
	}
	
	private class greskaProzor extends Dialog {
		
		public greskaProzor() {
			
			super(new Frame());
			
			setTitle("Greška");
			setLocation(700, 500);
			
			Panel poruka = new Panel(new GridLayout(0, 1));
			
			for (String line : getMessage().split("\n")) {
				
				Label l = new Label(line, Label.CENTER);
				l.setFont(new Font("Dialog", Font.PLAIN, 25));
				poruka.add(l);
				
			}
			
			add(poruka, BorderLayout.CENTER);
			
			Panel okPanel = new Panel();
			Button ok = new Button("OK");
			ok.setPreferredSize(new Dimension(80, 35));
		    ok.setFont(new Font("Dialog", Font.PLAIN, 20));
			okPanel.add(ok);
			add(okPanel, BorderLayout.SOUTH);
			
			ok.addActionListener((ae) -> {
				dispose();
			});
			
			pack();
			
			setResizable(false);
			setModalityType(ModalityType.APPLICATION_MODAL);
			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
			
			setVisible(true);
			
		}
		
	}
	
	public void iskoci() {
		new greskaProzor();
	}
	
}

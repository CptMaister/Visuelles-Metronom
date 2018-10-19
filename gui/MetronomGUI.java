package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MetronomGUI extends Frame implements ActionListener {
	
	private Label lBpm, lBloecks, lFarben1, lFarben2, lZahl;
	private TextField tfBpm, tfBloecks;
	private Button bStart;
	private Choice cFarben1, cFarben2;
	private Checkbox chZahl;
	private Panel pOben, pBpm, pFarben, pZahl, pAnleitung;
	private ArrayList<String> anleitung = new ArrayList<String>();
	
	private Integer bpm, bloecks;
	
	public MetronomGUI() {
		super("Visuelles Metronom");
		
		//Sachen initialisieren
		pOben = new Panel();
		
		pBpm = new Panel();
		lBpm = new Label("BPM eingeben:");
		tfBpm = new TextField("120", 6);
		lBloecks = new Label("Anzahl Blöcks:");
		tfBloecks = new TextField("4", 3);
		
		pFarben = new Panel();
		lFarben1 = new Label("Taktfarbe:");
		lFarben2 = new Label("Hintergrundfarbe:");
		cFarben1 = new FarbChoice();
		cFarben1.select("Rot");
		cFarben2 = new FarbChoice();
		cFarben2.select("Weiss");
		
		pZahl = new Panel();
		chZahl = new Checkbox();
		lZahl = new Label("Zahlen anzeigen");
		
		bStart = new Button("Metronom starten!");
		bStart.setBackground(new Color(245, 245, 245));
		bStart.addActionListener(this);
		
		//Anleitung hier Zeile für Zeile befüllen
		pAnleitung = new Panel();
		anleitung.add("BPM eingeben und auf Start drücken.");
		anleitung.add("Im neuen Fenster mit s-Taste anhalten.");
		anleitung.add("Mit anderen Tasten neu starten.");
		anleitung.add("© Alexander Mai, CC BY-SA 4.0");
		//contact alex.m.s[at]gmx.de
		
		
		//Layout setzen
		add(pOben, BorderLayout.NORTH);
		pOben.setLayout(new GridLayout(3, 1));
		pOben.add(pBpm);
		pOben.add(pFarben);
		pOben.add(pZahl);
		
		pBpm.add(lBpm);
		pBpm.add(tfBpm);
		pBpm.add(lBloecks);
		pBpm.add(tfBloecks);
		
		pFarben.add(lFarben1);
		pFarben.add(cFarben1);
		pFarben.add(lFarben2);
		pFarben.add(cFarben2);
		
		pZahl.add(chZahl);
		pZahl.add(lZahl);
		
		add(bStart, BorderLayout.CENTER);
		
		//Anleitung im Layout setzen
		add(pAnleitung, BorderLayout.SOUTH);
		pAnleitung.setLayout(new GridLayout(anleitung.size(), 1));
		for (String s: anleitung) {
			if (s.charAt(0) == '©') {
				pAnleitung.add(new Label(s, Label.RIGHT));
			} else {
				pAnleitung.add(new Label(s));
			}
		}

		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				dispose();
			}
		});
		
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) ((screenSize.getWidth() / 2) - (this.getWidth() / 2)), (int) ((screenSize.getHeight() / 2) - (this.getHeight() / 2)));
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		bpm = tryParse(tfBpm.getText());
		bloecks = tryParse(tfBloecks.getText());
		
		if (bpm == null)
			return;
		if (bloecks == null)
			bloecks = 4;
		
		if (e.getSource().equals(bStart)) {
			new LaufendDialog(this, bpm, bloecks, cFarben1.getSelectedItem(), cFarben2.getSelectedItem(), chZahl.getState());
		}
	}
	
	
	public static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	
}

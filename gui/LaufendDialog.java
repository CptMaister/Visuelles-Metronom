package gui;

import java.awt.*;
import java.awt.event.*;

import data.*;

public class LaufendDialog extends Dialog implements KeyListener {
	
	private Color blinkFarbe, hintergrundFarbe;
	private Integer bpm, bloecks, blinkCounter, labelFontSize;
	private Panel pBloecks;
	
	private MetronomThread thread;
	
	public LaufendDialog(Frame owner, Integer beats, Integer blocke, String blinkF, String hintergrundF, boolean bZahl) {
		super(owner, beats.toString() + "bpm mit " + blocke.toString() + " Blöcks", false);
		
		//int und so initialisieren
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		bpm = beats;
		bloecks = blocke;
		blinkCounter = 0;
		blinkFarbe = bestimmeFarbe(blinkF);
		hintergrundFarbe = bestimmeFarbe(hintergrundF);
		
		//Fenster befüllen
		pBloecks = new Panel();
		pBloecks.setBackground(hintergrundFarbe);
		pBloecks.setLayout(new GridLayout(1, bloecks));
		for (int i = 0; i < bloecks; i++) {
			Panel pTemp = new Panel();
			if (bZahl) {
				Label lTemp = new Label(((Integer) (i + 1)).toString());
				labelFontSize = (int) (Math.min(screenSize.getWidth(), screenSize.getHeight()) / bloecks) ;
				Font fTemp = new Font("Fixedsys", Font.PLAIN, labelFontSize);
				lTemp.setFont(fTemp);
				pTemp.add(lTemp);
			}
			pBloecks.add(pTemp);
			
		}
		add(pBloecks);
		
		thread = new MetronomThread(bpm, this);
		
		
		
		this.addKeyListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		
		setSize((int) (screenSize.getWidth() * 0.9), (int) (screenSize.getHeight() * 0.9));
		setLocation((int) ((screenSize.getWidth() / 2) - (this.getWidth() / 2)), (int) ((screenSize.getHeight() / 2) - (this.getHeight() / 2)));
		setVisible(true);
		
		
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 's') {
			thread.halt();
		} else if (e.getKeyChar() == 'q') {
			dispose();
		} else {
			thread.restart();
		}
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
	
	}
	
	public void blinkNext() {
		pBloecks.getComponent(trueModulo(blinkCounter - 1, bloecks)).setBackground(hintergrundFarbe);
		pBloecks.getComponent(blinkCounter).setBackground(blinkFarbe);
		blinkCounter = trueModulo((blinkCounter + 1), bloecks);
	}
	
	public void blinkDeleteCurrent() {
		pBloecks.getComponent(trueModulo(blinkCounter - 1, bloecks)).setBackground(hintergrundFarbe);
	}
	
	public int trueModulo(int c, int mod) {
		while (c < 0) {
			c += mod;
		}
		while (c >= mod) {
			c -= mod;
		}
		return c;
	}
	
	public void setCounter(int j) {
		this.blinkCounter = j;
	}
	
	public Color bestimmeFarbe(String st) {
		if (st.equals("Schwarz"))
			return Color.BLACK;
		if (st.equals("Weiss"))
			return Color.WHITE;
		if (st.equals("Rot"))
			return Color.RED;
		if (st.equals("Blau"))
			return Color.BLUE;
		if (st.equals("Gruen"))
			return Color.GREEN;
		if (st.equals("Cyan"))
			return Color.CYAN;
		if (st.equals("Magenta"))
			return Color.MAGENTA;
		if (st.equals("Gelb"))
			return Color.YELLOW;
		if (st.equals("Orange"))
			return Color.ORANGE;
		if (st.equals("Pink"))
			return Color.PINK;
		return Color.BLACK;
	}
	
}

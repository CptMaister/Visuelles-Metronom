package data;

import gui.*;
import java.awt.event.*;

public class MetronomThread extends Thread {
	
	private int pause;
	private boolean running;
	private LaufendDialog dia;
	
	public MetronomThread(Integer bpm, LaufendDialog d) {
		dia = d;
		pause = 60000 / bpm;
		running = true;
		
		this.start();
	}
	
	public void run() {
		while (true) {
			try {
				sleep(pause);
			} catch (InterruptedException e) {
			}
			if (running) 
				dia.blinkNext();
		}
	}
	
	//startet Blinken nochmal von links
	public void restart() {
		dia.blinkDeleteCurrent();
		dia.setCounter(0);
		running = true;
		this.interrupt();
	}
	
	//soll das Blinken an der Stelle pausieren
	public void halt() {
		if (running) {
			running = false;
		} else {
			running = true;
			interrupt();
		}
	}
	
}

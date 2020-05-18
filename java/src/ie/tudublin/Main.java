package ie.tudublin;

import ie.tudublin.c18453976.main.Music;

public class Main{	
	public void startUI() {
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Music());
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.startUI();			
	}
}
package ie.tudublin.c18453976.shapes;

import ddf.minim.*;
import ie.tudublin.c18453976.main.Music;
import processing.core.PApplet;

public class Radial extends PApplet {
  private PApplet parent; 
  private Music m = new Music();

  public static float speed = 0.006f;
  private float spiral;

  // Initialised to 0 which means it is not used
  // Used so I can change the colour of the shape in the control panel
  public static int colour = 0;

  public Radial(PApplet parent) {
    // getting the PApplet from Music class and assigning to this class
    // this is how the music and the control panel are being synced together across multiple files
    this.parent = parent; 
  }

  public void drawPoint(AudioPlayer music) {
    parent.fill(colour == 0 ? m.originalColour : colour);   
    for (int i = 0; i < music.bufferSize() -1; i++) { 
      float leftLevel = music.left.level() * 20;
      parent.ellipse(i, i, leftLevel, leftLevel);
      parent.rotateZ((float) (spiral * -PI / 3 * 0.05));
    }
    
    spiral += speed;
  }
}
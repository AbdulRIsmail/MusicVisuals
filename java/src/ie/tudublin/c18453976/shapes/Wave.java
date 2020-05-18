package ie.tudublin.c18453976.shapes;

import processing.core.PApplet;
import ddf.minim.*;
import ie.tudublin.c18453976.main.Music;

public class Wave {
  private PApplet parent;
  private Music m = new Music();

  // Initialised to 0 which means it is not used
  // Used so I can change the colour of both shapes in the control panel
  public static int colour = 0;
  public static int colour1 = 0;

  // orb & dots
  private float orb;
  private float dots;

  public Wave(PApplet parent) {
    // getting the PApplet from Music class and assigning to this class
    // this is how the music and the control panel are being synced together across multiple files
    this.parent = parent;
  }

  public void drawPoint(AudioPlayer music) {
    parent.noStroke();

    for (int i = 0; i < music.bufferSize() - 1; i++) {
      // orb angle, x, y
      float orbAngle = PApplet.sin(i + orb)* 300;
      float orbX = PApplet.sin(PApplet.radians(i))*(orbAngle+30);
      float orbY = PApplet.cos(PApplet.radians(i))*(orbAngle+30);

      // dots angle, x, y
      float dotsAngle = PApplet.sin(i + dots)* 10;
      float dotsX = PApplet.sin(PApplet.radians(i))*(500/dotsAngle);
      float dotsY = PApplet.cos(PApplet.radians(i))*(500/dotsAngle);
  
      // orb
      parent.fill(colour == 0 ? m.originalColour : colour);
      parent.ellipse(orbX, orbY, music.left.get(i)*5, music.left.get(i)*5);
      parent.fill(colour == 0 ? m.originalColour : colour);
      parent.rect(orbX, orbY, music.right.get(i)*5, music.left.get(i)*5);

      // dots
      parent.fill(colour1 == 0 ? m.originalColour : colour1);
      parent.rect(dotsX, dotsY, music.left.get(i) * 10, music.left.get(i) * 5);
      parent.fill(colour1 == 0 ? m.originalColour : colour1);
      parent.rect(dotsX, dotsY, music.right.get(i) * 5, music.right.get(i)*10);
    }

    dots += 0.008;
    orb += 0.4;
  }
}

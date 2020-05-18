package ie.tudublin.c18453976.shapes;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ie.tudublin.c18453976.main.Music;
import ie.tudublin.c18453976.GUI.ControlPanel;

public class Tunnel {
  private PApplet parent;
  private FFT fft;
  private Music m = new Music();
    
  private final float dist = -25; // distance between each line point
  private final float height = 2; // to make the height

  // Initialised to 0 which means it is not used
  // Used so I can change the colour of the shape in the control panel
  public static int colour = 0;

  public Tunnel(PApplet parent, AudioPlayer music) {
    // getting the PApplet from Music class and assigning to this class
    // this is how the music and the control panel are being synced together across multiple files
    this.parent = parent;
    this.fft = new FFT(music.bufferSize(), music.sampleRate());
  }

  public void drawPoint(AudioPlayer music) {
    fft.forward(music.mix);
    float previousBandValue = fft.getBand(0); // must keep track of the previous band

    //  hide the lines when music is paused
    if (!ControlPanel.musicPaused) {
      // keep displaying if the music position is less than the music length - 3 seconds
      if (music.position() < (music.length() - 3000)) {
        for (int i = 1; i < fft.specSize(); i++) {
          parent.translate(0, 0);  // make it start from top left frame position
          float currentBandValue = fft.getBand(i) * (1 + (i / 50));

          // the line colour
          parent.stroke(colour == 0 ? m.originalColour : colour);
          parent.line(parent.width, (previousBandValue * height), dist * (i - 1), parent.width, (currentBandValue * height), dist * i); // top right corner
          parent.line(parent.width, parent.height - (previousBandValue * height), dist * (i - 1), parent.width, parent.height - (currentBandValue * height), dist * i); //  bottom right corner
          parent.line(0, (previousBandValue * height), dist * (i - 1), 0, (currentBandValue * height), dist * i); // top left corner
          parent.line(0, parent.height - (previousBandValue * height), dist * (i - 1), 0, parent.height - (currentBandValue * height), dist * i); // bottom left corner 
           
          // used for the next loop iteration
          previousBandValue = currentBandValue;
        }
      }                
    }
  }
}
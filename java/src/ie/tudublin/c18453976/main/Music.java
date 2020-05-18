package ie.tudublin.c18453976.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.c18453976.GUI.ControlPanel;
import ie.tudublin.c18453976.shapes.*;
import processing.core.PApplet;

public class Music extends PApplet {
    public Minim minim;
    public static AudioPlayer music;

    // hide/show each shape
    public static boolean showWave = true;
    public static boolean showRadial = true;
    public static boolean showTunnel = true;
    public static boolean switchColours = true;

    // instance of classes
    Wave wave;
    Radial radial;
    Tunnel tunnel;

    // original colour of all the shapes
    public int originalColour = color(255, 255, 0);

    // map of String & Integer
    // using this to store the name color and the value e.g. Red --> color (255,0,0)
    // this will be used to change the colours
    public static Map<String, Integer> colours = new HashMap<>();

    public void settings() {
        size(displayWidth - 200, displayHeight - 200, P3D);
    }

    public void setup() {
        // setup colours - adds colours to the Map of <String, Integer>
        setupColours();

        // setup the control panel
        ControlPanel cp = new ControlPanel();
        cp.main(args);

        // load the music to play
        minim = new Minim(this);
        music = minim.loadFile("Peyruis - Tarlo.mp3");

        // set up the track player
        ControlPanel.musicPlayer.setMinimum(0);
        ControlPanel.musicPlayer.setMaximum(music.length());

        // initialise the classes
        wave = new Wave(this);
        radial = new Radial(this);
        tunnel = new Tunnel(this, music);

        // play the music
        music.play();
    }

    public void draw() {
        // constant background
        background(30);

        // makes the track player in the control panel
        ControlPanel.musicPlayer.setValue(music.position());

        // will translate at (0,0) to have it across all corners
        if (showTunnel) tunnel.drawPoint(music);

        // will translate at the middle of the frame to center erery thing horizontally and vertically
        translate(width / 2, height / 2);
        if (showWave) wave.drawPoint(music);
        if (showRadial) radial.drawPoint(music);
        if (ControlPanel.rainbow) rainbowEffect();
    }

    private void rainbowEffect() {
        // start a new thread so it can keep drawing while im calling the thread.sleep method
        new Thread() {
            public void run() {
                if (switchColours) {
                    // store the Map<String, Integer> into a List of arrays
                    List<Integer> colourList = new ArrayList<Integer>(colours.values());
                    
                    // loop through each of the colours giving it a rainbow effect
                    for (Integer c : colourList) {
                        // break out the loop if rainbow is off
                        if (switchColours) {
                            // set all colours to the same colour
                            Wave.colour = c;
                            Wave.colour1 = c;
                            Radial.colour = c;
                            Tunnel.colour = c;

                            // setting it to false so it wont change the colour instantly
                            switchColours = false;

                            // random time to sleep
                            int time = (int) (Math.random() * (1200 - 700));

                            try { Thread.sleep(time); } catch (InterruptedException ignored) {} // change colours every between .7s to 1.2s

                            // after sleeping, time to pick a new colour
                            switchColours = true;      
                        } else {
                            break;
                        }
                    }  
                }
            }
        }.start(); // start the thread
    }

    private void setupColours() {
        colours.put("Red", color(255, 0, 0));
        colours.put("Blue", color(0, 0, 255));
        colours.put("Cyan", color(0, 255, 255));
        colours.put("Green", color(0, 255, 0));
        colours.put("Magenta", color(255, 0, 255));
        colours.put("Orange", color(255, 200, 0));
        colours.put("Pink", color(255, 175, 175));
        colours.put("Yellow", color(255, 255, 0));
    }
}

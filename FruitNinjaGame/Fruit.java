//NEED TO EDIT LATER
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Write a description of class Fruit here.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/16/2020
 */
public class Fruit extends Thread
{
    //private static Image fruitPic;

    // the filename that will be loaded into snowPic
    //private static final String snowPicFilename = "snow.gif";

    // its height should really be queried, but we will ignore that
    // complication for now
    private static final int fruitPicHeight = 24; 

    // delay between snow motions
    private static final int DELAY_TIME = 33;

    // what to add to ySpeed to simulate gravity?
    private static final double GRAVITY = 0.3;

    private Component panel;

    // pixels to move each iteration
    private double xSpeed, ySpeed;

    //bottom of panel
    private int bottom;

    // latest location of the ball
    private double upperLeftX, upperLeftY;

    //is the fuit done?
    private boolean done;

    /**
    Construct a new Fruitobject at the given position and speed.

    @param panel the Component in which this FallingSnow will live
    objects
     */
    public Fruit(Component panel) {
        this.panel = panel;

        bottom = panel.getHeight();

        upperLeftX = 100;
        upperLeftY = bottom;

        xSpeed = 5;

    }

    /**
    Draw the ball at its current location.

    @param g the Graphics object on which the ball should be drawn
     */
    public void paint(Graphics g) {

        g.fillOval((int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight);
    }

    /**
    Run method to define the life of this Fruit. EDIT LATER.
     */
    @Override
    public void run() {

        ySpeed = -6;

        while (upperLeftY < bottom) 
        {
            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }

            // every iteration, update the coordinates
            // by a pixel
            upperLeftX += xSpeed;
            upperLeftY += ySpeed;

            // gravity factor also
            ySpeed += GRAVITY;

        }
        
        done = true;

    }

    /**
    Check if this Fruits work is done.

    @return true if this Fruit work is done
     */
    public boolean done() 
    {
        return done;

    }

    // /**
    // Set the Image to be used by all FallingSnow objects, to be 
    // called by the main method before the GUI gets set up
    // */
    // public static void loadFruitPic() {

    // Toolkit toolkit = Toolkit.getDefaultToolkit();
    // FallingSnow.snowPic = toolkit.getImage(snowPicFilename);
    // }
}

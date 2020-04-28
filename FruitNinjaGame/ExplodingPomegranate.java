import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JComponent;
import java.util.Random;

/**
 * This ExplodingPomegranate class extends the abstract class 'Fruit' in order to have all of its proper
 * functionality. This class loads the proper bomb picture, so that when it is thrown,
 * a bomb image will appear on the screen.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public class ExplodingPomegranate extends Thread // extends Pomegranate
{
    protected static final int PIECE_SIZE = 50;
    
    private static Image fruitPiece;

    //max xSpeed 
    protected static final int MAX_X_SPEED = 10;

    //max ySpeed 
    protected static final int MAX_Y_SPEED = 5;

    //delay between fuit motions
    protected static final int DELAY_TIME = 33;

    //constant used for gravity
    protected static final double GRAVITY = 0.3;

    //the Component where we'll be creating Fruit
    protected JComponent panel;

    // pixels to move each iteration
    protected double xSpeed, ySpeed;

    //bottom of panel
    protected int bottom;

    // latest location of the ball
    protected double upperLeftX, upperLeftY;

    //is the fuit done?
    protected boolean done;

    //which side is fruit coming from: left = false, right = true
    protected boolean isRight; 

    /**
     * Construct a new Fruit object at the given position and speed.
     * 
     * @param panel the Component in which this Fruit will live
     */
    public ExplodingPomegranate(JComponent panel, int X, int Y) {
        this.panel = panel;

        bottom = panel.getHeight();

        upperLeftX = X;
        upperLeftY = Y;

        Random rand = new Random();

        ySpeed = rand.nextInt(MAX_Y_SPEED) - 7;

        isRight = rand.nextBoolean();
        if (isRight)
        {
            xSpeed = rand.nextInt(MAX_X_SPEED) - MAX_X_SPEED;
        }
        else
        {
            xSpeed = rand.nextInt(MAX_X_SPEED);
        }

    }

    /**
     * Will draw the pomegranate at a certain position. 
     * 
     * @param g the Graphics object in which to paint
     */

    public void paint(Graphics g)
    {
        g.drawImage(fruitPiece, (int)upperLeftX, (int)upperLeftY, PIECE_SIZE, PIECE_SIZE, null);
       // g.fillOval((int)upperLeftX, (int)upperLeftY, PIECE_SIZE, PIECE_SIZE);
    }

    /**
     * Run method to define the life of the Pomgranate Piece. It determines how fast it is being 'thrown'
     * along with determining the directon of the throw.
     */
    @Override
    public void run() {
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

            //include the gravity factor 
            ySpeed += GRAVITY;

            panel.repaint();

        }
        done = true;
    }

    /**
     * Check if this Fruits work is done.
     * 
     * @return true if this Fruits work is done
     */
    public boolean done() 
    {
        return done;
    }

    /**
     * Set the Image to be used by all ExplodingPomegranate objects, to be 
     * called by the main method before the GUI gets set up
     */
    public static void loadFruitPic() {
        //image is from the public domain website: https://www.clipartmax.com/
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ExplodingPomegranate.fruitPiece = toolkit.getImage("Images/pomegranatePiece.png");
    }

}

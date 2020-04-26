import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;
import java.util.Random;
/**
 * This abstract class is responsible for creating and animating the fruit/bombs. They will then
 * be painted with their individual classes.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public abstract class Fruit extends Thread
{
    //the height of each fruit. MAY HAVE TO EDIT COMMENT LATER
    protected  int fruitPicHeight = 100; 

    //max xSpeed 
    protected static final int MAX_X_SPEED = 10;

    //max ySpeed 
    protected static final int MAX_Y_SPEED = 5;

    //delay between fuit motions
    protected static final int DELAY_TIME = 33;

    //constant used for gravity
    protected static final double GRAVITY = 0.3;

    //the value of the point to tell if the user
    //has sliced a bomb or not
    protected int pointValue;

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

    //has the fruit been sliced?
    protected boolean isSliced;

    //is it a bomb?
    protected boolean isBomb;


    /**
     * Construct a new Fruit object at the given position and speed.
     * 
     * @param panel the Component in which this Fruit will live
     */
    public Fruit(JComponent panel) {
        this.panel = panel;

        bottom = panel.getHeight();
        upperLeftY = bottom - 1;
        isSliced = false;

        Random rand = new Random();

        ySpeed = rand.nextInt(MAX_Y_SPEED) - 17;

        isRight = rand.nextBoolean();
        if (isRight)
        {
            upperLeftX = rand.nextInt(350) + 700;
            xSpeed = rand.nextInt(MAX_X_SPEED) - MAX_X_SPEED;
        }
        else
        {
            upperLeftX = rand.nextInt(350);
            xSpeed = rand.nextInt(MAX_X_SPEED);
        }

    }

    /**
     * Abstract paint method that will change depending on which type of fruit or bomb is being thrown.
     * 
     * @param g the Graphics object in which to paint
     */
    public abstract void paint(Graphics g);

    /**
     * Run method to define the life of the Fruit. It determines how fast it is being 'thrown'
     * along with determining which side it will be thrown from. And if it sliced, it will fall 
     * through to the bottom.
     */
    @Override
    public void run() {

        //do if or switch statement for the diffLevel
        while (upperLeftY < bottom)

            while (upperLeftY < bottom) 

            {
                try {
                    sleep(DELAY_TIME);
                }
                catch (InterruptedException e) {
                }

                if(!isSliced)
                {
                    // every iteration, update the coordinates
                    // by a pixel
                    upperLeftX += xSpeed;
                }

                while (upperLeftY < bottom) 
                {
                    try {
                        sleep(DELAY_TIME);
                    }
                    catch (InterruptedException e) {
                    }

                    if(!isSliced)
                    {
                        // every iteration, update the coordinates
                        // by a pixel
                        upperLeftX += xSpeed;

                    }

                    upperLeftY += ySpeed;

                    //include the gravity factor 
                    ySpeed += GRAVITY;

                    panel.repaint();

                }
            }

        done = true;

    }

    /**
     * Check if the mouse overlaps the fruit.
     */
    public int mouseOverlapsFruit(Point mousePos)
    {
        if (mousePos == null) return 0;
        int radius = fruitPicHeight / 2;

        if (!isSliced && mousePos.distance(new Point((int) upperLeftX + radius, (int) upperLeftY  + radius)) < radius)
        {
            isSliced = true;
            ySpeed = 10;
            return pointValue;
        }

        return 0;
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
     * Check if this Fruit has been sliced or not
     * 
     * @return true if this Fruit has been sliced
     */
    public boolean isSliced()
    {
        return isSliced;
    }

    /**
     * Check if this Fruits is actually a bomb
     * 
     * @return true if this Fruit is a bomb
     */
    public boolean isBomb()
    {
        return isBomb;
    }

}
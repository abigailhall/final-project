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
    // its height should really be queried, but we will ignore that
    // complication for now
    protected  int fruitPicHeight = 100; 

    //max xSpeed easy
    protected static final int MAX_X_SPEED = 10;

    //max ySpeed easay
    protected static final int MAX_Y_SPEED = 5;

    //max xSpeed easy
    protected static final int MAX_X_SPEED_EASY = 10;

    //max ySpeed easay
    protected static final int MAX_Y_SPEED_EASY = 5;

    //max xSpeed medium
    protected static final int MAX_X_SPEED_MED = 10;

    //max ySpeed medium
    protected static final int MAX_Y_SPEED_MED = 5;

    //max xSpeed hard
    protected static final int MAX_X_SPEED_HARD = 10;

    //max ySpeed medium
    protected static final int MAX_Y_SPEED_HARD = 5;

    // delay between snow motions
    protected static final int DELAY_TIME = 33;

    // what to add to ySpeed to simulate gravity?
    protected static final double GRAVITY = 0.3;

    protected int pointValue;

    protected JComponent panel;

    // pixels to move each iteration
    protected double xSpeed, ySpeed;

    //bottom of panel
    protected int bottom;

    protected int diffLevel;

    // latest location of the ball
    protected double upperLeftX, upperLeftY;

    //is the fuit done?
    protected boolean done;

    // which side is fruit coming from: left = false, right = true
    protected boolean isRight; 

    // has the fruit been sliced?
    protected boolean isSliced;

    protected boolean isBomb;

    protected boolean explosion;

    protected boolean explosionOver;

    /**
    Construct a new Fruitobject at the given position and speed.
    @param panel the Component in which this FallingSnow will live
    objects
     */
    public Fruit(JComponent panel, int diffLevel) {
        this.panel = panel;
        this.diffLevel = diffLevel;

        bottom = panel.getHeight();
        upperLeftY = bottom - 1;
        isSliced = false;
        explosion = false;
        explosionOver = false;

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

    public abstract void paint(Graphics g);

    /**
    Run method to define the life of this Fruit. EDIT LATER.
     */
    @Override
    public void run() {

        //do if or switch statement for the diffLevel
        while (upperLeftY < bottom && !explosion) 

            while (upperLeftY < bottom && !explosionOver) 

            {
                try {
                    sleep(DELAY_TIME);
                }
                catch (InterruptedException e) {
                }
                if(explosion)
                {

                    int i = 0;
                    while(i < 25)
                    {
                        fruitPicHeight += 5;
                        try {
                            sleep(DELAY_TIME);
                        }
                        catch (InterruptedException e) {
                        }
                        i++;
                        panel.repaint();
                    }

                    explosionOver = true;
                }
                else if(!isSliced)
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
                    // gravity factor also
                    ySpeed += GRAVITY;

                    panel.repaint();

                }
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

    public boolean isSliced()
    {
        return isSliced;
    }

    public boolean isBomb()
    {
        return isBomb;
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

    public void setExplosion()
    {
        explosion = true;
    }

    public boolean explosionOver()
    {
        return explosionOver;
    }

}
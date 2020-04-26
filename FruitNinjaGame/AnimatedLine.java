import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
/**
 * This abstract class is responsible for creating and animating the sword, which will then
 * be painted with seperate classes.
 *
 * @author Kate Frisch, taken from the animations lab
 * @version Spring 2020
 */
public abstract class AnimatedLine extends Thread
{
    public static final int DELAY_TIME = 11;

    protected Point start;
    protected Point end;
    protected JComponent container;
    protected boolean done;

    /**
     * Construct a new AnimatedLine object at the given position.
     * 
     * @param start the starting point
     *        end the ends point
     *        panel the Component in which this Fruit will live
     *        diffLevel the difficulty level the user selects at the beginning of the game.
     */
    public AnimatedLine(Point start, Point end, JComponent container)
    {
        this.start = start;
        this.end = end;
        this.container = container;
    }

    /**
     * Abstract paint method that will change depending on which type of sword is chosen by 
     * the user.
     * 
     * @param g the Graphics object in which to paint
     */
    public abstract void paint(Graphics g);

    /**
     * Check if this AnimatedLine's work is done.
     * 
     * @return true if this AnimatedLine's work is done
     */
    public boolean done() 
    {
        return done;
    }

}

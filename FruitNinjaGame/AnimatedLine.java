import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
/**
 * Write a description of class AnimatedLine here.
 *
 * @author Kate Frisch
 * @version Spring 2020
 */
public abstract class AnimatedLine extends Thread
{
    public static final int DELAY_TIME = 33;

    protected Point start;
    protected Point end;
    protected JComponent container;
    protected boolean done;
    
    public AnimatedLine(Point start, Point end, JComponent container)
    {
        this.start = start;
        this.end = end;
        this.container = container;
    }

    public abstract void paint(Graphics g);

    public boolean done() 
    {
        return done;
    }

}

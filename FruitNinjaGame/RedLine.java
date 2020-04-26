import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
/**
 * This RedLine class extends the abstract class 'AnimatedLine' in order to have all of its proper
 * functionality so it appears as a sword. This class makes it so that the sword is red and it 
 * looks like it also vanishes while moving it.
 *
 * @author Kate Frisch, based heavily off of the animations lab
 * @version Spring 2020
 */
public class RedLine extends AnimatedLine
{

    public int colorNum;

    /**
     * Construct a new RedLine object at the given position.
     * 
     * @param start the starting point
     *        end the ends point
     *        panel the Component in which this Fruit will live
     */
    public RedLine(Point start, Point end, JComponent container)
    {
        super(start, end, container);
        colorNum = 255;
    }

    /**
     * Will draw the RedLine at given position. ColorNum is used to keep track of 
     * the decrease in color (red).
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g) 
    {
        g.setColor(new Color(colorNum, 0, 0));
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    /**
     * Run method to define the life of the RedLine. This method is what makes
     * it have its 'sword' appearance. 
     */
    @Override
    public void run() {

        while (colorNum >= 10) {

            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }

            colorNum = colorNum - 10;

            container.repaint();
        }

        done = true;
    }

}

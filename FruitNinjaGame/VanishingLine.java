import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
/**
 * This VanishingLine class extends the abstract class 'AnimatedLine' in order to have all of its proper
 * functionality so it appears as a sword. This class makes it so that the sword is just black, and so it
 * looks like it vanishes while moving it.
 *
 * @author Kate Frisch, taken from the animations lab
 * @version Spring 2020
 */
public class VanishingLine extends AnimatedLine
{
    public int colorNum;

    /**
     * Construct a new VanishingLine object at the given position.
     * 
     * @param start the starting point
     *        end the ends point
     *        panel the Component in which this Fruit will live
     */
    public VanishingLine(Point start, Point end, JComponent container)
    {
        super(start, end, container);
        colorNum = 0;
    }

    /**
     * Will draw the VanishingLine at given position. ColorNum is used to keep track of 
     * the increase color (black).
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g) 
    {
        g.setColor(new Color(colorNum, colorNum, colorNum));
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    /**
     * Run method to define the life of the VanishingLine. This method is what makes
     * it have its 'sword' appearance. 
     */
    @Override
    public void run() {

        while (colorNum < 245) {

            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }

            colorNum = colorNum + 10;

            container.repaint();
        }

        done = true;
    }

}

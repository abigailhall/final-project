import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
/**
 * This RainbowLine class extends the abstract class 'AnimatedLine' in order to have all of its proper
 * functionality so it appears as a sword. This class makes it so that the sword is rainbow, while also
 * having a vanishing appearance to it.
 *
 * @author Kate Frisch, taken from the animations lab
 * @version Spring 2020
 */
public class RainbowLine extends AnimatedLine
{
    private Color color;
    private int nextNum;
    private Point midpoint;
    private int xDist;
    private int yDist;

    /**
     * Construct a new RainbowLine object at the given position.
     * 
     * @param start the starting point
     *        end the ends point
     *        panel the Component in which this Fruit will live
     */
    public RainbowLine(Point start, Point end, JComponent container)
    {
        super(start, end, container);
        nextNum = 0;
        color = Color.RED;
        midpoint = new Point((start.x + end.x) / 2, (start.y + end.y) / 2);
        xDist = Math.abs(start.x - end.x);
        xDist = xDist < 5 ? 5 : xDist;
        yDist = Math.abs(start.y - end.y);
        yDist = yDist < 5 ? 5 : yDist;
    }

    /**
     * Will draw the RainbowLine at given position. Color will be set/determined
     * by the run method. It will have many colors, like a rainbow.
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g) 
    {

        g.setColor(color);
        g.fillRect(midpoint.x - (xDist / 2), midpoint.y - (yDist / 2), xDist, yDist);

    }

    /**
     * Run method to define the life of the RainbowLine. This method is what makes
     * it have different colors and have its 'sword' appearance. 
     */
    @Override
    public void run() {

        while (nextNum < 9) {

            try {
                sleep(DELAY_TIME * 2);
            }
            catch (InterruptedException e) {
            }

            switch(nextNum)
            {
                case 0:
                color = new Color(255, 69, 0);
                break;

                case 1:
                color = Color.ORANGE;
                break;

                case 2:
                color = Color.YELLOW;
                break;

                case 3:
                color = new Color(173, 255, 47);
                break;

                case 4:
                color = Color.GREEN;
                break;

                case 5:
                color = Color.CYAN;
                break;

                case 6:
                color = Color.BLUE;
                break;

                case 7:
                color = new Color(138, 43, 226);
                break;

                default:
                color = Color.MAGENTA;
                break;
            }

            nextNum++;
            container.repaint();
        }

        done = true;
    }

}

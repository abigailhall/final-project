import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

/**
 * Write a description of class ColorLine here.
 *
 * @author Kate Frisch
 * @version Spring 2020
 */
public class ColorLine extends AnimatedLine
{

    private Random rand;
    private int count;
    private Point midpoint;
    private int xDist;
    private int yDist;

    public ColorLine(Point start, Point end, JComponent container)
    {
        super(start, end, container);
        rand = new Random();
        count = 15;
        midpoint = new Point((start.x + end.x) / 2, (start.y + end.y) / 2);
        xDist = Math.abs(start.x - end.x);
        xDist = xDist < 5 ? 5 : xDist;
        yDist = Math.abs(start.y - end.y);
        yDist = yDist < 5 ? 5 : yDist;
    }

    public void paint(Graphics g) 
    {

        g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        g.fillRect(midpoint.x - (xDist / 2), midpoint.y - (yDist / 2), xDist, yDist);

    }
    @Override
    public void run() {

        while (count > 0) {

            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }

            count--;
            container.repaint();
        }

        done = true;
    }

}

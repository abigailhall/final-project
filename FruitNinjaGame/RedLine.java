import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

/**
 * Write a description of class RedLine here.
 *
 * @author Kate Frisch
 * @version Spring 2020
 */
public class RedLine extends AnimatedLine
{

    public int colorNum;

    public RedLine(Point start, Point end, JComponent container)
    {
        super(start, end, container);
        colorNum = 255;
    }

    public void paint(Graphics g) 
    {
        g.setColor(new Color(colorNum, 0, 0));
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    @Override
    public void run() {

        while (colorNum > 0) {

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

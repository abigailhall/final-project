import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

/**
 * Write a description of class BlueLine here.
 *
 * @author Kate Frisch
 * @version Spring 2020
 */
public class BlueLine extends AnimatedLine
{

    public int colorNum;

    public BlueLine(Point start, Point end, JComponent container)
    {
        super(start, end, container);
        colorNum = 255;
    }

    public void paint(Graphics g) 
    {
        g.setColor(new Color(0, 0, colorNum));
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

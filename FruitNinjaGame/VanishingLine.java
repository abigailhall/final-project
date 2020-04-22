import java.awt.Point;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Write a description of class VanishingLine here.
 *
 * @author Kate Frisch
 * @version Spring 2020
 */
public class VanishingLine extends AnimatedLine
{

    public int colorNum;

    public VanishingLine(Point start, Point end, JComponent container)
    {
        super(start, end, container);
        colorNum = 0;
    }

    public void paint(Graphics g) 
    {
        g.setColor(new Color(colorNum, colorNum, colorNum));
        g.drawLine(start.x, start.y, end.x, end.y);
        g.setColor(Color.BLACK);
    }

    @Override
    public void run() {
        try {
            sleep(100);
        }
        catch (InterruptedException e) {
        }
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

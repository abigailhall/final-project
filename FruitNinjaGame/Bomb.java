import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JComponent;
/**
 * This Bomb class extends the abstract class 'Fruit' in order to have all of its proper
 * functionality. This class loads the proper bomb picture, so that when it is thrown,
 * a bomb image will appear on the screen.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public class Bomb extends Fruit
{
    private static Image fruitPic;

    /**
     * Construct a new Bomb object.
     * 
     * @param panel the Component in which this Fruit will live
     *        diffLevel the difficulty level the user selects at the beginning of the game.
     */
    public Bomb(JComponent panel, int diffLevel)
    {
        super(panel, diffLevel);
        isBomb = true;
        pointValue = -1;
    }

    /**
     * Will draw the bomb at a certain position. 
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g)
    {
        g.drawImage(fruitPic, (int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight, null);
    }

    /**
     * Set the Image to be used by all Bomb objects, to be 
     * called by the main method before the GUI gets set up
     */
    public static void loadFruitPic() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Bomb.fruitPic = toolkit.getImage("bomb.png");
    }

}

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JComponent;
/**
 * This Apple class extends the abstract class 'Fruit' in order to have all of its proper
 * functionality. This class loads the proper Apple picture, so that when it is thrown,
 * an apple image will appear on the screen. When it is sliced, a sliced image will appear.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public class Apple extends Fruit
{
    private static Image fruitPic;
    private static Image fruitSlice;

    /**
     * Construct a new Apple object.
     * 
     * @param panel the Component in which this Fruit will live
     *        diffLevel the difficulty level the user selects at the beginning of the game.
     */
    public Apple(JComponent panel, int diffLevel)
    {
        super(panel, diffLevel);
        isBomb = false;
        pointValue = 1;

    }

    /**
     * Will draw the apple at a certain position if it has not been sliced yet.
     * If it has been sliced, an image of a sliced apple will appear. 
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g)
    {
        if(isSliced)
            g.drawImage(fruitSlice, (int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight, null);
        else
            g.drawImage(fruitPic, (int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight, null);
    }

    /**
     * Set the Image to be used by all Apple objects (either whole
     * or sliced), to be called by the main method before 
     * the GUI gets set up.
     */
    public static void loadFruitPic() {
        //images are from the public domain website: https://www.clipartmax.com/
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Apple.fruitPic = toolkit.getImage("apple.png");
        Apple.fruitSlice = toolkit.getImage("appleSlice.png");

    }

}

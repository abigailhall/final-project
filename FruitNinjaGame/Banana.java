import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JComponent;
/**
 * This Banana class extends the abstract class 'Fruit' in order to have all of its proper
 * functionality. This class loads the proper Banana picture, so that when it is thrown,
 * a banana image will appear on the screen. When it is sliced, a sliced image will appear.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public class Banana extends Fruit
{
    private static Image fruitPic;
    private static Image fruitSlice;

    /**
     * Construct a new Banana object.
     * 
     * @param panel the Component in which this Banana will live
     */
    public Banana(JComponent panel)
    {
        super(panel);
        isBomb = false;
        pointValue = 1;
    }

    /**
     * Will draw the banana at a certain position if it has not been sliced yet.
     * If it has been sliced, an image of a sliced banana will appear. 
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
     * Set the Image to be used by all Banana objects (either whole
     * or sliced), to be called by the main method before 
     * the GUI gets set up.
     */
    public static void loadFruitPic() {
        //images are from the public domain website: https://www.clipartmax.com/
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Banana.fruitPic = toolkit.getImage("Images/banana.png");
        Banana.fruitSlice = toolkit.getImage("Images/bananaSlice.png");
    }

}

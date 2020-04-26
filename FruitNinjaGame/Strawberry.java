import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JComponent;
/**
 * This Strawberry class extends the abstract class 'Fruit' in order to have all of its proper
 * functionality. This class loads the proper Strawberry picture, so that when it is thrown,
 * a strawberry image will appear on the screen. When it is sliced, a sliced image will appear.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/24/2020
 */
public class Strawberry extends Fruit
{
    private static Image fruitPic;
    private static Image fruitSlice;

    /**
     * Construct a new Strawberry object.
     * 
     * @param panel the Component in which this Strawberry will live
     *        diffLevel the difficulty level the user selects at the beginning of the game.
     */
    public Strawberry(JComponent panel, int diffLevel)
    {
        super(panel, diffLevel);
        isBomb = false;
        pointValue = 1;
    }

    /**
     * Will draw the strawberry at a certain position if it has not been sliced yet.
     * If it has been sliced, an image of a sliced strawberry will appear. 
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
     * Set the Image to be used by all Strawberry objects (either whole
     * or sliced), to be called by the main method before 
     * the GUI gets set up.
     */
    public static void loadFruitPic() {
        //images are from the public domain website: https://www.clipartmax.com/
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Strawberry.fruitPic = toolkit.getImage("strawberry.png");
        Strawberry.fruitSlice = toolkit.getImage("strawberrySlice.png");
    }

}

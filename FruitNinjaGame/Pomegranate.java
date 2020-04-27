import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JComponent;
/**
 * This Pomegranate class extends the abstract class 'Fruit' in order to have all of its proper
 * functionality. This class loads the proper bomb picture, so that when it is thrown,
 * a bomb image will appear on the screen.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public class Pomegranate extends Fruit
{
    private static Image fruitPic;
    private static Image fruitSlice;

    /**
     * Construct a new Pomegranate object.
     * 
     * @param panel the Component in which this Pomegranate will live
     *        diffLevel the difficulty level the user selects at the beginning of the game.
     */
    public Pomegranate(JComponent panel)
    {
        super(panel);
        isBomb = false;
        pointValue = 10;
    }

    /**
     * Will draw the pomegranate at a certain position. 
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
     * Set the Image to be used by all Pomegranate objects, to be 
     * called by the main method before the GUI gets set up
     */
    public static void loadFruitPic() {
        //image is from the public domain website: https://www.clipartmax.com/
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Pomegranate.fruitPic = toolkit.getImage("Image/pomegranate.png");
        Pomegranate.fruitSlice = toolkit.getImage("Image/pomegranatePiece.png");
    }

}

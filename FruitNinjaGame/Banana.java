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
     * Constructor for objects of class Cantaloupe
     */
    public Banana(JComponent panel, int diffLevel)
    {
        super(panel, diffLevel);
        isBomb = false;
        pointValue = 1;

    }

    public void paint(Graphics g)
    {
        if(isSliced)
            g.drawImage(fruitSlice, (int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight, null);

        else
            g.drawImage(fruitPic, (int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight, null);
    }

    public static void loadFruitPic() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Banana.fruitPic = toolkit.getImage("banana.png");
        Banana.fruitSlice = toolkit.getImage("bananaSlice.png");
    }

}

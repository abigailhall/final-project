//NEED TO EDIT LATER
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
/**
 * Write a description of class Cantaloupe here.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version Spring 2020
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

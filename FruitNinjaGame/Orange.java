//NEED TO EDIT LATER
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
/**
 * Write a description of class Orange here.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version (a version number or a date)
 */
public class Orange extends Fruit
{

    /**
     * Constructor for objects of class Orange
     */
    public Orange(JComponent panel, int diffLevel)
    {
        super(panel, diffLevel);
        isBomb = false;
        fruitColor = Color.ORANGE;
        pointValue = 1;
        
    }

    public void paint(Graphics g)
    {
        g.drawImage(fruitPic, (int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight, null);
    }


    public static void loadFruitPic() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Fruit.fruitPic = toolkit.getImage("orange.png");
    }

}

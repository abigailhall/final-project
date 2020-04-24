//NEED TO EDIT LATER
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
/**
 * Write a description of class Bomb here.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version (a version number or a date)
 */
public class Bomb extends Fruit
{

    /**
     * Constructor for objects of class Bomb
     */
    public Bomb(JComponent panel)
    {
        super(panel);
        isBomb = true;
        fruitColor = Color.BLACK;
        pointValue = -1;
    }


}

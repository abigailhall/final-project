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
    }

    /**
    Draw the ball at its current location.
    @param g the Graphics object on which the ball should be drawn
     */
    @Override
    public void paint(Graphics g) {

        g.setColor(Color.GREEN);

        g.fillOval((int)upperLeftX, (int)upperLeftY, 15, 15);
       
    }

    /**
     * Check if the mouse overlaps the fruit.
     */
    @Override
    public int mouseOverlapsFruit(Point mousePos)
    {
        if (mousePos == null) return 0;
        int radius = fruitPicHeight / 2;

        if (!isSliced && mousePos.distance(new Point((int) upperLeftX + radius, (int) upperLeftY  + radius)) < radius)
        {
            isSliced = true;
            ySpeed = 10;
            return POINT_VALUE;
        }

        return -1;
    }

}

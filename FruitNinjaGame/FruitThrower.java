//NEED TO EDIT LATER
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class FruitThrower here.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/16/2020
 */
public class FruitThrower extends Thread
{
    // time between FRUIT
    private static final int FRUIT_INTERVAL = 900;

    // the Component where we'll be creating Fruit
    private JComponent panel;

    // list of Fruit objects that are the responsibility
    // of this class
    private java.util.List<Fruit> fruits;

    // mouse Position
    private Point mousePos;

    // check if done
    private boolean done;

    private int score;

    private int strikeCount;

    /**
    Construct a new FruitThrower, using the given component to pass along
    to its Fruit objects that it will create.

    @param panel the Component in which this FruitThrower will generate Fruit
    objects
     */
    public FruitThrower(JComponent panel) {

        this.panel = panel;
        fruits = new Vector<Fruit>();
        done = false;
    }

    /**
    Pass along a paint to all Fruit objects managed by this FruitThrower.

    @param g the Graphics object in which to paint
     */
    public void paint(Graphics g) {

        if (done) return;

        int i = 0; 
        while (i < fruits.size())
        {
            Fruit fruit = fruits.get(i);
            if (fruit.done() && !fruit.explosionOver())
            {

                if(!fruit.isSliced() && !fruit.isBomb())
                {
                    strikeCount++;

                    if(strikeCount == 3)
                    {
                        done = true;
                    }
                }
                fruits.remove(i);

            }
            
            else
            {

                int scoreVal = fruit.mouseOverlapsFruit(mousePos);

                if(scoreVal == -1)
                {

                    fruit.setExplosion();
                     done = true;
                }
                else
                {
                    score += scoreVal;
                }

                fruit.paint(g);
                g.setColor(Color.BLACK);
                i++;
            }
        }

    }

    /**
    Run method to define the life of this FruitThrower, which consists of
    generating Faruit objects for a while.
     */
    @Override
    public void run() 
    {
        Random r = new Random();

        while(!done)
        {
            try {
                sleep(FRUIT_INTERVAL);
            }
            catch (InterruptedException e) {
            }

            Fruit newFruit;
            switch (r.nextInt(10))
            {
                case 0:
                newFruit = new Bomb(panel);
                break;
                default: newFruit = new Orange(panel);
                break;
            }

            fruits.add(newFruit);
            newFruit.start();
        }

    }

    /**
    Check if this FruitThrower's work is done.

    @return true if this FruitThrower's work is done
     */
    public boolean done() {
        return done;
    }

    public void setMousePos(Point mousePos)
    {
        this.mousePos = mousePos;
    }

    public int getScore()
    {
        return score;
    }

    public int getStrikeCount()
    {
        return strikeCount;
    }

}

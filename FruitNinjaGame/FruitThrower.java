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
    private static final int FLAKE_INTERVAL = 900;

    // the Component where we'll be creating Fruit
    private Component panel;

    // list of Fruit objects that are the responsibility
    // of this class
    private java.util.List<Fruit> fruits;
    
    // check if done
    private boolean done;
    
    /**
       Construct a new FruitThrower, using the given component to pass along
       to its Fruit objects that it will create.

       @param panel the Component in which this FruitThrower will generate Fruit
       objects
    */
    public FruitThrower(Component panel) {

	this.panel = panel;
	fruits = new Vector<Fruit>();
	done = false;
    }

    /**
       Run method to define the life of this FruitThrower, which consists of
       generating Faruit objects for a while.
    */
    @Override
    public void run() 
    {


    }

    /**
       Pass along a paint to all Fruit objects managed by this FruitThrower.

       @param g the Graphics object in which to paint
    */
    public void paint(Graphics g) {

	if (done) return;
	
	for (Fruit fruit : fruits) {
	    //fruit.paint(g);
	}
    }
    
    /**
       Check if this FruitThrower's work is done.

       @return true if this FruitThrower's work is done
    */
    public boolean done() {
        return done;

    }
}

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JComponent;
import java.util.Random;
import java.util.Vector;
/**
 * The class that is responsible for creating and managing the life of the
 * fruit and bombs that are being thrown.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public class FruitThrower extends Thread
{
    //time between fruit
    private int fruit_interval = 900;

    //the Component where we'll be creating Fruit
    private JComponent panel;

    //the difficulty level that is chosen by the user
    private int diffLevel;

    //list of Fruit objects that are the responsibility
    //of this class
    private java.util.List<Fruit> fruits;

    //mouse Position
    private Point mousePos;

    //check if done
    private boolean done;

    //keeps track of the score
    private int score;

    //keeps track of the strike count
    private int strikeCount;

    /**
     * Construct a new FruitThrower, using the given component to pass along
     * to its Fruit objects that it will create.
     * 
     * @param panel the Component in which this FruitThrower will generate Fruit objects
     *        diffLevel the difficulty level the user selects at the beginning of the game.
     */
    public FruitThrower(JComponent panel, int diffLevel) {
        this.panel = panel;
        this.diffLevel = diffLevel;
        fruits = new Vector<Fruit>();
        done = false;

        if(diffLevel == 2)
        {
            fruit_interval = 600;
        }
        else if(diffLevel == 3)
        {
            fruit_interval = 300;
        }

    }

    /**
     * Pass along a paint to all Fruit objects managed by this FruitThrower.
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g) {

        if (done) return;

        int i = 0; 
        while (i < fruits.size())
        {
            Fruit fruit = fruits.get(i);

            if (fruit.done())
            {
                //If the fruit is not sliced and it is not a bomb
                //that means it is a strike, so the strike count must 
                //increased. Once there are 3 strikes, the game ends.
                if(!fruit.isSliced() && !fruit.isBomb())
                {
                    strikeCount++;

                    if(strikeCount == 3)
                    {
                        done = true;
                    }
                }

                //remove the fruit from the list
                fruits.remove(i);
            }

            else
            {
                int scoreVal = fruit.mouseOverlapsFruit(mousePos);

                //if the user hits a bomb, game over
                if(scoreVal == -1)
                {

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
     * Run method to define the life of this FruitThrower, which consists of
     * generating Fruit objects for a while.
     */
    @Override
    public void run() 
    {
        Random r = new Random();

        while(!done)
        {
            try {
                sleep(fruit_interval);
            }
            catch (InterruptedException e) {
            }

            //Randomly chooses what kind of fruit is thrown or if a bomb is thrown
            Fruit newFruit;
            
            if(r.nextInt(10) == 0)
            {
                newFruit = new Pomegranate(panel);
            }
            else
            {
                switch (r.nextInt(15 + diffLevel))
                {
                    case 0: case 1:
                    newFruit = new Orange(panel);
                    break;

                    case 2: case 3: 
                    newFruit = new Banana(panel);
                    break;

                    case 4: case 5:
                    newFruit = new Apple(panel);
                    break;

                    case 6: case 7: 
                    newFruit = new Watermelon(panel);
                    break;

                    case 8: case 9: 
                    newFruit = new Strawberry(panel);
                    break;

                    case 10: case 11: 
                    newFruit = new Peach(panel);
                    break;

                    case 12: case 13: 
                    newFruit = new Avocado(panel);
                    break;

                    default: 
                    newFruit = new Bomb(panel);
                    break;
                }
            }

            fruits.add(newFruit);
            newFruit.start();
        }

    }

    /**
     * Check if this FruitThrower's work is done.
     * 
     * @return true if this FruitThrower's work is done
     */
    public boolean done() {
        return done;
    }

    /**
     * Mutator method to set the mouse position
     * 
     * @param mousePoint, the current mouse position
     */
    public void setMousePos(Point mousePos)
    {
        this.mousePos = mousePos;
    }

    /**
     * Accessor method which returns the current score of the game
     * 
     * @return score, the score of the game
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Accessor method which returns the number of strikes the
     * user currently has.
     * 
     * @return strikeCount, the number of strikes
     */
    public int getStrikeCount()
    {
        return strikeCount;
    }

}

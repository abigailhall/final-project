import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JComponent;
import java.util.ArrayList;

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
    private ArrayList<ExplodingPomegranate> pieces;

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
        pointValue = 15;
        pieces = new ArrayList<ExplodingPomegranate>();

    }

    /**
     * Will draw the pomegranate at a certain position. 
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g)
    {
        if(isSliced)
        {   
            int i = 0; 
            while (i < pieces.size())
            {
                ExplodingPomegranate pom = pieces.get(i);
                if(pom.done())
                {
                    pieces.remove(i);
                }
                else
                {
                    pom.paint(g);
                    i++;
                }
            }
        }
        else
        {
            g.drawImage(fruitPic, (int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight, null);
        }
    }

    /**
     * Run method to define the life of the Pomgranate. It determines how fast it is being 'thrown'
     * along with determining which side it will be thrown from. And if it sliced, it will explode.
     */
    @Override
    public void run() {

        while (upperLeftY < bottom && !isSliced) 
        {
            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }

            // every iteration, update the coordinates
            // by a pixel
            upperLeftX += xSpeed;

            upperLeftY += ySpeed;

            //include the gravity factor 
            ySpeed += GRAVITY;

            panel.repaint();
        }
        if(isSliced)
        {
            for(int i = 0; i < 7; i++)
            {
                ExplodingPomegranate pom = new ExplodingPomegranate(panel, (int)upperLeftX, (int)upperLeftY);
                pieces.add(pom);
                pom.start();
            }
            while(!pieces.isEmpty())
            {
                panel.repaint();
            }
        }
        done = true;
    }

    /**
     * Set the Image to be used by all Pomegranate objects, to be 
     * called by the main method before the GUI gets set up
     */
    public static void loadFruitPic() {
        //image is from the public domain website: https://www.clipartmax.com/
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Pomegranate.fruitPic = toolkit.getImage("Images/pomegranate.png");
        ExplodingPomegranate.loadFruitPic();
    }

}

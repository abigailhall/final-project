import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JComponent;
/**
 * This ExplodingPomegranate class extends the abstract class 'Fruit' in order to have all of its proper
 * functionality. This class loads the proper bomb picture, so that when it is thrown,
 * a bomb image will appear on the screen.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public class ExplodingPomegranate extends Pomegranate
{
    protected static final int PIECE_SIZE = 10;
    private static Image fruitPiece;

    /**
     * Construct a new ExplodingPomegranate object.
     * 
     * @param panel the Component in which this ExplodingPomegranate will live
     *        diffLevel the difficulty level the user selects at the beginning of the game.
     */
    public ExplodingPomegranate(JComponent panel, int X, int Y)
    {
        super(panel);
        upperLeftX = X;
        upperLeftY = Y;
    }

    /**
     * Will draw the pomegranate at a certain position. 
     * 
     * @param g the Graphics object in which to paint
     */
    public void paint(Graphics g)
    {
        g.drawImage(fruitPiece, (int)upperLeftX, (int)upperLeftY, fruitPicHeight, fruitPicHeight, null);

    }

    /**
     * Run method to define the life of the Pomgranate. It determines how fast it is being 'thrown'
     * along with determining which side it will be thrown from. And if it sliced, it will explode.
     */
    @Override
    public void run() {

        while (upperLeftY < bottom)

            while (upperLeftY < bottom) 
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
        done = true;
    }

    /**
     * Set the Image to be used by all ExplodingPomegranate objects, to be 
     * called by the main method before the GUI gets set up
     */
    public static void loadFruitPic() {
        //image is from the public domain website: https://www.clipartmax.com/
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ExplodingPomegranate.fruitPiece = toolkit.getImage("Images/pomegranatePiece.png");
    }

}

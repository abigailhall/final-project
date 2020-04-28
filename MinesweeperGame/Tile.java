import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Image;

/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends Thread
{
    private final int SIZE = 50;

    private static Image flagPic;
    private static Image bombPic;

    private int number;
    private int row;
    private int col;
    private boolean isBomb;
    private Point upperLeft;
    private JComponent container;
    private boolean isPressed;
    private boolean tileExposed;
    private boolean flagTile;

    public Tile(int number, int row, int col, Point upperLeft, JComponent container)
    {
        this.number = number;
        this.row = row;
        this.col = col;
        if (number == -1)
        {
            isBomb = true;
        }
        else
        {
            isBomb = false;
        }
        this.upperLeft = upperLeft;
        this.container = container;
    }

    public int getNumber()
    {
        return number; 
    }

    public int getRow()
    {
        return row; 
    }

    public int getCol()
    {
        return col; 
    }

    public boolean isBomb()
    {
        return isBomb; 
    }

    public void setNumber(int number)
    {
        this.number = number;
        if (number == -1)
        {
            isBomb = true;
        }
    }

    public void incrementNumber()
    {
        number++;
    }

    public int getTileSize()
    {
        return SIZE;
    }

    public void paint(Graphics g)
    {
        if (!tileExposed && isPressed)
        {
            g.setColor(Color.GRAY);
            g.fillRect(upperLeft.x, upperLeft.y, SIZE, SIZE);
        }

        if (tileExposed)
        {
            if (isBomb)
            {
                g.setColor(Color.RED);
                g.fillRect(upperLeft.x, upperLeft.y, SIZE, SIZE);
            }
            else if (flagTile)
            {
                g.drawImage(flagPic, upperLeft.x, upperLeft.y, SIZE, SIZE, null);
            }
            else
            {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(upperLeft.x, upperLeft.y, SIZE, SIZE);
            }
        }

        g.setColor(Color.BLACK);
        g.drawRect(upperLeft.x, upperLeft.y, SIZE, SIZE);
    }

    public void press(boolean isPressed)
    {
        this.isPressed = isPressed;
    }

    public void showTile()
    {
        tileExposed = true;
    }

    public void plantFlag()
    {
        flagTile = true;
    }

    /**
     * Set the Images to be used by all Tile objectsto be called by the main method before 
     * the GUI gets set up.
     */
    public static void loadTilePic() {
        //images are from the public domain website: https://www.clipartmax.com/
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Tile.flagPic = toolkit.getImage("Flag.png");
        Tile.bombPic = toolkit.getImage("");

    }
}

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile
{
    private final int SIZE = 50;

    private static ImageIcon flagPic = new ImageIcon("Flag.png");
    private static ImageIcon bombPic = new ImageIcon("bomb.png");
    // private  ImageIcon Pic1 = new ImageIcon("Pic-1.png");
    // private  ImageIcon Pic2 = new ImageIcon("Pic-2.png");
    // private  ImageIcon Pic3 = new ImageIcon("Pic-3.png");
    // private ImageIcon Pic4 = new ImageIcon("Pic-4.png");
    // private  ImageIcon Pic5 = new ImageIcon("Pic-5.png");
    // private  ImageIcon Pic6 = new ImageIcon("Pic-6.png");
    // private  ImageIcon Pic7 = new ImageIcon("Pic-7.png");
    // private ImageIcon Pic8 = new ImageIcon("Pic-8.png");

    private int number;
    private int row;
    private int col;
    private boolean isBomb;
    private Point upperLeft;
    private JComponent container;
    private boolean isPressed;
    private boolean tileExposed;
    private boolean flagTile;
    private ImageIcon numberPic;

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

    public void getNumberPic()
    {
        switch (number)
        {
            case 1: 
            numberPic = new ImageIcon("1-pic.png");
            break;

            case 2: 
            numberPic = new ImageIcon("2-pic.png");
            break;

            case 3: 
            numberPic = new ImageIcon("3-pic.png");
            break;

            case 4: 
            numberPic = new ImageIcon("4-pic.png");
            break;

            case 5: 
            numberPic = new ImageIcon("5-pic.png");
            break;

            case 6: 
            numberPic = new ImageIcon("6-pic.png");
            break;

            case 7: 
            numberPic = new ImageIcon("7-pic.png");
            break;

            case 8: 
            numberPic = new ImageIcon("8-pic.png");
            break;
        }
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
        if (!isBomb)
        {
            number++;
            getNumberPic();
        }
    }

    public int getTileSize()
    {
        return SIZE;
    }

    public void paint(Graphics g) throws NullPointerException
    {
        if (!tileExposed && isPressed)
        {
            g.setColor(Color.GRAY);
            g.fillRect(upperLeft.x, upperLeft.y, SIZE, SIZE);
        }

        if (tileExposed)
        {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(upperLeft.x, upperLeft.y, SIZE, SIZE);
            if (isBomb) 
            {
                g.drawImage(bombPic.getImage(), upperLeft.x, upperLeft.y, SIZE, SIZE, null);
            }
            else if (number == 0)
            {
                
            }
            else
            {
                g.drawImage(numberPic.getImage(), upperLeft.x + 5, upperLeft.y + 5, SIZE - 10, SIZE -10, null);
            }
        }
        else
        {
            if (flagTile)
            {
                g.drawImage(flagPic.getImage(), upperLeft.x + 5, upperLeft.y + 5, SIZE - 10, SIZE -10, null);
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
        if (!flagTile && !tileExposed)
        {
            tileExposed = true;
            if (number == 0)
            {
                for (int currentRow = row - 1; currentRow <= row + 1; currentRow++)
                {
                    for (int currentCol = col - 1; currentCol <= col + 1; currentCol++)
                    {
                        try
                        {
                            MinesweeperWindow.tileArray[currentRow][currentCol].showTile();
                        }
                        catch (IndexOutOfBoundsException e)
                        {

                        }
                    }
                }
            }
        }

    }

    public void plantFlag()
    {
        flagTile = true;
        
        container.repaint();
    }

    public void removeFlag()
    {
        flagTile = false;
    }

    public boolean isFlagged()
    {
        return flagTile;
    }

    /**
     * Set the Images to be used by all Tile objectsto be called by the main method before 
     * the GUI gets set up.
     */
    public static void loadTilePic() {
        //images are from the public domain website: https://www.clipartmax.com/
        // Toolkit toolkit = Toolkit.getDefaultToolkit();
        // Tile.flagPic = toolkit.getImage("Flag.png");
        // Tile.bombPic = toolkit.getImage("bomb.png");
        // Tile.Pic1 = toolkit.getImage("1-pic.png");
        // Tile.Pic2 = toolkit.getImage("2-pic.png");
        // Tile.Pic3 = toolkit.getImage("3-pic.png");
        // Tile.Pic4 = toolkit.getImage("4-pic.png");
        // Tile.Pic5 = toolkit.getImage("5-pic.png");
        // Tile.Pic6 = toolkit.getImage("6-pic.png");
        // Tile.Pic7 = toolkit.getImage("7-pic.png");
        // Tile.Pic8 = toolkit.getImage("8-pic.png");
    }
}

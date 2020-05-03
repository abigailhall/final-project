import javax.swing.JComponent;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Write a description of class Tile here.
 *
 * @author Kate Frisch, Van Griffith, Abby Hall
 * @version 5/2/2020
 */
public class Tile
{
    public static final int SIZE = 35;

    private static ImageIcon flagPic = new ImageIcon("Flag.png");
    private static ImageIcon bombPic = new ImageIcon("bomb.png");

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

    /**
     * Creates a tile object.
     * 
     * @param number The number of bombs surrounding the tile.
     * @param row The row number of the tile.
     * @param col The column number of the tile.
     * @param upperLeft The coordinate of the upper left point of the tile to draw the tile.
     * @param container The panel the tiles exist on.
     */
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

    /**
     * Returns the number of bombs surrounding th tile.
     * 
     * @return The tile's number.
     */
    public int getNumber()
    {
        return number; 
    }

    /**
     * Sets the picture for the tile.
     */
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

/**
 * Returns the tile's row number.
 * 
 * @return The tile's row number.
 */
    public int getRow()
    {
        return row; 
    }

/**
 * Returns the tile's column number.
 * 
 * @return The tile's column number.
 */
    public int getCol()
    {
        return col; 
    }

    /**
     * Returns whether the tile is a bomb or not.
     * 
     * @return True if the tile is a bomb and flase otherwise.
     */
    public boolean isBomb()
    {
        return isBomb; 
    }

    /**
     * Sets the number of the tile and if the umber is -1 the tile is a bomb.
     * 
     * @param number The number of bombs surounding the tile.
     */
    public void setNumber(int number)
    {
        this.number = number;
        if (number == -1)
        {
            isBomb = true;
        }
    }

    /**
     * Increments the tile's number.
     */
    public void incrementNumber()
    {
        if (!isBomb)
        {
            number++;
            getNumberPic();
        }
    }

    /**
     * Returns the height and width of the tile.
     * 
     * @return The size of the tile.
     */
    public int getTileSize()
    {
        return SIZE;
    }

    /**
     * Paints the tile with the number, bomb, or flag.
     * 
     * @param g The graphics component that does the painting.
     * @throws NullPointerException
     */
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

    /**
     * Helps animate the tile by changing press to darken the tile.
     * 
     * @param isPressed True if the tile has been clicked, false.
     */
    public void press(boolean isPressed)
    {
        this.isPressed = isPressed;
    }

    /**
    * Show adjacent tiles if the tile is an empty tile.
    */
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
            MinesweeperWindow.tilesExposed++;
        }
    }

    /**
     * Places a flag on the tile.
     */
    public void plantFlag()
    {
        flagTile = true;
        
        container.repaint();
    }

    /**
     * Removes the flag from the tile.
     */
    public void removeFlag()
    {
        flagTile = false;
    }

    /**
     * Returns whether the flag is on the tile.
     * 
     * @return True if the tile is flagged and flase otherwise.
     */
    public boolean isFlagged()
    {
        return flagTile;
    }
    
    /**
     * Compares if the this tile is adjacent to the tile paramter.
     * 
     * @param other A tile object
     * @return True if the given tile is adjacent ot this tile and false otherwise.
     */
    public boolean isAdjacentTo(Tile other)
    {
        return (other.row == row - 1 ||
                other.row == row ||
                other.row == row + 1 ||
                other.col == col - 1 ||
                other.col == col ||
                other.col == col + 1);
    }
    
    /**
     * Returns if the tile is showing its number.
     * 
     * @return True if the tile is showing its number and false otherwise.
     */
    public boolean isExposed()
    {
        return tileExposed;
    }

}

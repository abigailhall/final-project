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
            MinesweeperWindow.tilesExposed++;
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
    
    public boolean isAdjacentTo(Tile other)
    {
        return (other.row == row - 1 ||
                other.row == row ||
                other.row == row + 1 ||
                other.col == col - 1 ||
                other.col == col ||
                other.col == col + 1);
    }
    
    public boolean isExposed()
    {
        return tileExposed;
    }

}

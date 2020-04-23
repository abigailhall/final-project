import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends Thread
{
    private final int SIZE = 50;
    
    private int number;
    private int row;
    private int col;
    private boolean isBomb;
    private Point upperLeft;
    private JComponent container;
    private boolean isPressed;
    

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
        if (isPressed)
        {
            g.setColor(Color.GRAY);
            g.fillRect(upperLeft.x, upperLeft.y, SIZE, SIZE);
        }
        g.setColor(Color.BLACK);
        g.drawRect(upperLeft.x, upperLeft.y, SIZE, SIZE);
    }
    
    public void press()
    {
        if (isPressed)
        {
            isPressed = false;
        }
        else
        {
            isPressed = true;
        }
    }
}

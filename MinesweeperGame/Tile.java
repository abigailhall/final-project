import javax.swing.JButton;
/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends JButton
{
    private int number;
    private int row;
    private int col;
    private boolean isBomb;
    
    public Tile(int number, int row, int col, boolean isBomb)
    {
        super();
        this.number = number;
        this.row = row;
        this.col = col;
        this.isBomb = isBomb;
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
    }
    
    public void incrementNumber()
    {
        number++;
    }
    
}

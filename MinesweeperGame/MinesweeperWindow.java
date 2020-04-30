//may have to edit later
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class GameWindow here.
 *
 * @author Kate Frisch, Van Griffith & Abby Hall
 * @version Spring 2020
 */
public class MinesweeperWindow extends MouseAdapter implements Runnable, ActionListener
{
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 500;
    private final int GAME_HEIGHT = 500;
    private final int MENU_HEIGHT = 100;
    private final int TILE_SIZE = 50;

    private final int BEGINNER = 1;
    private final int BEGINNER_WIDTH = 9;
    private final int BEGINNER_HEIGHT = 9;
    private final int BEGINNER_BOMBS = 10;

    private final int INTERMEDIATE = 2;
    private final int INTERMEDIATE_WIDTH = 16;
    private final int INTERMEDIATE_HEIGHT = 16;
    private final int INTERMEDIATE_BOMBS = 40;

    private final int EXPERT = 3;
    private final int EXPERT_WIDTH = 16;
    private final int EXPERT_HEIGHT = 30;
    private final int EXPERT_BOMBS = 99;

    private int difficulty;

    private int arrayWidth;
    private int arrayHeight;
    private int bombCount;
    public static Tile[][] tileArray;

    private JFrame gameFrame;
    private JPanel menuPanel;
    private JPanel mineField;
    private JButton faceButton;
    private JLabel timer;
    private JLabel bombLabel;
    private boolean gameStarted;

    private Tile currentTile;

    public void run()
    {

        //Creates and adds JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);

        gameFrame = new JFrame("Minesweeper");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, MENU_HEIGHT));
        gameFrame.add(menuPanel, BorderLayout.NORTH);

        faceButton = new JButton("New Game");
        faceButton.addActionListener(this);
        menuPanel.add(faceButton);

        timer = new JLabel("0");
        menuPanel.add(timer);

        bombLabel = new JLabel("0");
        menuPanel.add(bombLabel);

        newGame();

        mineField = new JPanel(new GridLayout(arrayWidth, arrayHeight)) {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                for (int row = 0; row < arrayWidth; row++)
                {
                    for (int col = 0; col < arrayHeight; col++)
                    {
                        Tile tile = tileArray[row][col];
                        try
                        {
                            tile.paint(g);
                        }
                        catch(NullPointerException e)
                        {
                        }
                    }
                }
            }
        };
        mineField.addMouseListener(this);
        gameFrame.add(mineField); 

        gameFrame.setPreferredSize(new Dimension(arrayWidth * TILE_SIZE + 20, MENU_HEIGHT + arrayHeight * TILE_SIZE + 40));
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    /**
     * Starts a new game
     */
    public void newGame()
    {

        difficulty = BEGINNER; // to be replaced with difficulty selection

        if (difficulty == BEGINNER)
        {
            arrayWidth = BEGINNER_WIDTH;
            arrayHeight = BEGINNER_HEIGHT;
            bombCount = BEGINNER_BOMBS;
        }
        else if (difficulty == INTERMEDIATE)
        {
            arrayWidth = INTERMEDIATE_WIDTH;
            arrayHeight = INTERMEDIATE_HEIGHT;
            bombCount = INTERMEDIATE_BOMBS;
        }
        else if (difficulty == EXPERT)
        {
            arrayWidth = EXPERT_WIDTH;
            arrayHeight = EXPERT_HEIGHT;
            bombCount = EXPERT_BOMBS;
        }

        tileArray = new Tile[arrayWidth][arrayHeight];

        Random rand = new Random();
      
        
        

        int upperLeftX = 0;
        int upperLeftY = MENU_HEIGHT;
        for (int row = 0; row < arrayWidth; row++)
        {
            upperLeftY = 0;
            for (int col = 0; col < arrayHeight; col++)
            {
                tileArray[row][col] = new Tile(0, row, col, new Point(upperLeftX, upperLeftY), mineField);
                upperLeftY += 50;
            }
            upperLeftX += 50;
        }
        
        if (!gameStarted)
        {
            currentTile = tileArray[arrayWidth / 2][arrayHeight / 2];
        }

        int i = 0;
        while (i < bombCount)
        {
            int row = rand.nextInt(arrayWidth);
            int col = rand.nextInt(arrayHeight);

            Tile tile = tileArray[row][col];

            if (!tile.isBomb() && !tile.isAdjacentTo(currentTile))
            {
                tile.setNumber(-1);
                incrementAdjacent(row, col);
                i++;
            }
        }


    }

    private void incrementAdjacent(int row, int col)
    {
        for (int currentRow = row - 1; currentRow <= row + 1; currentRow++)
        {
            for (int currentCol = col - 1; currentCol <= col + 1; currentCol++)
            {
                try
                {
                    tileArray[currentRow][currentCol].incrementNumber();
                }
                catch (IndexOutOfBoundsException e)
                {

                }
            }
        }
    }

    /**
     * Action performed event handler, handles the game start and game reset buttons.
     * 
     * @param e The ActionEvent object which calls the method.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == faceButton)
        {
            gameStarted = false;
            newGame();
            mineField.repaint();
        }
    }

    public void mousePressed (MouseEvent e)
    {
        Point mousePos = e.getPoint(); 
        int tileRow = mousePos.x / TILE_SIZE;
        int tileCol = mousePos.y / TILE_SIZE;

        try
        {
            currentTile = tileArray[tileRow][tileCol];
            currentTile.press(true);
            if (!gameStarted)
            {
                gameStarted = true;
                newGame();
            }
        }
        catch (ArrayIndexOutOfBoundsException k)
        {

        }

        mineField.repaint();
    }

    public void mouseReleased(MouseEvent e)
    {
        try
        {
            currentTile.press(false);

            if (SwingUtilities.isRightMouseButton(e))
            {
                if (currentTile.isFlagged())
                {
                    currentTile.removeFlag();
                }
                else
                {
                    currentTile.plantFlag();
                }

            }
            else
            {
                currentTile.showTile();
            }

        }
        catch (NullPointerException k)
        {

        }

        mineField.repaint();
        currentTile = null;
    }

    /**
     * Main method to run the program, allows user to select the color of their ball.
     * 
     * @param args[] no command line input necessary.
     */
    public static void main(String args[])
    {
        //Easy medium and hard settings will go here. 
        Tile.loadTilePic();

        javax.swing.SwingUtilities.invokeLater(new MinesweeperWindow());
    }
}

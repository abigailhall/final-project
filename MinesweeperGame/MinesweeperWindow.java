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

    private int arrayWidth = 9;
    private int arrayHeight = 9;
    private int bombCount = 10;
    private Tile[][] bombArray;

    private JFrame gameFrame;
    private JPanel menuPanel;
    private JPanel mineField;
    private JButton faceButton;
    private JLabel timer;
    private JLabel bombLabel;

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
                        Tile tile = bombArray[row][col];
                        tile.paint(g);
                    }
                }
            }
        };
        mineField.addMouseListener(this);
        gameFrame.add(mineField); 

        bombArray = new Tile[arrayWidth][arrayHeight];

        newGame();

        gameFrame.setPreferredSize(new Dimension(arrayWidth * TILE_SIZE + 20, MENU_HEIGHT + arrayHeight * TILE_SIZE + 40));

        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    /**
     * Starts a new game
     */
    public void newGame()
    {
        Random rand = new Random();

        int upperLeftX = 0;
        int upperLeftY = MENU_HEIGHT;
        for (int row = 0; row < arrayWidth; row++)
        {
            upperLeftY = 0;
            for (int col = 0; col < arrayHeight; col++)
            {
                bombArray[row][col] = new Tile(0, row, col, new Point(upperLeftX, upperLeftY), mineField);
                upperLeftY += 50;
            }
            upperLeftX += 50;
        }

        int i = 0;
        while (i < bombCount)
        {
            int row = rand.nextInt(arrayWidth);
            int col = rand.nextInt(arrayHeight);

            Tile tile = bombArray[row][col];

            if (!tile.isBomb())
            {
                tile.setNumber(-1);
                i++;
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

    }

    public void mousePressed (MouseEvent e)
    {
        Point mousePos = e.getPoint(); 
        int tileRow = mousePos.x / TILE_SIZE;
        int tileCol = mousePos.y / TILE_SIZE;

        
        try
        {
            currentTile = bombArray[tileRow][tileCol];
            currentTile.press(true);
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
                currentTile.plantFlag();
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

        javax.swing.SwingUtilities.invokeLater(new MinesweeperWindow());
    }
}


//may have to edit later
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
    private int TILE_SIZE = Tile.SIZE;

    private final int BEGINNER = 1;
    private final int BEGINNER_WIDTH = 9;
    private final int BEGINNER_HEIGHT = 9;
    private final int BEGINNER_BOMBS = 10;

    private final int INTERMEDIATE = 2;
    private final int INTERMEDIATE_WIDTH = 16;
    private final int INTERMEDIATE_HEIGHT = 16;
    private final int INTERMEDIATE_BOMBS = 40;

    private final int EXPERT = 3;
    private final int EXPERT_WIDTH = 30;
    private final int EXPERT_HEIGHT = 16;
    private final int EXPERT_BOMBS = 99;
    TimerClass timer;

    private ImageIcon faceBomb = new ImageIcon("face_bomb.png");
    private ImageIcon facePress = new ImageIcon("face_press.png");
    private ImageIcon faceShock = new ImageIcon("face_shock.png");
    private ImageIcon faceSmile = new ImageIcon("face_smile.png");
    private ImageIcon faceWin = new ImageIcon("face_win.png");

    private int difficulty;

    private int arrayWidth;
    private int arrayHeight;
    private int bombCount;
    public static Tile[][] tileArray;

    private JFrame gameFrame;
    private JPanel menuPanel;
    private JPanel mineField;
    private JButton faceButton;
    private JLabel timerLabel;
    private JLabel bombLabel;
    public static int tilesExposed;
    private int totalTiles;
    private int flagCount;

    private Tile currentTile;
    private boolean gameOver;
    private boolean gameStarted;

    public void run()
    {

        //Creates and adds JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);

        gameFrame = new JFrame("Minesweeper");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setLayout(new BorderLayout());

        menuPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                timerLabel.setText("Time: " + timer.getSeconds());
            }
        };
        menuPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, MENU_HEIGHT));
        gameFrame.add(menuPanel, BorderLayout.NORTH);

        bombLabel = new JLabel();
        menuPanel.add(bombLabel);

        faceButton = new JButton(faceSmile);
        faceButton.addActionListener(this);
        menuPanel.add(faceButton);

        timerLabel = new JLabel("Time: ");
        menuPanel.add(timerLabel);

        //Create the panel
        mineField = new JPanel() {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                if (gameStarted)
                {
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
                else
                {
                    for (int row = 0; row < arrayWidth; row++)
                    {
                        for (int col = 0; col < arrayHeight; col++)
                        {
                            g.drawRect(row * TILE_SIZE, col * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        }
                    }
                }
            }
        };
        mineField.addMouseListener(this);
        gameFrame.add(mineField); 
        newGame();
        //preGameSetup();

        gameFrame.setPreferredSize(new Dimension(arrayWidth * TILE_SIZE + 20, MENU_HEIGHT + arrayHeight * TILE_SIZE + 40));
        gameFrame.pack();
        gameFrame.setVisible(true);
    }
    
    /**
     * Will draw a dummy board beforehand so that the players first move can consistently be blank
     */
    private void preGameSetup()
    {
        chooseDifficulty();
    }
    
    /**
     * Choose the difficulty fot the game
     */
    private void chooseDifficulty()
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
        totalTiles = arrayWidth * arrayHeight;
        tileArray = new Tile[arrayWidth][arrayHeight];
    }
    

    /**
     * Starts a new game
     */
    public void newGame()
    {

        gameOver = false;
        flagCount = bombCount;
        bombLabel.setText("Flags: " + flagCount);

        Random rand = new Random();

        int upperLeftX = 0;
        int upperLeftY = MENU_HEIGHT;
        for (int row = 0; row < arrayWidth; row++)
        {
            upperLeftY = 0;
            for (int col = 0; col < arrayHeight; col++)
            {
                tileArray[row][col] = new Tile(0, row, col, new Point(upperLeftX, upperLeftY), mineField);
                upperLeftY += TILE_SIZE;
            }
            upperLeftX += TILE_SIZE;
        }

        int i = 0;
        while (i < bombCount)
        {
            int row = rand.nextInt(arrayWidth);
            int col = rand.nextInt(arrayHeight);

            Tile tile = tileArray[row][col];

            if (!tile.isBomb())
            {
                tile.setNumber(-1);
                incrementAdjacent(row, col);
                i++;
            }
        }

        timer = new TimerClass(menuPanel);
        timer.start();
        tilesExposed = 0;

        faceButton.setIcon(faceSmile);
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
     * Action performed event handler, handles the face button for restarting the game
     * 
     * @param e The ActionEvent object which calls the method.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == faceButton)
        {
            faceButton.setIcon(facePress);
            newGame();
            mineField.repaint();
        }
    }

    /**
     * Mouse pressed event handler, this will draw the tiles a dark gray while clicking them to provide feedback 
     * on what tile is being clicked on
     * 
     * @param e The MouseEvent object which calls the method
     */
    public void mousePressed (MouseEvent e)
    {
        if(!gameOver)
        {
            Point mousePos = e.getPoint(); 
            int tileRow = mousePos.x / TILE_SIZE;
            int tileCol = mousePos.y / TILE_SIZE;

            faceButton.setIcon(faceShock);

            try
            {
                currentTile = tileArray[tileRow][tileCol];
                currentTile.press(true);
            }
            catch (ArrayIndexOutOfBoundsException k)
            {

            }

            mineField.repaint();
        }
    }

    /**
     * Mouse Released Event Handler which handles exposing tiles, marking flags
     * 
     * @param e The MouseEvent object which calls the method
     */
    public void mouseReleased(MouseEvent e)
    {
        try
        {
            currentTile.press(false);

            faceButton.setIcon(faceSmile);

            if (SwingUtilities.isRightMouseButton(e))
            {
                if (currentTile.isFlagged())
                {
                    currentTile.removeFlag();
                    flagCount++;
                }
                else
                {
                    currentTile.plantFlag();
                    flagCount--;
                }
                bombLabel.setText("Flags: " + flagCount);
            }
            else
            {
                currentTile.showTile();
                if(currentTile.isBomb() && !currentTile.isFlagged())
                {
                    lose();
                }
            }

            if (tilesExposed + bombCount == totalTiles)
            {
                win();
            }

        }
        catch (NullPointerException k)
        {

        }

        mineField.repaint();
        currentTile = null;
    }

    /**
     * Call when the player's won the game
     */
    private void win()
    {
        faceButton.setIcon(faceWin);
        gameOver = true;
        System.out.println("You won");
        timer.stopTimer();
    }

    /**
     * Called when the player loses the game
     */
    private void lose()
    {
        faceButton.setIcon(faceBomb);
        gameOver = true;
        System.out.println("You lost");
        for (int row = 0; row < arrayWidth; row++)
        {
            for (int col = 0; col < arrayHeight; col++)
            {
                Tile tile = tileArray[row][col];
                if (tile.isBomb())
                {
                    tile.removeFlag();
                    tile.showTile();
                }
            }
        }
        timer.stopTimer();
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

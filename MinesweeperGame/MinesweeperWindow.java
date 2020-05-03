import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.util.Random;

/**
 * A java version/simulation of the microsoft game Minesweeper. To play this game, the user must first select a level of difficulty 
 * (beginner, intermediate, or expert). Then you must click any spot so the game board will be made. 
 * Some squares will contain mines (bombs) while others do not. The objective of the game is to click all of the squares without hitting any bombs.
 * Left clicking a square (that is not a bomb) will tell you the number of neighboring squares that are touching bombs. A square can be touching
 * a bomb if it is adjacent above, below, left, right, and all 4 diagonals. To mark a square you think is a bomb, right click on it. 
 *
 * @author Kate Frisch, Van Griffith & Abby Hall
 * @version 5/2/2020
 */
public class MinesweeperWindow extends MouseAdapter implements Runnable, ActionListener
{
    private final int WINDOW_WIDTH = 500;
    private final int MENU_HEIGHT = 100;
    private int TILE_SIZE = Tile.SIZE;

    private static final int BEGINNER = 1;
    private final int BEGINNER_WIDTH = 9;
    private final int BEGINNER_HEIGHT = 9;
    private final int BEGINNER_BOMBS = 10;

    private static final int INTERMEDIATE = 2;
    private final int INTERMEDIATE_WIDTH = 16;
    private final int INTERMEDIATE_HEIGHT = 16;
    private final int INTERMEDIATE_BOMBS = 40;

    private static final int EXPERT = 3;
    private final int EXPERT_WIDTH = 30;
    private final int EXPERT_HEIGHT = 16;
    private final int EXPERT_BOMBS = 99;
    TimerClass timer;

    private ImageIcon faceBomb = new ImageIcon("face_bomb.png");
    private ImageIcon facePress = new ImageIcon("face_press.png");
    private ImageIcon faceShock = new ImageIcon("face_shock.png");
    private ImageIcon faceSmile = new ImageIcon("face_smile.png");
    private ImageIcon faceWin = new ImageIcon("face_win.png");

    private static int difficulty;

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
    private JLabel gameOverLabel;
    private JLabel winLabel;
    public static int tilesExposed;
    private int totalTiles;
    private int flagCount;

    private Tile currentTile;
    private boolean gameOver;
    private boolean gameStarted;
    private boolean fakePress;
    private Point fakePressPos;

    /**
     * The run method establishes the graphical user interface of the game itself.
     */
    public void run()
    {

        //Creates and adds JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);
        gameFrame = new JFrame("Minesweeper");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setLayout(new BorderLayout());

        // Creates and adds menuPanel to Jframe
        menuPanel = new JPanel(){
            /**
             *  This method will display the timer on menuPanel so the user knows how lon
             *  the game is taking them.
             *  
             *  @param g The Graphics component which will do the painting
             */
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                timerLabel.setText("Time: " + timer.getSeconds());
            }
        };
        menuPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, MENU_HEIGHT));
        gameFrame.add(menuPanel, BorderLayout.NORTH);

        //Creates and adds buttons and labels to the menuPanel
        bombLabel = new JLabel();
        menuPanel.add(bombLabel);

        faceButton = new JButton(faceSmile);
        faceButton.addActionListener(this);
        menuPanel.add(faceButton);

        timerLabel = new JLabel("Time: ");
        menuPanel.add(timerLabel);

        mineField = new JPanel() { 
            /**
             *  This method will redraw the proper size of the gamboard and display 
             *  it in the window.
             *  
             *  @param g The Graphics component which will do the painting
             */
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
                    if (fakePress)
                    {
                        g.setColor(Color.GRAY);
                        g.fillRect((fakePressPos.x / TILE_SIZE) * TILE_SIZE, (fakePressPos.y / TILE_SIZE) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        g.setColor(Color.BLACK);
                    }
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

        //Created and edit a game over label so it appears in the correct part of the panel
        gameOverLabel = new JLabel("YOU LOST! PRESS THE SMILEY FACE TO PLAY AGAIN!");
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVisible(false);
        menuPanel.add(gameOverLabel);

        //Created and edit a game over label so it appears in the correct part of the panel
        winLabel = new JLabel("YOU WON! PRESS THE SMILEY FACE TO PLAY AGAIN!");
        winLabel.setForeground(Color.RED);
        winLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winLabel.setVisible(false);
        menuPanel.add(winLabel);

        mineField.addMouseListener(this);
        gameFrame.add(mineField); 
        //newGame();
        preGameSetup();

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
        gameOver = false;
        gameStarted = false;
        tilesExposed = 0;
        timer = new TimerClass(menuPanel);
        faceButton.setIcon(faceSmile);
    }

    /**
     * Choose the difficulty fot the game
     */
    private void chooseDifficulty()
    {
        //Difficulty level is chosen by the user at the start of the game
        //The size of the gameboard is decided by the level the user picks
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
        flagCount = bombCount;
        bombLabel.setText("Flags: " + flagCount);
    }

    /**
     * Starts a new game
     */
    public void newGame()
    {
        gameOverLabel.setVisible(false);
        winLabel.setVisible(false);
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

        Tile startTile = tileArray[fakePressPos.x / TILE_SIZE][fakePressPos.y / TILE_SIZE];
        //Randomly places the bombs in appropriate places
        Random rand = new Random();
        int i = 0;
        while (i < bombCount)
        {
            int row = rand.nextInt(arrayWidth);
            int col = rand.nextInt(arrayHeight);

            Tile tile = tileArray[row][col];

            if (!tile.isBomb() && !tile.isAdjacentTo(startTile))
            {
                tile.setNumber(-1);
                incrementAdjacent(row, col);
                i++;
            }
        }
        timer.start();
        startTile.showTile();
    }

    /**
     * This method increments the adjacent tiles of a bomb. EDIT?
     * 
     * @param int row the given row
     *        int col the given coloumn
     */
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
     * Action performed event handler, handles the reset game smiley face button.
     * 
     * @param e The ActionEvent object which calls the method.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == faceButton)
        {
            faceButton.setIcon(facePress);
            preGameSetup();
            mineField.repaint();
            gameOverLabel.setVisible(false);
            winLabel.setVisible(false);
        }
    }

    /**
     * Mouse pressed event handler gets the current location of the mouse.
     * Meaning, the tile that has been clicked.
     * 
     * @param e The MouseEvent object which calls the method.
     */
    public void mousePressed (MouseEvent e)
    {
        if(!gameOver)
        {
            if(gameStarted)
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
            else
            {
                Point mousePos = e.getPoint();
                fakePress = true;
                fakePressPos = mousePos;
                mineField.repaint();
            }
        }

    }

    /**
     * Mouse released event handler allows the user to play the game. If the user right clicks
     * on a square it will show a bomb; if they left click they will either hit a safe tile or a bomb.
     * The proper smiley face icon will also appear on the button depending on what type of square the
     * user presses/releases from.
     * 
     * @param e The MouseEvent object which calls the method.
     */
    public void mouseReleased(MouseEvent e)
    {
        try
        {
            if (gameStarted)
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
            else
            {
                fakePress = false;
                gameStarted = true;
                newGame();
            }
        }
        catch (NullPointerException k)
        {

        }

        mineField.repaint();
        currentTile = null;
    }

    /**
     * If the user wins the game, the face on the button will smile, the timer will stop, 
     * and a 'you won' message will appear. 
     */
    private void win()
    {
        faceButton.setIcon(faceWin);
        gameOver = true;
        winLabel.setVisible(true);
        timer.stopTimer();
    }

    /**
     * If the user loses the game, the proper icon will appear on the button, the timer will stop, 
     * and a 'you lost' message will appear. 
     */
    private void lose()
    {
        faceButton.setIcon(faceBomb);
        gameOver = true;
        gameOverLabel.setVisible(true);
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
        //User will select Difficulty
        String[] diffOpts = {"Beginner", "Intermediate", "Expert"};

        Object selectedValue1 = JOptionPane.showInputDialog(null,
                "Choose a level of Difficulty: ", "Welcome to Minesweeper!",
                JOptionPane.PLAIN_MESSAGE, null,
                diffOpts, diffOpts[0]);

        String chosenDiff = (String) selectedValue1;
        try
        {
            switch(chosenDiff)
            {
                case "Beginner":  difficulty =  BEGINNER;
                break;
                case "Intermediate": difficulty = INTERMEDIATE;
                break;
                case "Expert": difficulty = EXPERT;
                break;
            }
        }
        catch(NullPointerException e)
        {
            System.exit(1);
        }

        javax.swing.SwingUtilities.invokeLater(new MinesweeperWindow());
    }
}

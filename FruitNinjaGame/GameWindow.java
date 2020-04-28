import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Image;
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
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

/**
 * A java version/simulation of the app "Fruit Ninja." The objective of this game is to slice as many fruit as possible while 
 * dragging the mouse (the sword). The two things that will end the game are slicing a bomb or letting 3 fruit fall through 
 * without slicing them. In our version, we added 3 difficulty levels, 4 swords options, and 4 background options that the user is
 * able to select. 
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/26/2020
 */
public class GameWindow extends MouseAdapter implements Runnable, ActionListener
{
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 1050;
    private final int GAME_HEIGHT = 500;
    private final int MENU_HEIGHT = 100;

    private static JFrame gameFrame;
    private static int swordType;
    private static int diffLevel;
    private static int backType;
    private static FruitThrower newFT;
    private JPanel fruitPanel;
    private JPanel menuPanel; 
    private JButton startButton;
    private JButton resetButton;
    private JLabel scoreLabel;
    private JLabel gameOverLabel;
    private JLabel strikeLabel;
    private JLabel contentPane;
    private ArrayList<AnimatedLine> swordList;
    private Point lastMouse;
    private int lineNum;

    /**
     * The run method establishes the graphical user interface of the game itself.
     */
    @Override
    public void run()
    {
        //Creates and adds JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);

        gameFrame = new JFrame("Fuit Ninja");
        gameFrame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setLayout(new BorderLayout());

        //Creates and adds a menu Panel to the JFrame
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, MENU_HEIGHT));
        menuPanel.setBackground(Color.LIGHT_GRAY);
        gameFrame.add(menuPanel, BorderLayout.NORTH);

        //Creates a new font to use with different labels
        Font newFont = new Font("Georgia", Font.BOLD, 25);

        //Creates the start and reset game buttons
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        startButton.setFont(newFont);
        menuPanel.add(startButton);

        resetButton = new JButton("Reset Game");
        resetButton.addActionListener(this);
        resetButton.setVisible(false);
        resetButton.setFont(newFont);
        menuPanel.add(resetButton);

        //Creates the score label 
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(newFont);
        scoreLabel.setVisible(false);
        menuPanel.add(scoreLabel);

        // Creates and adds fruitPanel to Jframe
        fruitPanel = new JPanel(new BorderLayout()) {
            @Override
            /**
             *  This method will redraw the background depending on which one the user selects at
             *  the beginning of the game. It also displays the sword on the screen, along with
             *  showing the proper number of strikes on the screen as well. Once a bomb is hit,
             *  or 3 fruit fall through the "game over" label will appear on the screen.
             *  Repaints the scene according to the state of the game.
             *  
             *  @param g The Graphics component which will do the painting
             */
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(Color.BLACK);

                //This will chose the background based on what the user selected
                //Got some help writing this code from: https://www.youtube.com/watch?v=bv4PBdhoo4o
                //Images are from a public domain website: https://www.pexels.com/

                ImageIcon background;
                switch(backType)
                {
                    case 1: background = new ImageIcon("close-up-of-wooden-plank-326311.jpg");
                    g.drawImage(background.getImage(), 0, 0, WINDOW_WIDTH, GAME_HEIGHT, null);
                    break;
                    case 2: background = new ImageIcon("abstract-ancient-antique-art-235985.jpg");
                    g.drawImage(background.getImage(), 0, 0, WINDOW_WIDTH, GAME_HEIGHT, null);
                    break;
                    case 3: background = new ImageIcon("close-up-photo-of-blue-body-of-water-1435752.jpg");
                    g.drawImage(background.getImage(), 0, 0, WINDOW_WIDTH, GAME_HEIGHT, null);
                    break;
                    case 4: background = new ImageIcon("grayscale-photo-of-brickwall-1022692.jpg");
                    g.drawImage(background.getImage(), 0, 0, WINDOW_WIDTH, GAME_HEIGHT, null);
                    break;

                }

                //Paint and remove the sword on screen
                int i = 0;
                while(i < swordList.size())
                {
                    AnimatedLine line = swordList.get(i);
                    if(line.done())
                    {
                        swordList.remove(i);
                    }
                    else 
                    {
                        line.paint(g);
                        i++;
                    }
                }

                //Updates the score as the game continues
                scoreLabel.setText("Score: " + newFT.getScore());

                //This will display the number of strikes the user has during the course of the game
                //Image is from a public domain website: https://publicdomainvectors.org/
                ImageIcon strikes = new ImageIcon("X.png");
                switch (newFT.getStrikeCount())
                {
                    case 1: g.drawImage(strikes.getImage(), 850, 10, strikes.getIconWidth(), strikes.getIconHeight(), null);
                    break;
                    case 2: g.drawImage(strikes.getImage(), 850, 10, strikes.getIconWidth(), strikes.getIconHeight(), null);
                    g.drawImage(strikes.getImage(), 900, 10, strikes.getIconWidth(), strikes.getIconHeight(), null);
                    break;
                    case 3: g.drawImage(strikes.getImage(), 850, 10, strikes.getIconWidth(), strikes.getIconHeight(), null);
                    g.drawImage(strikes.getImage(), 900, 10, strikes.getIconWidth(), strikes.getIconHeight(), null);
                    g.drawImage(strikes.getImage(), 950, 10, strikes.getIconWidth(), strikes.getIconHeight(), null);
                    break;
                }

                //When the user has lost the game, this message will appear on the screen
                if(newFT.done())
                {

                    gameOverLabel.setVisible(true);

                }

                newFT.paint(g);
            }
        };

        //Created a fruit thrower object to user throughout the class
        newFT = new FruitThrower(fruitPanel, diffLevel);

        //Creating and adding the fruit panel to the game frame.
        fruitPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, GAME_HEIGHT));
        gameFrame.add(fruitPanel, BorderLayout.SOUTH);

        //Created a different font to use for labels.
        Font newFont2 = new Font("Georgia", Font.BOLD, 35);

        //Created and edit a game over label so it appears in the correct part of the panel
        gameOverLabel = new JLabel("GAME OVER!! PRESS 'RESET GAME' TO PLAY AGAIN!");
        gameOverLabel.setFont(newFont2);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVisible(false);
        fruitPanel.add(gameOverLabel);

        fruitPanel.addMouseListener(this);
        fruitPanel.addMouseMotionListener(this);

        new Thread(){
            @Override
            /** 
             * NEED TO EDIT THIS
             */
            public void run(){
                while (!newFT.done()){
                    try{
                        sleep(33);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.print(e);
                    }

                    fruitPanel.repaint();
                }

            }
        }.start();

        //creating a sword list
        swordList = new ArrayList<AnimatedLine>();

        //display the frame we made
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    /**
     * Action performed event handler, handles the start game and resest game buttons.
     * 
     * @param e The ActionEvent object which calls the method.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == startButton)
        {
            newFT.start();
            startButton.setVisible(false);
            resetButton.setVisible(true);
            scoreLabel.setVisible(true);
        }

        if (e.getSource() == resetButton)
        {
            newFT = new FruitThrower(fruitPanel, diffLevel);
            gameOverLabel.setVisible(false);
            fruitPanel.repaint();
            newFT.start();
        }
    }

    /**
     * Mouse pressed event handler, gets current location of the mouse
     * 
     * @param e The MouseEvent object which calls the method.
     */
    @Override
    public void mousePressed(MouseEvent e) 
    {
        lastMouse = e.getPoint();
    }

    /**
     * Mouse dragged event handler, keeps track of where the sword is being dragged. Which
     * sword is used is chosed by the user at the beginning of the game
     * 
     * @param e The MouseEvent object which calls the method.
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        AnimatedLine newLine;

        if(swordType == 1)
        {
            newLine = new VanishingLine(lastMouse, e.getPoint(), fruitPanel);
        }
        else if(swordType == 2)
        {
            newLine = new RainbowLine(lastMouse, e.getPoint(), fruitPanel);
        }
        else if(swordType == 3)
        {
            newLine = new RedLine(lastMouse, e.getPoint(), fruitPanel);
        }
        else
        {
            newLine = new BlueLine(lastMouse, e.getPoint(), fruitPanel);
        }

        lastMouse = e.getPoint();
        swordList.add(newLine);

        newLine.start();
        newFT.setMousePos(e.getPoint());
        fruitPanel.repaint();
    }

    /**
     * Mouse released event handler, if the mouse is releasd will set it to null.
     * 
     * @param e The MouseEvent object which calls the method.
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        newFT.setMousePos(null);
    }

    /**
     * Main method to run the program, allows user to select their level of difficulty, their sword
     * type, and their background type. Also, a pop up window with the directions will appear before
     * the start of the game.
     * 
     * @param args[] no command line input necessary.
     */
    public static void main(String args[])
    {
        // Load fruit pics
        Bomb.loadFruitPic();
        Orange.loadFruitPic();
        Apple.loadFruitPic();
        Watermelon.loadFruitPic();
        Banana.loadFruitPic();
        Strawberry.loadFruitPic();
        Peach.loadFruitPic();
        Avocado.loadFruitPic();

        //Easy medium and hard settings will go here. Background settings and sword settings will come. 
        //User will select Game Settings
        //User will select DifficultyDifficulty

        //User will select Difficulty

        String[] diffOpts = {"Easy", "Medium", "Hard"};

        Object selectedValue1 = JOptionPane.showInputDialog(null,
                "Choose a level of Difficulty: ", "Welcome to Fruit Ninja!",
                JOptionPane.PLAIN_MESSAGE, null,
                diffOpts, diffOpts[0]);

        String chosenDiff = (String) selectedValue1;

        switch(chosenDiff)
        {
            case "Easy": diffLevel = 1;
            break;
            case "Medium": diffLevel = 2;
            break;
            case "Hard": diffLevel = 3;
            break;
        }

        //User will select sword settings
        String[] swordOpts = {"Vanishing", "Rainbow", "Red", "Blue"};

        Object selectedValue2 = JOptionPane.showInputDialog(null,
                "Choose a Sword Type: ", "Sword Settings",
                JOptionPane.PLAIN_MESSAGE, null,
                swordOpts, swordOpts[0]);

        String chosenSword = (String) selectedValue2;

        switch(chosenSword)
        {
            case "Vanishing": swordType = 1;
            break;
            case "Rainbow": swordType = 2;
            break;
            case "Red": swordType = 3;
            break;
            case "Blue": swordType = 4;
            break;
        }

        //User will select a backdrop
        String[] backOpts = {"Wood", "Vintage", "Water", "Brick"};

        Object selectedValue3 = JOptionPane.showInputDialog(null,
                "Choose a Background: ", "Background Settings",
                JOptionPane.PLAIN_MESSAGE, null,
                backOpts, backOpts[0]);

        String chosenBG = (String) selectedValue3;

        switch(chosenBG)
        {
            case "Wood": backType = 1;
            break;
            case "Vintage": backType = 2;
            break;
            case "Water": backType = 3;
            break;
            case "Brick": backType = 4;
            break;
        }

        //Direction Window
        JOptionPane directions = new JOptionPane();
        directions.showMessageDialog(null, "Drag the mouse to slice as many fruit as possible!\nSlicing a bomb or letting 3 fruit pass will automatically end the game.\nGood luck!",
            "Directions", JOptionPane.INFORMATION_MESSAGE);

        javax.swing.SwingUtilities.invokeLater(new GameWindow());
    }
}

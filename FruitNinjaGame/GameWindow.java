//may have to edit later
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
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

//NEED TO EDIT LATER
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * NEED TO EDIT THIS!! Write a description of class GameWindow here.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/16/2020
 */
public class GameWindow extends MouseAdapter implements Runnable, ActionListener
{
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 1050;
    private final int GAME_HEIGHT = 500;
    private final int MENU_HEIGHT = 100;

    private final int LINE_POS = 20;

    private static JFrame gameFrame;
    private JPanel fruitPanel;
    private JPanel menuPanel; 
    private JButton startButton;
    private JButton resetButton;
    private JLabel scoreLabel;
    private FruitThrower newFT;
    private ArrayList<AnimatedLine> swordList;
    private Point lastMouse;
    private int lineNum;
    private JLabel gameOverLabel;
    private JLabel strikeLabel;
    private JLabel contentPane;
    private static int swordType;
    private static int diffLevel;
    private static int backType;

    /**
     * The run method which establishes the graphical interface.
     */
    @Override
    public void run()
    {
        //Creates and adds JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);

        gameFrame = new JFrame("Fuit Ninja");
        gameFrame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, MENU_HEIGHT));
        gameFrame.add(menuPanel, BorderLayout.NORTH);

        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        menuPanel.add(startButton);

        resetButton = new JButton("Reset Game");
        resetButton.addActionListener(this);
        resetButton.setVisible(false);
        menuPanel.add(resetButton);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setVisible(false);
        menuPanel.add(scoreLabel);

        strikeLabel = new JLabel();
        strikeLabel.setVisible(true);
        menuPanel.add(strikeLabel);

        // Creates and adds fruitPanel to Jframe
        fruitPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(Color.BLACK);

                //Divides up the JFrame
                g.drawLine(0, 0, WINDOW_WIDTH, 0);

                //will chose the background the user selected
                
                //Got some help writing this code from https://www.youtube.com/watch?v=bv4PBdhoo4o
                contentPane = new JLabel();
                ImageIcon background;
                switch(backType)
                {
                    case 1:
                    break;
                    case 2: background = new ImageIcon("abstract-ancient-antique-art-235985.jpg");
                    g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                    break;

                }
                
                
                
               

                int i = swordList.size() - 1;
                while(i >= 0 && !swordList.isEmpty())
                {
                    AnimatedLine line = swordList.get(i);
                    if(line.done())
                    {
                        swordList.remove(i);
                    }
                    else 
                    {
                        line.paint(g);
                        i--;
                    }
                }

                scoreLabel.setText("Score: " + newFT.getScore());

                switch (newFT.getStrikeCount())
                {
                    case 1: strikeLabel.setText("X");
                    break;
                    case 2: strikeLabel.setText("X X");
                    break;
                    case 3: strikeLabel.setText("X X X");
                    break;
                    default: strikeLabel.setText("");
                    break;
                }

                if(newFT.done())
                {
                    gameOverLabel.setVisible(true);
                }

                newFT.paint(g);
            }
        };
        
        newFT = new FruitThrower(fruitPanel, diffLevel);
        fruitPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, GAME_HEIGHT));
        gameFrame.add(fruitPanel, BorderLayout.SOUTH);

        fruitPanel.addMouseListener(this);
        fruitPanel.addMouseMotionListener(this);

        gameOverLabel = new JLabel("GAME OVER!! PRESS RESTART TO PLAY AGAIN!");
        gameOverLabel.setVisible(false);
        menuPanel.add(gameOverLabel);

        new Thread(){
            @Override
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

        swordList = new ArrayList<AnimatedLine>();

        //display the frame we made
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    /**
     * Action performed event handler, handles the game start and game reset buttons.
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
            strikeLabel.setText("");
            newFT.start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        Random rand = new Random();
        lastMouse = e.getPoint();
        //lineNum = rand.nextInt(6);
    }

    /**
     * Mouse dragged event handler, tracks when user is dragging the ball before firing
     * 
     * @param e The MouseEvent object which calls the method.
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(swordType == 1)
        {
            AnimatedLine newLine = new VanishingLine(lastMouse, e.getPoint(), fruitPanel);
            lastMouse = e.getPoint();
            swordList.add(newLine);

            newLine.start();
            newFT.setMousePos(e.getPoint());
            fruitPanel.repaint();
        }

        //Other SwordTypes will go here once they are in.

        // lastMouse = e.getPoint();
        // swordList.add(newLine);

        // newLine.start();
        // newFT.setMousePos(e.getPoint());
        //fruitPanel.repaint();
    }

    /**
     * Mouse released event handler, fires ball when user releases mouse.
     * 
     * @param e The MouseEvent object which calls the method.
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        newFT.setMousePos(null);
    }

    /**
     * Main method to run the program, allows user to select the color of their ball.
     * 
     * @param args[] no command line input necessary.
     */
    public static void main(String args[])
    {
        //Easy medium and hard settings will go here. Background settings and sword settings will come. 

        //User will select Game Settings

        //User will select DifficultyDifficulty
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

        javax.swing.SwingUtilities.invokeLater(new GameWindow());

    }

}

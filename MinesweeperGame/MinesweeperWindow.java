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

/**
 * Write a description of class GameWindow here.
 *
 * @author Kate Frisch, Van Griffith & Abby Hall
 * @version Spring 2020
 */
public class MinesweeperWindow implements Runnable, ActionListener
{
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 1050;
    private final int GAME_HEIGHT = 500;
    private final int MENU_HEIGHT = 100;

    private final int LINE_POS = 20;
    
    private int arrayWidth;
    private int arrayHeight;
    private int bombCount;
    private JButton[][] bombArray;
    
    private JFrame gameFrame;
    private JPanel menuPanel;
    private JPanel mineField;
    private JButton faceButton;
    private JLabel timer;
    private JLabel bombLabel;
    
    public void run()
    {
        //Creates and adds JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        gameFrame = new JFrame("Minesweeper");
        gameFrame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
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
        
        mineField = new JPanel() {
         @Override
         public void paintComponent(Graphics g)
         {
             super.paintComponent(g);
             g.setColor(Color.BLACK);
             
             //Line to divide JFrame
             g.drawLine(0, 0, WINDOW_WIDTH, 0);
            }
        };
        gameFrame.add(mineField); 
        
        bombArray = new JButton[arrayWidth][arrayHeight];
        
        
        
    }
    
    /**
     * Action performed event handler, handles the game start and game reset buttons.
     * 
     * @param e The ActionEvent object which calls the method.
     */
    public void actionPerformed(ActionEvent e)
    {
        
    }
}

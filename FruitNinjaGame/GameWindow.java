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

    private JFrame gameFrame;
    private JPanel fruitPanel;
    private JPanel menuPanel; 
    private JButton startButton;
    private JButton resetButton;
    private FruitThrower newFT;

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

        // Creates and adds fruitPanel to Jframe
        fruitPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                //Divides up the JFrame
                g.drawLine(0, 0, WINDOW_WIDTH, 0);

                newFT.paint(g);

            }
        };

        newFT = new FruitThrower(fruitPanel);

        fruitPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, GAME_HEIGHT));
        gameFrame.add(fruitPanel, BorderLayout.SOUTH);

        fruitPanel.addMouseListener(this);
        fruitPanel.addMouseMotionListener(this);

        new Thread(){
            @Override
            public void run(){
                while (true){
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
        }
        
        if (e.getSource() == resetButton)
        {
            newFT = new FruitThrower(fruitPanel);
            newFT.start();
        }
    }

    /**
     * Mouse dragged event handler, tracks when user is dragging the ball before firing
     * 
     * @param e The MouseEvent object which calls the method.
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        newFT.setMousePos(e.getPoint());
    }

    /**
     * Mouse released event handler, fires ball when user releases mouse.
     * 
     * @param e The MouseEvent object which calls the method.
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    /**
     * Main method to run the program, allows user to select the color of their ball.
     * 
     * @param args[] no command line input necessary.
     */
    public static void main(String args[])
    {
        //Easy medium and hard settings will go here. Background settings and sword settings will come. 

        javax.swing.SwingUtilities.invokeLater(new GameWindow());
    }

}

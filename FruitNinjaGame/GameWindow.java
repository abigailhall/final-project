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
 * Write a description of class GameWindow here.
 *
 * @author Kate Frisch, Van Griffith, & Abby Hall
 * @version 4/16/2020
 */
public class GameWindow extends MouseAdapter implements Runnable, ActionListener
{
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 1050;
    private final int LINE_POS = 100;

    private JFrame gameFrame;
    private JPanel fruitPanel;
    private JPanel menuPanel; 

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

        // Creates and adds fruitPanel to Jframe
        fruitPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g)
            {

                super.paintComponent(g);

                //Divides up the JFrame
                g.drawLine(0, LINE_POS, WINDOW_WIDTH, LINE_POS);
            }
        };

        fruitPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameFrame.add(fruitPanel,BorderLayout.WEST);

        fruitPanel.addMouseListener(this);
        fruitPanel.addMouseMotionListener(this);
        

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

    }
    
    /**
     * Mouse dragged event handler, tracks when user is dragging the ball before firing
     * 
     * @param e The MouseEvent object which calls the method.
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {

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

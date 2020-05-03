import javax.swing.JComponent;
/**
 * This class is a simulation of a timer by extending Thread.
 *
 * @author Kate Frisch, Van Griffith, Abby Hall
 * @version 5/2/2020
 */
public class TimerClass extends Thread
{
    private int secondsPassed = 0;
    private JComponent panel;
    private boolean ticking = true;

    /**
     *  Constructors a new TimerClass object
     *  
     *  @param panel the Component in which this TimerClass will live
     */
    public TimerClass(JComponent panel)
    {
        this.panel = panel;
    }

    /**
     * Run method to defines the life of the TimerClass. In essence, it keeps track of the
     * time in seconds. Will paint it to the menuPanel so the player can see how long
     * it takes for them to play the game.
     */
    public void run()
    {
        while(ticking)
        {
            try {
                sleep(1000);

            }
            catch (InterruptedException e) {
            }

            secondsPassed++;
            panel.repaint();
        }
    }

    /**
     * This method will stop the timer. 
     */
    public void stopTimer()
    {
        ticking = false;
    }

    /**
     * Accessor method to know how long the timer has been running. 
     */
    public int getSeconds()
    {
        return secondsPassed;
    }
}

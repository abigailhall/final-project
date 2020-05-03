import javax.swing.JComponent;
/**
 * Write a description of class TimerClass here.
 *
 * @author Kate Frisch, Van Griffith, Abby Hall
 * @version 5/2/2020
 */
public class TimerClass extends Thread
{
    private int secondsPassed = 0;
    private JComponent panel;
    private boolean ticking = true;

    public TimerClass(JComponent panel)
    {
        this.panel = panel;
    }

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

    public void stopTimer()
    {
        ticking = false;
    }

    public int getSeconds()
    {
        return secondsPassed;
    }
}

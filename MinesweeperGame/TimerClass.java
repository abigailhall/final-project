import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComponent;
/**
 * Write a description of class TimerClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
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

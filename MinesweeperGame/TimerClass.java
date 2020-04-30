import java.util.Timer;
import java.util.TimerTask;
/**
 * Write a description of class TimerClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TimerClass
{
    int secondsPassed = 0;
    
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        public void run()
        {
            secondsPassed++;
            System.out.println(secondsPassed);
        }
    };
    
    public void start()
    {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}

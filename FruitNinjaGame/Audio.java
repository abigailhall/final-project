import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * This class contains a method that allows you to play a sound. 
 *
 * @author Van Griffith
 * @version Spring 2020
 */
public class Audio
{
    /**
     * 
     */
    public
    
    /**
     * Plays a sound from a given file. The file must be in the same folder as this class
     * 
     * @param fileName the name of the file
     */
    public void playSound (String fileName)
    {
        File file = new File(fileName);
        
        try 
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        }
        catch (Exception e)
        {
            
        }
    }
}

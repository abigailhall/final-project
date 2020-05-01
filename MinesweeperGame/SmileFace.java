import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;

public class SmileFace extends Thread
{
    private static final int FACE_SIZE = 20;

    private JComponent panel;
    private boolean shockFace;
    private boolean smileFace;

    public SmileFace(JComponent panel)
    {
        this.panel = panel;
        shockFace = false;
        smileFace = true;
    }
   
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, FACE_SIZE, FACE_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, FACE_SIZE, FACE_SIZE);

        if(shockFace)
        {
            showShock(g);
        }
        else
        {
            showSmile(g);;
        }
    }

    @Override
    public void run()
    {
        panel.repaint();

    }

    public void setShock(boolean shock)
    {
        shockFace = shock;
    }
    public void setSmile(boolean smile)
    {
        smileFace = smile;
    }

    public void showSmile(Graphics g)
    {
        // g.setColor(Color.BLACK);
        // g.fillOval(0, 0, FACE_SIZE, FACE_SIZE);
        System.out.println("Show smile face");
    }
    public void showShock(Graphics g)
    {
        // g.setColor(Color.BLACK);
        // g.fillOval(0, 0, FACE_SIZE, FACE_SIZE);
        System.out.println("Show shock face");
    }
    

}
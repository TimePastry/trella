package net.tjacobhi.samebird;

import sun.java2d.loops.DrawLine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by tjacobhi on 28-Dec-16.
 * Please reference the following site to learn 2d graphics
 * https://docs.oracle.com/javase/tutorial/2d/index.html
 *
 * This will allow us to create a window with 2d graphics
 */
public class TestWindow extends JFrame {

    public TestWindow()
    {
        super();
    }

    private  void createAndShowGUI() {
        //Create and set up the window.
        //Frame frame = new LineJFrame(); //notice this line was changed to my own created inner class
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("");

        //here I was experimenting trying to force window size, doesn't quite work yet, but is getting there
        emptyLabel.setPreferredSize(new Dimension(800, 600));
        emptyLabel.setMinimumSize(new Dimension(800, 600));
        emptyLabel.setMaximumSize(new Dimension(800, 600));
        this.setMaximumSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(800, 600));

        this.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        //Display the window.
        this.pack();
        this.setVisible(true);
    }

    public void createWindow() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g); // I added this line to handle repaint issues
        g.setColor(new Color(0x4488ee)); // This allows us to create a color using hex value
        g.fillRect(60, 60, 80, 120); // Draws a filled in rectangle
        g.setColor(Color.red); // This allows us to use color name (very limited)
        g.drawLine(50, 200, 400, 300); // Draws a line
        g.setColor(new Color(0x21e249));
        g.drawString("Hello, World!", 380, 290); // Draws text
    }

}

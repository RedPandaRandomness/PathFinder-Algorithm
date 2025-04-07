import javax.swing.JFrame;

/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Driver
     */
    
    public Driver()
    {
        // Setup the environment for drawing
        JFrame frame = new JFrame();
        frame.setTitle("PatherFinder Algorithm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args)
    {
        Driver game = new Driver();
    }
}


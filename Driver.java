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
    public Board maze;
    public Ranger him;

    /**
     * Constructor for objects of class Driver
     */
    
    public Driver()
    {
        // Setup the environment for drawing
        JFrame frame = new JFrame();
        frame.setTitle("PatherFinder Algorithm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Makes new game board
        maze = new Board();
        him = new Ranger(maze);
        
        frame.getContentPane().add(maze);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args)
    {
        Driver game = new Driver();
    }
}

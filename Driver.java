import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver
{
    // instance variables 
    public Board maze;
    public Ranger him;

    /**
     * Constructor for objects of class Driver
     */
    
    public Driver()
    {
        // Make the main frame for the board
        JFrame board = new JFrame();
        board.setTitle("PatherFinder Algorithm");
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Make panel for buttons, then add them to it
        JPanel buttonPanel = new JPanel();
        JButton speed = new JButton("Caffeine mode");
        JButton editor = new JButton("Edit maze");
        JButton start = new JButton("Send ranger");
        
        buttonPanel.add(speed);
        buttonPanel.add(editor);
        buttonPanel.add(start);
        
        
        //Make objects for game
        maze = new Board();
        him = new Ranger(maze);
        maze.sendRanger(him);
        
        speed.addActionListener(maze);
        editor.addActionListener(maze);
        start.addActionListener(maze);
        
        board.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        board.getContentPane().add(maze);
        board.pack();
        board.setVisible(true);

    }

    public static void main(String[] args)
    {
        Driver game = new Driver();
    }
}

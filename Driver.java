//SMT funny

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

/**
 * PathFinderAlgorithm 
 * - Load a default maze
 * - Can edit it
 * - Has a Ranger that can search for end of maze (Cabin)
 * - Can reset to search new maze 
 *
 * @Authors: Lora Frederico, Benjamin Ford
 * @Development date: April 7th - May 1st 2025
 * @Version: 1.0.0
 * - Base functionality (main goals of project)
 * - Cafiene mode to speed up search
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
        JButton reset = new JButton("Reset");
        
        buttonPanel.add(speed);
        buttonPanel.add(editor);
        buttonPanel.add(start);
        buttonPanel.add(reset); 
        
        //Make objects for game
        maze = new Board();
        him = new Ranger(maze);
        maze.sendRanger(him);
        
        //Listen to buttons
        speed.addActionListener(maze);
        editor.addActionListener(maze);
        start.addActionListener(maze);
        reset.addActionListener(maze);
        
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

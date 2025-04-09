import static java.lang.System.out; //shortcut for writing output
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import javax.swing.ImageIcon;


/**
 * Write a description of class Board here.
 *
 * @author (Lora & Benj)
 * @version (a version number or a date)
 */
public class Board extends JPanel
{
    // instance variables
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    Tile[][] grid = new Tile[12][12];
    ImageIcon house = new ImageIcon("Sprites/drhouse.jpg");

    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        // initialise instance variables
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(new Color(211,211,211));
        
        main();
    }
    
    public void main()
    {   
        //make tiles
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                grid[i][j] = new Tile(i*50,j*50);
            }
        }
        grid[11][11].setState(5);
        
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D outline = (Graphics2D) g;
        outline.setColor(Color.black);
        outline.setStroke(new BasicStroke(3));
        
    
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                //Draw fill
                g.setColor(grid[i][j].colour);
                g.fillRect(grid[i][j].x,grid[i][j].y,
                    grid[i][j].width,grid[i][j].height);
                
                //Draw outline
                outline.setColor(Color.gray);
                outline.setStroke(new BasicStroke(3));
                outline.drawRect(grid[i][j].x,grid[i][j].y,
                    grid[i][j].width,grid[i][j].height);
                    
                //print house
                if(grid[i][j].getState() == 5){
                    ImageIcon brush = house;
        
                    brush.paintIcon(this,g,
                    grid[i][j].x,grid[i][j].y);
                }
               
            }
        }
    }
}

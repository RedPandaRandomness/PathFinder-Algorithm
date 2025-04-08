import static java.lang.System.out; //shortcut for writing output
import java.awt.Rectangle;
import java.awt.Color;

/**
 * Write a description of class Tile here.
 *
 * @author (Lora, Benji)
 * @version (0.0.2)
 */
public class Tile extends Rectangle
{
    // instance variables
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public Color colour;
    public int state = 0;
    /* 0 = unvisited, unmarked
     * 1 = unvisited, marked
     * 2 = visited, unmarked
     * 3 = visited, marked <- impossible?
     * 4 = tree (ignore)
     */
    
    /**
     * Constructor for objects of class Tile
     */
    public Tile(int x, int y)
    {
        super(x,y,WIDTH,HEIGHT);
        setState(0);
    }

    public static void main(String[] args)
    {
        
    }
    
    public int getState()
    {
        return 0;
    }
    
    public void setState(int state)
    {
        if(state == 0) // unvisited, unmarked
        {
            colour = new Color(255,255,255); // white
            this.state = state;
        }
        else if(state == 1) // unvisited, marked
        {
            colour = new Color(255,0,0); // red
            this.state = state;
        }
        else if(state == 2) // visited, unmarked
        {
            colour = new Color(0,255,0);
            this.state = state;
        }
        else if(state == 3) //# visited, marked
        {
            colour = new Color(200,0,255);
            this.state = state;
        }
        else if(state == 4) // tree
        {
            colour = new Color(0,0,0); // black
            this.state = state;
        }
    }
}

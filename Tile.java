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
    private int state = 0;
    /* 0 = Blank (unvisited)
     * 1 = Visited
     * 2 = Crossed off
     * 3 = tree (ignore)
     * 4 = house
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
        return state;
    }
    
    public void setState(int state)
    {
        if(state == 0) // Blank, not visited
        {
            colour = new Color(135,217,98); // grassy 
            this.state = state;
        }
        else if(state == 1) // Visited
        {
            colour = new Color(84,62,38); // path
            this.state = state;
        }
        else if(state == 2) // Crossed off
        {
            colour = new Color(179,242,150); // lighter grassy
            this.state = state;
        }
        else if(state == 3) // house?
        {
            colour = new Color(135,217,98); // grassy
            this.state = state;
        }
        else if(state == 4) // tree?
        {
            colour = new Color(85,177,58); // darker grassy
            this.state = state;
        }
    }
}

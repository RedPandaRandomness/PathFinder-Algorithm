import static java.lang.System.out; //shortcut for writing output
import java.awt.Rectangle;

/**
 * Write a description of class Tile here.
 *
 * @author (Lora, Benji)
 * @version (0.0.1)
 */
public class Tile extends Rectangle
{
    // instance variables
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public int state = 0;
    

    /**
     * Constructor for objects of class Tile
     */
    public Tile(int x, int y)
    {
        super(x,y,WIDTH,HEIGHT);
        
    }

    public static void main(String[] args)
    {
        
    }
}

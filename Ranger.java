import java.awt.Point;

/**
 * Write a description of class Ranger here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ranger
{
    // instance variables - replace the example below with your own
    private Point location;
    Board maze;

    /**
     * Constructor for objects of class Ranger
     */
    public Ranger(Board board)
    {
        location = new Point(0,0);
        maze = board;
    }
    
    public Point getLocation()
    {
        return location;
    }
    
    public void setLocation(int x, int y)
    {
        location.move(x,y);
    }
    
    public void look()
    {
        if(maze.grid[location.x][location.y-1].getState() == 0){
            
        }
    }
}

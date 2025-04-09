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
    ToCheckNode top = new ToCheckNode();
    

    /**
     * Constructor for objects of class Ranger
     */
    public Ranger(Board board)
    {
        location = new Point(0,0);
        maze = board;
        
        look();
    }
    
    public Point getLocation()
    {
        return location;
    }
    
    public int getX()
    {
        return location.x;
    }
    public int getY()
    {
        return location.y;
    }
    
    public void setLocation(int x, int y)
    {
        location.move(x,y);
    }
    
    public void setLocation(Point location)
    {
        this.location = location;
    }
    
    public void Cpush(Point coor) { //check push
      ToCheckNode newTop = new ToCheckNode(coor);  // Store N in the new Node.
      newTop.setNext(top);   // The new Node points to the old top.
      top = newTop;        // The new item is now on top.
    }
    
    public Point cPop(){
        
        return null;
    }
   
    
    
    public void look()
    {
        Point moveTo = new Point(0,0);
        
        //Check north
        System.out.println("Check North");
        if(location.y-1 >= 0 && maze.grid[location.x][location.y-1].getState() == 0){
              if(moveTo.equals(new Point (0,0))){
                System.out.println("North");
                moveTo = new Point(0,-1);
            }
            else{
                Cpush(new Point(location.x,location.y-1));
            }
        }
        //Check East
        System.out.println("Check East");
        if(location.x+1 <= 11 && maze.grid[location.x+1][location.y].getState() == 0){
              if(moveTo.equals(new Point (0,0))){
                System.out.println("E");
                moveTo = new Point(1,0);
            }
            else{
                Cpush(new Point(location.x+1,location.y));
            }
        }
        //Check South
        System.out.println("Check South");
        if(location.y+1 <= 11 && maze.grid[location.x][location.y+1].getState() == 0){
              if(moveTo.equals(new Point (0,0))){
                System.out.println("S");
                moveTo = new Point(0,1);
            }
            else{
                Cpush(new Point(location.x,location.y+1));
            }
        }
        //Check West
        System.out.println("Check West");
        if(location.x-1 >= 0 &&maze.grid[location.x-1][location.y].getState() == 0){
              if(moveTo.equals(new Point (0,0))){
                System.out.println("W");
                moveTo = new Point(-1,0);
            }
            else{
                Cpush(new Point(location.x-1,location.y));
            }
        }
        
        System.out.println(moveTo);
        setLocation(moveTo);
    }
}

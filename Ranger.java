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
    public Point location;
    Board maze;
    ToCheckNode topC = new ToCheckNode();
    PathNode topP = new PathNode();
    
    

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
    
    public int getX()
    {
        return location.x;
    }
    public int getY()
    {
        return location.y;
    }
    
    public void setLocation(Point location)
    {
        this.location = location;
    }
    
    public void move(Point location)
    {
        this.location.translate(location.x, location.y);
    }
    
    public void Cpush(Point coor) { //Pushes point to check later
      ToCheckNode newTop = new ToCheckNode(coor);  // Store N in the new Node.
      newTop.setNext(topC);   // The new Node points to the old top.
      topC = newTop;        // The new item is now on top.
    }
    
    public Point cPop(){
        if (topC == null ){
            throw new IllegalStateException(
                "Can't pop from an empty stack.");
            }
        Point coor = topC.getCoor();
        topC = topC.getNext();
        return coor;
    }
    
    public void pPush(Point coor) { //Pushes point to check later
      PathNode newTop = new PathNode(coor);  // Store N in the new Node.
      newTop.setNext(topP);   // The new Node points to the old top.
      topP = newTop;        // The new item is now on top.
      System.out.println(topP.getCoor());
    }
    
    public Point pPop(){
        if (topP == null ){
            throw new IllegalStateException(
                "Can't pop from an empty stack.");
            }
        Point coor = topP.getCoor();
        topP = topP.getNext();
        return coor;
    }
    
    public void look()
    {
        Point moveTo = new Point(0,0);
        
        //Check north
        if(tileValid(location.x, location.y-1)){
              if(moveTo.equals(new Point (0,0))){
                moveTo = new Point(0,-1);
            }
            else{
                Cpush(new Point(location.x,location.y));
            }
        }
        //Check East
        if(location.x+1 <= 11 && maze.grid[location.x+1][location.y].getState() == 0){
              if(moveTo.equals(new Point (0,0))){
                moveTo = new Point(1,0);
            }
            else{
                Cpush(new Point(location.x,location.y));
            }
        }
        //Check South
        if(location.y+1 <= 11 && maze.grid[location.x][location.y+1].getState() == 0){
              if(moveTo.equals(new Point (0,0))){
                moveTo = new Point(0,1);
            }
            else{
                Cpush(new Point(location.x,location.y));
            }
        }
        //Check West
        if(location.x-1 >= 0 &&maze.grid[location.x-1][location.y].getState() == 0){
              if(moveTo.equals(new Point (0,0))){
                moveTo = new Point(-1,0);
            }
            else{
                Cpush(new Point(location.x,location.y));
            }
        }
        maze.grid[location.x][location.y].setState(1);
        move(moveTo);
        
        if(moveTo.equals(new Point(0,0))){
            setLocation(cPop());
            //backtrack();
        }
        
        
    }
    
    private boolean tileValid(int x, int y){
        return x>=0 && y>=0 && x<=11 && y<=11 && maze.grid[x][y].getState() != 4 
                && maze.grid[x][y].getState() != 1;
    }
    
    private void backtrack()
    {
        if(!topP.getCoor().equals(topC.getCoor())){
             setLocation(pPop());
        }
    }
}

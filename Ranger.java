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
    private boolean backtrack = false;
    int face = 2;

    /**
     * Constructor for objects of class Ranger
     */
    public Ranger(Board board)
    {
        location = new Point(0,0);
        maze = board;
        
    }
    
    public int getFacing()
    {
        return face;
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
    
    public void move(Point move)
    {
        location = new Point(location.x+move.x, location.y+move.y);
    }
    
    public void cPush(Point coor) { //Pushes point to check later
      ToCheckNode newTop = new ToCheckNode(coor);
      newTop.setNext(topC);   // The new Node points to the old top.
      topC = newTop;        // The new item is now on top.
    }
    
    public void pPush(Point coor){
        PathNode newTop = new PathNode(coor);
        newTop.setNext(topP);   // The new Node points to the old top.
        topP = newTop;        // The new item is now on top.
    }
    
    public Point cPop(){
        if (topC == null ){
            throw new IllegalStateException(
                "Can't pop from an empty stack.");
        }
        Point coor = topC.getCoor();//Get coordinate of current top
        topC = topC.getNext();
        return coor;
    }
    
    public Point pPop(){
        if (topP == null ){
            throw new IllegalStateException(
                "Can't pop from an empty stack.");
        }
        Point coor = topP.getCoor(); //Gets current top
        topP = topP.getNext(); //New top is old tops pointer
        return coor;
    }
    
    public void look()
    {   
        Point moveTo = new Point(0,0);
        
        if(!backtrack){
    
            //Check north
            if(tileValid(location.x, location.y-1)){
                  if(moveTo.equals(new Point (0,0))){
                    moveTo = new Point(0,-1);
                    face = 0;
                }
                else{
                    cPush(new Point(location.x,location.y));
                }
            }
            //Check East
            if(tileValid(location.x+1, location.y)){
                  if(moveTo.equals(new Point (0,0))){
                    moveTo = new Point(1,0);
                    face = 1;
                }
                else{
                    cPush(new Point(location.x,location.y));
                }
            }
            //Check South
            if(tileValid(location.x, location.y+1)){
                  if(moveTo.equals(new Point (0,0))){
                    moveTo = new Point(0,1);
                    face = 2;
                }
                else{
                    cPush(new Point(location.x,location.y));
                }
            }
            //Check West
            if(tileValid(location.x-1, location.y)){
                  if(moveTo.equals(new Point (0,0))){
                    moveTo = new Point(-1,0);
                    face = 3;
                }
                else{
                    cPush(new Point(location.x,location.y));
                }
            }
            maze.grid[location.x][location.y].setState(1);
            if(!moveTo.equals(new Point(0,0))){pPush(location);}//Add location to path stack
            move(moveTo);
        }
        
        
        //dead end
        if(moveTo.equals(new Point(0,0))){
            backtrack = true;
            maze.grid[location.x][location.y].setState(2);
            System.out.println("Backtrack now");
            backtrack();
            
            
        }
        maze.repaint();
        }
    
        private boolean tileValid(int x, int y){
            if(x>=0 && x<=11 && y>=0 && y<=11 &&(
                maze.grid[x][y].getState() == 0 || 
                maze.grid[x][y].getState() == 3)){
                return true;
            }
            else{
                return false;
            }
    }

    private void backtrack()
    {
        if(location.equals(topC.getCoor())){ //If at crossroad
            //Stop backtracking
            cPop();
            backtrack = false;
            maze.grid[location.x][location.y].setState(1);
        }
        else{
            //Go back to the next tile
            setLocation(pPop());          
        }
        
    }
}

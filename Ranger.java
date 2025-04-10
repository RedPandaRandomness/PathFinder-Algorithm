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
    
    public void move(Point move)
    {
        location = new Point(location.x+move.x, location.y+move.y);
    }
    
    public void cPush(Point coor) { //Pushes point to check later
      ToCheckNode newTop = new ToCheckNode(coor);
      newTop.setNext(topC);   // The new Node points to the old top.
      topC = newTop;        // The new item is now on top.
      System.out.println(topC.getCoor());
    }
    
    public void pPush(Point coor){
        PathNode newTop = new PathNode(coor);
        newTop.setNext(topP);   // The new Node points to the old top.
        topP = newTop;        // The new item is now on top.
        System.out.println("pPush: "+topP.getCoor());
    }
    
    public Point cPop(){
        if (topC == null ){
            throw new IllegalStateException(
                "Can't pop from an empty stack.");
        }
        Point coor = topC.getCoor();//Get coordinate of current top
        topC = topC.getNext();
        System.out.println("CPop: "+topC.getCoor());
        return coor;
    }
    
    public Point pPop(){
        if (topP == null ){
            throw new IllegalStateException(
                "Can't pop from an empty stack.");
        }
        Point coor = topP.getCoor(); //Gets current top
        topP = topP.getNext(); //New top is old tops pointer
        System.out.println("pPop: "+topP.getCoor());
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
                }
                else{
                    cPush(new Point(location.x,location.y));
                }
            }
            //Check East
            if(location.x+1 <= 11 && maze.grid[location.x+1][location.y].getState() == 0){
                  if(moveTo.equals(new Point (0,0))){
                    moveTo = new Point(1,0);
                }
                else{
                    cPush(new Point(location.x,location.y));
                }
            }
            //Check South
            if(location.y+1 <= 11 && maze.grid[location.x][location.y+1].getState() == 0){
                  if(moveTo.equals(new Point (0,0))){
                    moveTo = new Point(0,1);
                }
                else{
                    cPush(new Point(location.x,location.y));
                }
            }
            //Check West
            if(location.x-1 >= 0 &&maze.grid[location.x-1][location.y].getState() == 0){
                  if(moveTo.equals(new Point (0,0))){
                    moveTo = new Point(-1,0);
                }
                else{
                    cPush(new Point(location.x,location.y));
                }
            }
            maze.grid[location.x][location.y].setState(1);
            if(!moveTo.equals(new Point(0,0))){pPush(location);}//Add location to path stack
            move(moveTo);
            
        }
        //System.out.println("Current Path top: "+topP.getCoor()); 
        
        
        //dead end
        if(moveTo.equals(new Point(0,0))){
            backtrack = true;
            System.out.println("Backtrack now");
            backtrack();
            
        }
        maze.repaint();
        int nope =0;
        }
    
        private boolean tileValid(int x, int y){
        return x>=0 && y>=0 && x<=11 && y<=11 && maze.grid[x][y].getState() != 4 
                && maze.grid[x][y].getState() != 1 && maze.grid[x][y].getState() != 5;
    }
    
    private void backtrack()
    {
        if(location.equals(topC.getCoor())){
            cPop();
            backtrack = false;
        }
        else{
            setLocation(pPop());
        }
        
    }
}

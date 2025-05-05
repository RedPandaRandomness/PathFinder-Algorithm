//SMT funny

import java.awt.Point;

/**
 * Ranger:
 * - Looks for surrounding open path
 * - Stores path it's taken
 * - Stores crossroads/paths it hasn't taken to look at later
 * - Backtracks if hits a deadend
 *
 * @author Lora Frederico
 * @version 1.0.0
 * - All base funcitonality 
 */
public class Ranger
{
    // instance variables
    public Point location;
    Board maze;
    ToCheckNode topC = new ToCheckNode();
    PathNode topP = new PathNode();
    private boolean backtrack = false;
    private int face = 2; //Direction facing

    //Makes new Ranger, telling it to look at the board/maze   
    public Ranger(Board board)
    {
        location = new Point(0,0);
        maze = board; 
    }

    public int getFacing()
    {
        return face;
    }

    //Sets a valid facing direction
    public void setFace(int a)
    {
        if(a>=0 && a<=3){
            face = a;
        }
    }

    public Point getLocation()
    {
        return location;
    }

    public void setLocation(Point location)
    {
        this.location = location;
    }

    public void setLocation(int x, int y)
    {
        location = new Point(x,y);
    }

    //Changes location by adding coordinates of given point to location
    //Note: .translate() method also moved the node which broke the stack
    public void move(Point move)
    {
        location = new Point(location.x+move.x, location.y+move.y);
    }

    /* Pushes Coordinate to top of Crossroad stack
     * Keeps track of points Ranger could've taken but didn't
     */
    public void cPush(Point coor) {
        ToCheckNode newTop = new ToCheckNode(coor);
        newTop.setNext(topC);   
        topC = newTop;      
    }

    /* Pushes Coordinate to top of Path stack
     * Keeps track of the whole path the Ranger has taken
     * to backtrack later if hit's a dead end
     */
    public void pPush(Point coor){
        PathNode newTop = new PathNode(coor);
        newTop.setNext(topP);   
        topP = newTop;      
    }

    //Pops top of Crossroad stack
    public Point cPop(){
        if (topC == null ){
            throw new IllegalStateException(
                "Can't pop from an empty stack.");
        }
        Point coor = topC.getCoor();
        topC = topC.getNext();
        return coor;
    }

    //If the top of the Crossroad stack is null, then the stack is empty
    private boolean cIsEmpty(){
        return (topC == null);
    }

    //Pops top of path stack 
    public Point pPop(){
        if (topP == null ){
            throw new IllegalStateException(
                "Can't pop from an empty stack.");
        }
        Point coor = topP.getCoor(); 
        topP = topP.getNext();
        return coor;
    }

    //Returns distance from given point and coordinate
    public Point getDist(Point a){
        return new Point (a.x - location.x, a.y - location.y);
    }

    /*Depth first search
     * Checks if tile in direction is valid, if spot is open, 
     * store how much to move by and check other directions.
     * If a direction has already been picked to go, push the 
     * coordinate the ranger is on in the crossroad stack to check later.
     * Stores the coor the ranger is on so when it goes back it goes back
     * to the crossroad and checks for next valid path. 
     * If no direction to go is picked (there's nowhere to go), start
     * backtracking
     */
    
    public void look()
    {   
        //Keeps track of how far Ranger has to move
        Point moveTo = new Point(0,0); 
        
        if(!backtrack){ 
            
            //Check north
            if(tileValid(location.x, location.y-1)){
                //If haven't found open path yet
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
            if(!moveTo.equals(new Point(0,0))){pPush(location);}
            move(moveTo);
        }

        if(moveTo.equals(new Point(0,0))){
            backtrack = true;
            maze.grid[location.x][location.y].setState(2);
            backtrack();
        }

        maze.repaint();
    }

    //Checks if tile is either unexplored, or cabin and in bounds
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
    
    
    /*
     * If location is the same as the top of the crossroad stack,
     * stop backtracking. If its not, ie: you are backtracking, face 
     * the direction the tile then pop the path stack.
     * If the path stack is empty, then theres no solution to the maze
     * and we trigger our "homelessness procedure"
     */
    private void backtrack()
    {
        if(location.equals(topC.getCoor()) && !cIsEmpty()){
            cPop();
            backtrack = false; 
            maze.grid[location.x][location.y].setState(1);
        }
        else{
            Point direction = getDist(topP.getCoor());
            if(direction.y == -1){face = 0;}
            else if(direction.x == 1){face = 1;}
            else if(direction.y == 1){face = 2;}
            else if(direction.x == -1){face = 3;}
            
            if(topP.getNext() != null){
                setLocation(pPop());
            }
            else{
                maze.setBoard(42); 
            }
        }
    }
}

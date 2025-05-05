//SMT funny

import java.awt.Point;

/**
 * Path nodes:
 *  - Used to store the path the Ranger has taken 
 *  - Used later for backtracking
 */
public class PathNode
{
    private Point coor;
    private PathNode next;
    
    public PathNode() //default constructor
    {
        coor = new Point(0,0);
        next = null;
    }
    
    public PathNode(Point coor) //Gives a coordinate to make node with
    {
        this.coor = coor;
        next = null;
    }
    
    public Point getCoor()
    {
        return coor;
    }
    
    public void setCoor(int x, int y)
    {
        coor.move(x,y);
    }
    public void setCoor(Point coor)
    {
        this.coor = coor;
    }
    
    public void setNext(PathNode next){
        this.next = next;
    }
    
    public PathNode getNext(){
        return next;
    }
    
    public String toText(){
        return "Coor: ("+coor.x+", "+coor.y+")   Next: "+next;
    }
}

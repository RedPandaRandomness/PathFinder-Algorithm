//SMT funny

import java.awt.Point;

/**
 * 
 * 
 */
public class PathNode
{
    //
    private Point coor;
    private PathNode next; //a reference to the next item in our deck
    
    public PathNode() //default constructor
    {
        coor = new Point(0,0);
        next = null; // no other value 
    }
    
    public PathNode(Point coor)
    {
        this.coor = coor; //unknown
        next = null; // no other value 
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

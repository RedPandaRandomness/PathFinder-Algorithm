//SMT funny

import java.awt.Point;

/**
 * Crossroad nodes:
 *  - Used to store coordinates for ranger to check again later
 */
public class ToCheckNode
{
    //
    private Point coor;
    private ToCheckNode next;
    
    public ToCheckNode() //default constructor
    {
        coor = new Point(-1,-1);
        next = null;
    }
    
    public ToCheckNode(Point coor)
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
    
    public void setNext(ToCheckNode next){
        this.next = next;
    }
    
    public ToCheckNode getNext(){
        return next;
    }
    
    public String toText(){
        return "Coor: ("+coor.x+", "+coor.y+")   Next: "+next;
    }
    
}

import java.awt.Point;

/**
 * 
 * 
 */
public class ToCheckNode
{
    //
    private Point coor;
    private ToCheckNode next; //a reference to the next item in our deck
    
    public ToCheckNode() //default constructor
    {
        coor = new Point(-1,-1);
        next = null; // no other value 
    }
    
    public ToCheckNode(Point coor)
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

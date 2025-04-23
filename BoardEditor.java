import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Write a description of class BoardEditor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoardEditor extends Board implements MouseListener, ActionListener
{
    Tile[][] grid;
    int state;
    Board ogBoard; 
    
    private Color line;
    private ImageIcon house = new ImageIcon("Sprites/cabin.png");
    private ImageIcon tree = new ImageIcon("Sprites/tree.png");
    private ImageIcon ranger = new ImageIcon("Sprites/Ranger/south.png");
    private Ranger her;
    
    public BoardEditor(Tile[][] grid, Board board)
    {
        this.grid = grid;
        addMouseListener(this);
        ogBoard = board;
    }
    
    public void sendRanger(Ranger ranger){
        her = ranger;
        her.setLocation(rangerStartPos);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Tree")){
            state = 4;}
        else if(e.getActionCommand().equals("Eraser")){state = 0;}
        else if(e.getActionCommand().equals("Ranger")){state = 1;}
        else if(e.getActionCommand().equals("Cabin")){state = 3;}
        else if(e.getActionCommand().equals("Save Changes")){
            ogBoard.repaint();
        }
        
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D outline = (Graphics2D) g;
        outline.setColor(Color.black);
        outline.setStroke(new BasicStroke(3));

        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                 //Draw fill
                g.setColor(grid[i][j].colour);
                g.fillRect(grid[i][j].x,grid[i][j].y,
                    grid[i][j].width,grid[i][j].height);
                
                //Draw outline
                line = new Color(60, 110, 45);
                outline.setColor(line);
                outline.setStroke(new BasicStroke(3));
                outline.drawRect(grid[i][j].x,grid[i][j].y,
                grid[i][j].width,grid[i][j].height);

                //Draw Ranger
                ImageIcon rangerBrush = ranger;
                ranger.paintIcon(this,g,her.getLocation().x*50,
                                her.getLocation().y*50);
                

                if(grid[i][j].getState() == 3){
                    ImageIcon brush = house;

                    brush.paintIcon(this,g,
                        grid[i][j].x,grid[i][j].y);
                }
                if(grid[i][j].getState() == 4){
                    ImageIcon brush = tree;

                    brush.paintIcon(this,g,
                        grid[i][j].x,grid[i][j].y);
                }
            }   
        }
    }
    public void mouseClicked(MouseEvent e)
    {   
        for(int i=0; i<grid.length; i ++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j].contains(e.getX(),e.getY())){
                    if(state != 1){
                        grid[i][j].setState(state);
                    }
                    else if (state == 1){
                        her.setLocation(grid[i][j].x/50,grid[i][j].y/50);
                        System.out.println(her.getLocation());
                    }
                }
            }
        }
        
        repaint();
        ogBoard.repaint();
    }

    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent me){}
}

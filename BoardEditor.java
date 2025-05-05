//SMT funny

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Point;


/**
 * Board editor 
 * - Lets you change the states of the tiles on the board
 *
 * @author Lora Frederico
 * @version 1.0.0
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
    private Icon Tree; 
    private Icon Ranger; 
    private Icon Eraser; 
    private Icon Cabin; 
    private Ranger her;
    
    private JButton btnTree; 
    private JButton btnCabin; 
    private JButton btnEraser; 
    private JButton btnRanger; 
    
    public BoardEditor(Tile[][] grid, Board board)
    {
        this.grid = grid;
        addMouseListener(this);
        ogBoard = board;
    }
    
    //Gives the editor the ranger from the board
    public void sendRanger(Ranger ranger){
        her = ranger;
        her.setLocation(rangerStartPos);
    }

    //"Passes" the buttons from board
    public void sendBtn(JButton a,JButton b, JButton c, JButton d ){
        btnTree = a;
        btnRanger = b;
        btnCabin = c; 
        btnEraser = d;
    }
    
    /*
     * Listens to button and set "cursor" state based on what button you pressed
     * The state reflects the state we want to change the tile to
     */
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(btnTree)){state = 4;}
        else if(e.getSource().equals(btnEraser)){state = 0;}
        else if(e.getSource().equals(btnRanger)){state = 1;}
        else if(e.getSource().equals(btnCabin)){state = 3;}
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
                
                //Draws house
                if(grid[i][j].getState() == 3){
                    ImageIcon brush = house;

                    brush.paintIcon(this,g,
                        grid[i][j].x,grid[i][j].y);
                }
                //Draws tree
                else if(grid[i][j].getState() == 4){
                    ImageIcon brush = tree;

                    brush.paintIcon(this,g,
                        grid[i][j].x,grid[i][j].y);
                }
            }   
        }
    }
    
    /*
     * Checks which tile you clicked on and as long as the ranger isnt there,
     * set that tile's state to whatever the "Cursor's" state is (based on
     * the button pressed). If the cursor state is 1, don't change the tile
     * state but instead move the ranger there
     */
    public void mouseClicked(MouseEvent e)
    {   
        for(int i=0; i<grid.length; i ++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j].contains(e.getX(),e.getY()) 
                    && !new Point(i,j).equals(her.getLocation())){
                    if(state != 1){
                        grid[i][j].setState(state);
                    }
                    else if (state == 1){
                        her.setLocation(grid[i][j].x/50,grid[i][j].y/50);
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

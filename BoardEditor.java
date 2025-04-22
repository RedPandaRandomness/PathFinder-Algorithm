import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

/**
 * Write a description of class BoardEditor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoardEditor extends Board
{
    Tile[][] grid;
    
    private Color line;
    private ImageIcon house = new ImageIcon("Sprites/cabin.png");
    private ImageIcon tree = new ImageIcon("Sprites/tree.png");
    
    public BoardEditor(Tile[][] grid)
    {
        this.grid = grid;
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
}


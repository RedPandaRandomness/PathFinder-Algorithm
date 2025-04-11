import static java.lang.System.out; //shortcut for writing output
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

/**
 * Write a description of class Board here.
 *
 * @author (Lora & Benj)
 * @version (a version number or a date)
 */
public class Board extends JPanel
{
    // instance variables
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    Tile[][] grid = new Tile[12][12];
    ImageIcon house = new ImageIcon("Sprites/drhouse.jpg");
    ImageIcon ranger = new ImageIcon("Sprites/Ranger/south.png");
    Ranger him;
    private boolean houseFound = false;
    
    Timer timer;
    int speed = 500;

    public static int i = 0;
    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        // initialise instance variables
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(new Color(211,211,211));

        //saftey check in case program didn't properly close
        if(timer!= null && timer.isRunning()){
            timer.stop();
        }
        
        main();
    }

    public void main()
    {   
        //make tiles
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                grid[i][j] = new Tile(i*50,j*50);
            }
        }
        setBoard(0); // asks for value 0-2
        
        findPath();
    }

    public void sendRanger(Ranger ranger){
        him = ranger;
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
                outline.setColor(Color.gray);
                outline.setStroke(new BasicStroke(3));
                outline.drawRect(grid[i][j].x,grid[i][j].y,
                grid[i][j].width,grid[i][j].height);
                    
                //Draw Ranger
                ImageIcon rangerBrush = ranger;
        
                rangerBrush.paintIcon(this,g,
                him.location.x*50,him.location.y*50);
                
                    if(grid[i][j].getState() == 5){
                    ImageIcon brush = house;

                    brush.paintIcon(this,g,
                        grid[i][j].x,grid[i][j].y);
                }
            }   
        }
    }

    public void findPath(){
        
        //Delay before turning cards over automatically
        timer = new Timer(speed,new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                if(!houseFound){him.look();}
                
                repaint();
                if(grid[him.location.x][him.location.y].getState() == 5){
                    houseFound = true;
                }
                //int nope =0;
            }
        });

        timer.start();
    }

    public void setBoard(int mazeType)
    {
        if(mazeType == 0)
        {
            // 0 = path
            // 4 = tree
            // 5 = cabin

            // line 1
            grid[1][0].setState(4);
            grid[2][0].setState(4);
            grid[3][0].setState(4);
            grid[4][0].setState(4);
            grid[5][0].setState(4);
            grid[6][0].setState(4);
            grid[8][0].setState(4);
            grid[9][0].setState(4);
            grid[10][0].setState(4);
            // line 2
            grid[10][1].setState(4);
            //line 3
            grid[1][2].setState(4);
            grid[3][2].setState(4);
            grid[5][2].setState(4);
            grid[6][2].setState(4);
            grid[7][2].setState(4);
            grid[8][2].setState(4);
            // line 4
            grid[1][3].setState(4);
            grid[3][3].setState(4);
            grid[4][3].setState(4);
            grid[5][3].setState(4);
            grid[10][3].setState(4);
            grid[11][3].setState(4);
            // line 5
            grid[1][4].setState(4);
            grid[4][4].setState(4);
            grid[7][4].setState(4);
            grid[8][4].setState(4);
            grid[9][4].setState(4);
            grid[10][4].setState(4);
            // line 6
            grid[1][5].setState(4);
            grid[2][5].setState(4);
            grid[3][5].setState(4);
            grid[4][5].setState(4);
            grid[5][5].setState(4);
            grid[7][5].setState(4);
            // line 7
            grid[3][6].setState(4);
            grid[5][6].setState(4);
            grid[7][6].setState(4);
            grid[8][6].setState(4);
            grid[9][6].setState(4);
            grid[11][6].setState(4);
            // line 8
            grid[0][7].setState(4);
            grid[1][7].setState(4);
            grid[3][7].setState(4);
            grid[5][7].setState(4);
            // line 9
            grid[5][8].setState(4);
            grid[7][8].setState(4);
            grid[9][8].setState(4);
            grid[10][8].setState(4);
            grid[11][8].setState(4);
            // line 10
            grid[0][9].setState(4);
            grid[2][9].setState(4);
            grid[3][9].setState(4);
            grid[4][9].setState(4);
            grid[5][9].setState(4);
            grid[7][9].setState(4);
            grid[11][9].setState(4);
            // line 11
            grid[2][10].setState(4);
            grid[7][10].setState(4);
            grid[9][10].setState(4);
            // line 12
            grid[1][11].setState(4);
            grid[2][11].setState(4);
            grid[3][11].setState(4);
            grid[5][11].setState(4);
            grid[6][11].setState(4);
            grid[7][11].setState(4);
            grid[9][11].setState(4);
            grid[10][11].setState(4);
            grid[11][11].setState(5);
        }
        else if(mazeType == 1)
        {

        }
        else if(mazeType == 2)
        {

        }
        else // shouldn't be called, panic
        {
            for(int i = 0; i < 12; i++)
            {
                for(int j = 0; j < 12; j++)
                {
                    grid[i][j].setState(5);
                }
            }
        }
    }
}

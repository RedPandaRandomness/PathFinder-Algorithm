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
import java.util.Random;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import javax.swing.Icon;


/**
 * Write a description of class Board here.
 *
 * @author (Lora & Benj)
 * @version (a version number or a date)
 */
public class Board extends JPanel implements ActionListener
{
    // instance variables
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public Tile[][] grid = new Tile[12][12];
    private ImageIcon house;
    private ImageIcon tree;
    private ImageIcon eraser;
    private ImageIcon[] rangers = new ImageIcon[4];
    private Ranger him;
    public boolean houseFound;
    private Random rng = new Random();
    private Color line;
    private File file = new File("Board/map.txt");
    private Scanner map;
    private String[] mapA = new String[12];
    public Point rangerStartPos;
    
    Timer timer;
    public int speed = 300;
    private boolean caffeine = false;

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
        
        rangers[0] = new ImageIcon("Sprites/Ranger/north.png");
        rangers[1] = new ImageIcon("Sprites/Ranger/east.png");
        rangers[2] = new ImageIcon("Sprites/Ranger/south.png");
        rangers[3] = new ImageIcon("Sprites/Ranger/west.png");
        
        house = new ImageIcon("Sprites/cabin.png");
        tree = new ImageIcon("Sprites/tree.png");
        eraser = new ImageIcon("Sprites/eraser.png");
        
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
        
        
        try{
            makeMap();
        }
        catch(Exception e){
            setBoard(rng.nextInt(2)); // asks for value 0-2
        }
    }
    
    public void makeMap() throws IOException
    {
        map = new Scanner(file);
        
        //sets map string
        int temp = 0;
        while(map.hasNextLine() )
        {
            mapA[temp] = map.nextLine();
            temp++;
        }
    
        for(int x = 0; x<grid.length; x++){
            for(int y = 0; y<grid[x].length; y++){
                if(mapA[y].charAt(x) == '#'){
                    grid[x][y].setState(4);
                }
                if(mapA[y].charAt(x) == 'H'){
                    grid[x][y].setState(3);
                }
                if(mapA[y].charAt(x) == 'R'){
                    rangerStartPos = new Point (x,y);
                }
            }
        }
    }

    public void sendRanger(Ranger ranger){
        him = ranger;
        him.setLocation(rangerStartPos);     
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
                ImageIcon rangerBrush = rangers[him.getFacing()];

                rangerBrush.paintIcon(this,g,
                    him.location.x*50,him.location.y*50);

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

    public void findPath(){
        timer = new Timer(speed,new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                if(!houseFound){him.look();}
                
                repaint();
                if(grid[him.location.x][him.location.y].getState() == 3){
                    houseFound = true;
                }
                //int nope =0;
            }
        });

        timer.start();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Caffeine mode")){
            caffeine = !caffeine;
            if(caffeine){
                timer.setDelay(50);
                timer.restart();
            }
            else{
                timer.setDelay(speed);
                timer.restart();
            }
        }
        else if(e.getActionCommand().equals("Send ranger")){
            findPath();            
        }
        else if(e.getActionCommand().equals("Edit maze")){
            JFrame editor = new JFrame();
            editor.setTitle("Maze Editor");
            
            BoardEditor editPane= new BoardEditor(grid, this);
            editPane.sendRanger(him);
            
            JPanel buttonPanel = new JPanel();
            JButton btnTree = new JButton(tree);
            JButton btnRanger = new JButton(rangers[2]);
            JButton btnCabin = new JButton(house);
            JButton btnEraser = new JButton(eraser);
            
            editPane.sendBtn(btnTree,btnRanger,btnCabin,btnEraser);
            
            buttonPanel.add(btnTree);
            buttonPanel.add(btnRanger);
            buttonPanel.add(btnCabin);
            buttonPanel.add(btnEraser);
            
            btnTree.addActionListener(editPane);
            btnRanger.addActionListener(editPane);
            btnCabin.addActionListener(editPane);
            btnEraser.addActionListener(editPane);
            
            editor.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
            editor.getContentPane().add(editPane);
            editor.pack();
            editor.setVisible(true);
        }
    }

    public void setBoard(int mazeType)
    {
        if(mazeType == 0)
        {
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
            grid[11][11].setState(3);
        }
        else if(mazeType == 1)
        {
            // line 1
            grid[5][0].setState(4);
            grid[7][0].setState(4);
            grid[8][0].setState(4);
            grid[9][0].setState(4);
            grid[10][0].setState(4);
            grid[11][0].setState(4);
            // line 2
            grid[1][1].setState(4);
            grid[2][1].setState(4);
            grid[3][1].setState(4);
            grid[4][1].setState(4);
            grid[5][1].setState(4);
            grid[7][1].setState(4);
            grid[9][1].setState(4);
            grid[11][1].setState(4);
            //line 3
            grid[2][2].setState(4);
            grid[7][2].setState(4);
            grid[9][2].setState(4);
            grid[11][2].setState(4);
            // line 4
            grid[1][3].setState(4);
            grid[2][3].setState(4);
            grid[4][3].setState(4);
            grid[6][3].setState(4);
            grid[7][3].setState(4);
            // line 5
            grid[4][4].setState(4);
            grid[6][4].setState(4);
            grid[9][4].setState(4);
            grid[11][4].setState(4);
            // line 6
            grid[0][5].setState(4);
            grid[1][5].setState(4);
            grid[2][5].setState(4);
            grid[3][5].setState(4);
            grid[4][5].setState(4);
            grid[6][5].setState(4);
            grid[7][5].setState(4);
            grid[8][5].setState(4);
            grid[9][5].setState(4);
            // line 7
            grid[1][6].setState(4);
            grid[3][6].setState(4);
            grid[11][6].setState(4);
            // line 8
            grid[1][7].setState(4);
            grid[3][7].setState(4);
            grid[5][7].setState(4);
            grid[6][7].setState(4);
            grid[8][7].setState(4);
            grid[9][7].setState(4);
            grid[10][7].setState(4);
            grid[11][7].setState(4);
            // line 9
            grid[1][8].setState(4);
            grid[5][8].setState(4);
            grid[6][8].setState(4);
            grid[8][8].setState(4);
            // line 10
            grid[3][9].setState(4);
            grid[5][9].setState(4);
            grid[10][9].setState(4);
            // line 11
            grid[0][10].setState(4);
            grid[1][10].setState(4);
            grid[2][10].setState(4);
            grid[3][10].setState(4);
            grid[5][10].setState(4);
            grid[6][10].setState(4);
            grid[8][10].setState(4);
            grid[10][10].setState(4);
            // line 12
            grid[5][11].setState(4);
            grid[8][11].setState(4);
            grid[10][11].setState(4);
            grid[11][11].setState(3);
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
                    grid[i][j].setState(3);
                }
            }
        }
    }
}

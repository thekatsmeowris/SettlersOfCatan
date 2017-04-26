import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Image;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import javafx.scene.shape.Line;

public class GameBoard extends JPanel implements 
    MouseListener, ActionListener, FocusListener {
    
 //int xpoint0 = 629;
 //int xpoint1 = 654;
 //int ypoint0 = 445;
 //int ypoint1 = 401;
 //int[] xpoints = new int[]{xpoint0,xpoint0-5  ,xpoint1,xpoint1 - 5};
 //int[] ypoints = new int[]{ypoint0+5,ypoint0  ,ypoint1+5,ypoint1 };
 //int[] xpoints = new int[]{xpoint0,xpoint1  ,xpoint1-5,xpoint0-5};
 //int[] ypoints = new int[]{ypoint0,ypoint1  ,ypoint1-5,ypoint0-5 };
    //private final ArrayList<Shape> shapes;
    //shapes = new ArrayList<Shape>();
 
 private  Shape polygon;
    
private final  ImageIcon woodGif = new ImageIcon("woodhex.gif");
private final Image woodHex =woodGif.getImage();
private final  ImageIcon clayGif = new ImageIcon("clayhex.gif");
private final Image clayHex =clayGif.getImage();
private final  ImageIcon wheatGif = new ImageIcon("wheathex.gif");
private final Image wheatHex =wheatGif.getImage();
private final  ImageIcon sheepGif = new ImageIcon("sheephex.gif");
private final Image sheepHex =sheepGif.getImage();
private final  ImageIcon oreGif = new ImageIcon("orehex.gif");
private final Image oreHex =oreGif.getImage();
private final  ImageIcon desertGif = new ImageIcon("deserthex.gif");
private final Image desertHex =desertGif.getImage();
private final  ImageIcon robberGif = new ImageIcon("robber.gif");
private final Image robber =robberGif.getImage();
private final  ImageIcon villageGif = new ImageIcon("village.gif");
private final Image village =villageGif.getImage();
private final  ImageIcon waterGif = new ImageIcon("water.gif");
private final Image waterHex =waterGif.getImage();


    private static final long serialVersionUID = 1L;
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    private final int HalfWidth = WIDTH / 2;
    private final int HalfHeight = HEIGHT / 2;

    private final Font font = new Font("Arial", Font.BOLD, 24);
    FontMetrics metrics;
    
    int[] resources = new int[]{6,6,6,6,6,4,3,2,6,6,0,1,0,0,6,6,2,1,5,4,0,6,6,3,3,2,2,6,6,4,1,3,6,6,6,6,6};
    int[] dieNums = new int[]{0,0,0,0,0,5,3,9,0,0,4,8,5,11,0,0,8,3,0,9,6,0,0,10,6,12,10,0,0,11,4,2,0,0,0,0,0};

    int radius = 50;
    int padding = 3;
    double ang30 = Math.toRadians(30);
    double xOff = Math.sin(ang30) * (radius + padding);
    double yOff = Math.cos(ang30) * (radius + padding);
    Point origin = new Point(WIDTH / 2, HEIGHT / 2);
    Hexagon hexes[][] = new Hexagon[6][6];
    
    
    public GameBoard() {
        //this.polygon = new Polygon(xpoints,ypoints, 4);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));        
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        g2d.setFont(font);
        metrics = g.getFontMetrics();
        drawCircle(g2d, HalfWidth, HalfHeight, 6660, true, true, 0x4488D6, 0);
        drawCircle(g2d, HalfWidth, HalfHeight, 381, true, true, 0x4, 0);        
        drawGameBoard(g2d, origin, 7);
        //g2d.setColor(Color.WHITE);
    }

    private void drawGameBoard(Graphics g2d, Point origin, int size) {
        int half = size / 2;
        int dieNum = 0;
        int resource = 0;
        //int hexNumber = 0;
        int i = 0;//hexNumber
        int j = 0;
        for (int col = 0; col < size; col++) {
            int rows = size - java.lang.Math.abs(col - half);

            for (int row = 0; row < rows; row++) {
                int xText = col < half ? row - col : row - half;
                int yText = col - half;
                int x = (int) (origin.x + xOff * (col - half) * 3);
                int y = (int) (origin.y + yOff *  (row * 2 + 1 - rows));
                dieNum =  dieNums[i];
                resource = resources[i]; 
                 drawHex(g2d, xText, yText, x, y, radius, resource, dieNum,j);
                 i++;
                 //dieNum++;
                 System.out.println(j);
                j++;
        }        
    }    
}
   
    private void drawHex(Graphics g, int posX, int posY, int x, int y, int r, int resource, int dieNum,int j) {
        Graphics2D g2d = (Graphics2D) g;
        Hexagon hex = new Hexagon(x, y, r);
        Point hexpoints[] = new Point[6];
        hexpoints = hex.points;
        //hexes[j] = hex;
        //j = hex.xpoints[0] ;
        
        //String text = String.format("%s : %s", coord(posX), coord(posY));
        String text = ""+dieNum;
        int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        
        switch (resource){
            case  0:   g.setColor(new Color(0x008844)); //Lumber
            break;
            case  1:   g.setColor(new Color(0xC85A17));  //Clay
            break;
            case  2:   g.setColor(new Color(0xFCCE8E)); //Wheat
            break;
            case  3:   g.setColor(new Color(0xA0FC8D)); //Sheep
            break;
            case  4:   g.setColor(new Color(0x566D7E)); //Iron
            break;
            case  5:   g.setColor(new Color(0xFFFEDC)); //Desert
            break;
            case  6:   g.setColor(new Color(0x4488D6)); //Water
            break;
        }
        g2d.setStroke(new BasicStroke(3));
        g.fillPolygon(hex);
        g.setColor(new Color(0x000000));
        g.drawPolygon(hex); //
        
        switch (resource){
            case  0:   g.drawImage(woodHex, x-48,y-45, this);
            break;
            case  1:   g.drawImage(clayHex, x-48,y-45, this);
            break;
            case  2:   g.drawImage(wheatHex, x-48,y-45, this);
            break;
            case  3:   g.drawImage(sheepHex, x-48,y-45, this);
            break;
            case  4:   g.drawImage(oreHex, x-48,y-45, this);
            break;
            case  5:   g.drawImage(desertHex, x-48,y-45, this);
                       g.drawImage(robber, x-(int)xOff,y-(int)yOff, this);
            break;
            case  6:   g.drawImage(waterHex, x-48,y-45, this);
            break;
        }
        if(resource != 5 && resource != 6){
        g.setColor(new Color(0xFFFFFF));
        g.drawString(text, x - w/2, y + h/2);
        }
                g.setColor(new Color(0x806341));
                g2d.setStroke(new BasicStroke(6));
                Line line;
     line = new Line(hexpoints[0].x,hexpoints[0].y,hexpoints[1].x,hexpoints[1].y);
                //g.drawLine(hexpoints[0].x,hexpoints[0].y,hexpoints[1].x,hexpoints[1].y);
        //g.drawLine(335, 580,386, 580);
        g2d.setStroke(new BasicStroke(3));
        g.setColor(new Color(0xFFFFFF));
        for(int c = 0; c < 6; c++)
        {
        int[] xRoadPoints = new int[]{hex.xpoints[c],hex.xpoints[(c+1)%6],hex.xpoints[(c+1)%6]-5,hex.xpoints[c]-5};
        int[] yRoadPoints = new int[]{hex.ypoints[c],hex.ypoints[(c+1)%6],hex.ypoints[(c+1)%6]-5,hex.ypoints[c]-5};
        // polygon = new Polygon(xRoadPoints,yRoadPoints,4);
        //g2d.draw(polygon);//j = hex.xpoints[0] ;
        }
        //g2d.draw(hex.xpoints[],hex.xpoints[],hex.xpoints[],hex.xpoints[]);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                 {

                    if (polygon.contains(me.getPoint())) {//check if mouse is clicked within shape
                        g2d.setColor(new Color(0x000000));
                        g2d.setStroke(new BasicStroke(6));
                        g2d.draw(polygon);
                        //repaint();
                         drawGameBoard(g2d, origin, 7);
                        //we can either just print out the object class name
                        System.out.println("Clicked a "+polygon.getClass().getName());
                    }
                }
            }
        });
    }

    
    private String coord(int value) {
        return (value > 0 ? "+" : "") + Integer.toString(value);
    }

    public void drawCircle(Graphics2D g, int x, int y, int diameter,
            boolean centered, boolean filled, int colorValue, int lineThickness) {
        drawOval(g, x, y, diameter, diameter, centered, filled, colorValue, lineThickness);
    }

    public void drawOval(Graphics2D g, int x, int y, int width, int height,
            boolean centered, boolean filled, int colorValue, int lineThickness) {
        // Store before changing.
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));

        int x2 = centered ? x - (width / 2) : x;
        int y2 = centered ? y - (height / 2) : y;

        if (filled)
            g.fillOval(x2, y2, width, height);
        else
            g.drawOval(x2, y2, width, height);
        // Set values to previous when done.
        g.setColor(tmpC);
        g.setStroke(tmpS);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        GameBoard p = new GameBoard();
        f.setContentPane(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         //
        //  get the x & y coordinates where mouse was clicked
        //int x = e.getX( );
        //int y = e.getY( );
       // System.out.print(x);
        //robber.setLocation(x,y);
         //repaint();
        //
        //  move rectangle and display 
        //      location in JTextFields,
        //      if clicked in display area
        //if( moveToValidLocation( x, y ) ) {
            
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

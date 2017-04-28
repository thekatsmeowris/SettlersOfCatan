
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;

public class Hexagon extends Polygon {
    private static final long serialVersionUID = 1L;
    public static final int SIDES = 6;
    public Point[] points = new Point[SIDES];
    private Point center = new Point(0, 0);
    private int radius;
    private final int rotation;
    
    public Hexagon(Point center, int radius) {
        this.rotation = 0;
        npoints = SIDES;
        xpoints = new int[SIDES];
        ypoints = new int[SIDES];
        this.center = center;
        this.radius = radius;
        System.out.println("Center   " +  center.x + "  "+ center.y);
        updatePoints();    
    }

    public Hexagon(int x, int y, int radius) {
        this(new Point(x, y), radius);     
        //this.rotation = 0;
    }
   
    private double findAngle(double fraction) {
        return fraction * Math.PI * 2 + Math.toRadians((rotation + 180) % 360);
    }

    private Point findPoint(double angle) {
        int x = (int) (center.x + Math.cos(angle) * radius);
        int y = (int) (center.y + Math.sin(angle) * radius);
        return new Point(x, y);
    }

    protected final void updatePoints() {
        for (int p = 0; p < SIDES; p++) {
            double angle = findAngle((double) p / SIDES);
            Point point = findPoint(angle);
            xpoints[p] = point.x;
            ypoints[p] = point.y;
            points[p] = point;
            System.out.printf("%d. (%d, %d)\n", p, point.x, point.y);        
        }
    }

    public void drawPolygon(Graphics2D g, int x, int y, int lineThickness, int colorValue, boolean filled) {
        // Store before changing.
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

        if (filled)
            g.fillPolygon(xpoints, ypoints, npoints);
        else
            g.drawPolygon(xpoints, ypoints, npoints);
        
        // Set values to previous when done.
        g.setColor(tmpC);
        g.setStroke(tmpS);     
    }   
}
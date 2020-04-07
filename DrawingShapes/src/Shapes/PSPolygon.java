package Shapes;

import sample.PSShape;
import javafx.scene.canvas.GraphicsContext;
import sample.Point;

import java.util.ArrayList;

public class PSPolygon extends PSShape {

    public int minimumQuantityOfCoordinates = 3;
    private int currentCoordinate = 0;
    private ArrayList<Point> pointList = new ArrayList<Point>();

    double[] xPoints, yPoints;
    int nPoints;

    private double[] getXPoints() {
        double[] XPOINTS = new double[pointList.size()];
        int i = 0;
        for (Point point : pointList) {
            XPOINTS[i] = point.x;
            i++;
        }
        return XPOINTS;
    }

    private double[] getYPoints() {
        double[] YPOINTS = new double[pointList.size()];
        int i = 0;
        for (Point point : pointList) {
            YPOINTS[i] = point.y;
            i++;
        }
        return YPOINTS;
    }

    @Override
    public int leftPoints() {
        return Math.max(minimumQuantityOfCoordinates - currentCoordinate, 0);
    }

    @Override
    public int quantityOfCoordinates() {
        return minimumQuantityOfCoordinates;
    }

    @Override
    public void addPoints(Point point) {
        pointList.add(point);
        currentCoordinate++;
    }

    @Override
    public void draw(GraphicsContext g) {
        currentCoordinate = 0;
        xPoints = getXPoints();
        yPoints = getYPoints();
        nPoints = pointList.size();
        g.fillPolygon(xPoints, yPoints, nPoints);
        pointList.clear();
    }
}

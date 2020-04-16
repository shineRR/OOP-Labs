package Plugins;

import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import sample.PSShape;
import sample.Point;
import java.util.ArrayList;

public class PSTrapezium extends PSShape {

    public int quantityOfCoordinates = 4;
    public int minimumQuantityOfCoordinates = 4;
    private int currentCoordinate = 0;

    ArrayList<Point> pointList = new ArrayList<Point>();
    double[] xPoints, yPoints;

    private Pair[] getPoints() {
        double[] XPOINTS = new double[pointList.size()];
        double[] YPOINTS = new double[pointList.size()];
        int i = 0;
        for (Point point : pointList) {
            XPOINTS[i] = point.x;
            YPOINTS[i] = point.y;
            i++;
        }
        return new Pair[]{
                new Pair<>("xPoints", XPOINTS),
                new Pair<>("yPoints", YPOINTS) };
    }

    private void calculatePoints() {
        double x1 = xPoints[0];
        double x2 = xPoints[1];
        double x3 = xPoints[2];
        double x4 = xPoints[3];
        double y1 = yPoints[0];
        double y2 = yPoints[1];
        double y3 = yPoints[2];
        double y4 = yPoints[3];

        double lenAD = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
        double lenBC = Math.sqrt(Math.pow(y4 - y3, 2) + Math.pow(x4 - x3, 2));
        double coef = lenAD / lenBC;
        Point vectorBC = new Point();
        vectorBC.x = coef * (x3 - x2);
        vectorBC.y = coef * (y3 - y2);
        xPoints[3] = vectorBC.x + x1;
        yPoints[3] = vectorBC.y + y1;
    }

    @Override
    public int leftPoints() { return quantityOfCoordinates - currentCoordinate; }

    @Override
    public int quantityOfCoordinates() { return quantityOfCoordinates; }

    @Override
    public int minimumQuantityOfCoordinates() { return minimumQuantityOfCoordinates; }

    @Override
    public void addPoints(Point point) {
        pointList.add(point);
        currentCoordinate++;
    }

    @Override
    public void draw(GraphicsContext g) {
        var pairOfXYArrays = getPoints();
        xPoints = (double[]) pairOfXYArrays[0].getValue();
        yPoints = (double[]) pairOfXYArrays[1].getValue();
        calculatePoints();
        currentCoordinate = 0;
        g.fillPolygon(xPoints, yPoints, quantityOfCoordinates);
    }
}

package Shapes;

import javafx.util.Pair;
import sample.PSShape;
import javafx.scene.canvas.GraphicsContext;
import sample.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class PSPolygon extends PSShape {

    public int minimumQuantityOfCoordinates = 3;
    public int quantityOfCoordinates = 4;
    private int currentCoordinate = 0;

    ArrayList<Point> pointList = new ArrayList<Point>();
    double[] xPoints, yPoints;
    int nPoints;

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

    @Override
    public int leftPoints() {
        return Math.max(minimumQuantityOfCoordinates - currentCoordinate, 0);
    }

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
        currentCoordinate = 0;
        var pairOfXYArrays = getPoints();
        xPoints = (double[]) pairOfXYArrays[0].getValue();
        yPoints = (double[]) pairOfXYArrays[1].getValue();
        nPoints = pointList.size();
        g.fillPolygon(xPoints, yPoints, nPoints);
    }
}

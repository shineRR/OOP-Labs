package Shapes;

import sample.PSShape;
import javafx.scene.canvas.GraphicsContext;
import sample.Point;

import java.util.ArrayList;

public class PSPolygon extends PSShape {

    public int quantityOfCoordinates = 5;
    private int currentCoordinate = 0;
    private Point[] points = new Point[quantityOfCoordinates];

    double[] xPoints, yPoints;
    int nPoints;

    private double[] getXPoints(Point[] points) {
        double[] XPoints = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            XPoints[i] = points[i].x;
        }
        return XPoints;
    }

    private double[] getYPoints(Point[] points) {
        double[] YPoints = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            YPoints[i] = points[i].x;
        }
        return YPoints;
    }

    @Override
    public int quantityOfCoordinates() {
        return quantityOfCoordinates;
    }

    @Override
    public void addPoints(Point point) {
        System.out.println(currentCoordinate + " + 1");
        points[currentCoordinate] = point;
        currentCoordinate++;
    }

    @Override
    public void draw(GraphicsContext g) {
        currentCoordinate = 0;
        g.fillPolygon(getXPoints(points), getYPoints(points), points.length);
    }
}

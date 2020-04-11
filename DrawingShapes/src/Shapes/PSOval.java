package Shapes;

import javafx.scene.canvas.GraphicsContext;
import sample.PSShape;
import sample.Point;
import java.lang.Math;

public class PSOval extends PSShape {

    public int quantityOfCoordinates = 2;
    public int minimumQuantityOfCoordinates = 2;
    private int currentCoordinate = 0;
    private Point[] points = new Point[quantityOfCoordinates];

    private double positiveSubtraction(double secondPoint, double firstPoint) {
        return (secondPoint - firstPoint < 0 ? (-1) * (secondPoint - firstPoint) : secondPoint - firstPoint);
    }

    @Override
    public int leftPoints() {
        return quantityOfCoordinates - currentCoordinate;
    }

    @Override
    public int quantityOfCoordinates() {
        return quantityOfCoordinates;
    }

    @Override
    public int minimumQuantityOfCoordinates() { return minimumQuantityOfCoordinates; }

    @Override
    public void addPoints(Point point) {
        points[currentCoordinate] = point;
        currentCoordinate++;
    }

    @Override
    public void draw(GraphicsContext g) {
        currentCoordinate = 0;
        points = validateValuesForTwoCoordinates(points);
        g.fillOval(points[0].x, points[0].y, Math.abs(points[1].x - points[0].x), Math.abs(points[1].y - points[0].y));
    }
}

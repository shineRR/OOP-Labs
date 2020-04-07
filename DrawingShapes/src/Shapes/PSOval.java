package Shapes;

import javafx.scene.canvas.GraphicsContext;
import sample.PSShape;
import sample.Point;
import java.lang.Math;

public class PSOval extends PSShape {

    public int quantityOfCoordinates = 2;
    private int currentCoordinate = 0;
    private Point[] points = new Point[quantityOfCoordinates];

    private double positiveSubtraction(double secondPoint, double firstPoint) {
        return (secondPoint - firstPoint < 0 ? (-1) * (secondPoint - firstPoint) : secondPoint - firstPoint);
    }

    private void validateValues() {
        double dstX2X1 = points[1].x - points[0].x;;
        double dstY2Y1 = points[1].y - points[0].y;;

        if (dstX2X1 < 0) {
            double temp = points[0].x;
            points[0].x = points[1].x;
            points[1].x = temp;
        }

        if (dstY2Y1 < 0) {
            double temp = points[0].y;
            points[0].y = points[1].y;
            points[1].y = temp;
        }
    }

    @Override
    public int quantityOfCoordinates() {
        return quantityOfCoordinates;
    }

    @Override
    public void addPoints(Point point) {
        points[currentCoordinate] = point;
        currentCoordinate++;
    }

    @Override
    public void draw(GraphicsContext g) {
        currentCoordinate = 0;
        validateValues();
        // x, y, width, height
        g.fillOval(points[0].x, points[0].y, Math.abs(points[1].x - points[0].x), Math.abs(points[1].y - points[0].y));
    }
}
